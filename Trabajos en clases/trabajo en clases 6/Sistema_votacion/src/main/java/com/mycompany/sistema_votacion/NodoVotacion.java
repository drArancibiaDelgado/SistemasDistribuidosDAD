/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_votacion;

/**
 *
 * @author Animetx
 */
import org.jgroups.*;
import org.jgroups.util.Util;
import java.io.*;
import java.util.*;

public class NodoVotacion implements Receiver {

    private JChannel canal;
    private String nombreNodo;
    private boolean esIniciador;

    // Estado de la votación
    private String preguntaActual;
    private String[] opcionesActuales;
    private Map<String, Integer> conteoVotos = new HashMap<>();
    private Set<String> nodoQueVotaron   = new HashSet<>();
    private boolean votacionActiva = false;

    // CONSTRUCTOR E INICIO                                               

    public NodoVotacion(String nombreNodo, boolean esIniciador) throws Exception {
        this.nombreNodo  = nombreNodo;
        this.esIniciador = esIniciador;
    }

    public void iniciar() throws Exception {
        canal = new JChannel();          // usa udp.xml por defecto
        canal.setReceiver(this);
        canal.connect("CanalVotacion"); // todos los nodos usan el mismo nombre

        System.out.println(" Nodo [" + nombreNodo + "] conectado al canal.");
        System.out.println("   Miembros actuales: " + canal.getView().getMembers());

        if (esIniciador) {
            Thread.sleep(2000);          // espera a que otros nodos se unan
            iniciarVotacion();
        }

        // Menú interactivo para nodos votantes
        if (!esIniciador) {
            menuVotante();
        }
    }

    //INICIADOR: propone la pregunta        

    private void iniciarVotacion() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== MODO INICIADOR ===");
        System.out.print("Escribe la pregunta de votación: ");
        preguntaActual = sc.nextLine().trim();

        System.out.print("¿Cuántas opciones? ");
        int n = Integer.parseInt(sc.nextLine().trim());
        opcionesActuales = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("  Opción " + (i + 1) + ": ");
            opcionesActuales[i] = sc.nextLine().trim();
            conteoVotos.put(opcionesActuales[i], 0);
        }

        // Construir y enviar mensaje PREGUNTA
        MensajeVotacion mv = new MensajeVotacion(
                MensajeVotacion.Tipo.PREGUNTA, preguntaActual, nombreNodo);
        mv.setOpciones(opcionesActuales);

        enviarMensaje(mv);
        System.out.println("\n Votación iniciada. Esperando votos...");
        votacionActiva = true;

        // Esperar hasta que todos voten o el iniciador cierre manualmente
        esperarResultados(sc);
    }

    private void esperarResultados(Scanner sc) throws Exception {
        System.out.println("Presiona ENTER cuando quieras mostrar resultados finales.");
        sc.nextLine();
        publicarResultados();
    }

    private void publicarResultados() throws Exception {
        StringBuilder sb = new StringBuilder("RESULTADOS:\n");
        conteoVotos.forEach((opcion, votos) ->
                sb.append("  ").append(opcion).append(": ").append(votos).append(" voto(s)\n"));

        MensajeVotacion mv = new MensajeVotacion(
                MensajeVotacion.Tipo.RESULTADOS, sb.toString(), nombreNodo);
        enviarMensaje(mv);
    }

    //VOTANTE: menú para elegir opción                   

    private void menuVotante() throws Exception {
        Scanner sc = new Scanner(System.in);

        // Esperar hasta que llegue la pregunta
        while (!votacionActiva) {
            Thread.sleep(500);
        }

        System.out.println("\n=== VOTACIÓN ACTIVA ===");
        System.out.println("Pregunta: " + preguntaActual);
        for (int i = 0; i < opcionesActuales.length; i++) {
            System.out.println("  [" + (i + 1) + "] " + opcionesActuales[i]);
        }

        int eleccion = -1;
        while (eleccion < 1 || eleccion > opcionesActuales.length) {
            System.out.print("Tu voto (número): ");
            try { eleccion = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { /* reintenta */ }
        }

        String opcionElegida = opcionesActuales[eleccion - 1];
        MensajeVotacion mv = new MensajeVotacion(
                MensajeVotacion.Tipo.VOTO, opcionElegida, nombreNodo);
        enviarMensaje(mv);

        System.out.println("Voto enviado: " + opcionElegida);
        System.out.println("Esperando resultados...");

        // Mantener el nodo vivo hasta recibir resultados
        Thread.sleep(30_000);
    }

    //RECEPCIÓN DE MENSAJES (interfaz Receiver)
    @Override
    public void receive(Message msg) {
        try {
            MensajeVotacion mv = Util.objectFromByteBuffer(msg.getArray());

            switch (mv.getTipo()) {

                case PREGUNTA:
                    preguntaActual   = mv.getContenido();
                    opcionesActuales = mv.getOpciones();
                    for (String op : opcionesActuales) conteoVotos.put(op, 0);
                    votacionActiva = true;
                    System.out.println("\nNueva votación de [" + mv.getRemitenteNombre() + "]: "
                            + preguntaActual);
                    break;

                case VOTO:
                    if (esIniciador) {
                        String opcion = mv.getContenido();
                        conteoVotos.merge(opcion, 1, Integer::sum);
                        nodoQueVotaron.add(mv.getRemitenteNombre());
                        System.out.println("Voto recibido de [" + mv.getRemitenteNombre()
                                + "]: " + opcion);
                    }
                    break;

                case RESULTADOS:
                    System.out.println("\n" + mv.getContenido());
                    votacionActiva = false;
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error procesando mensaje: " + e.getMessage());
        }
    }

    @Override
    public void viewAccepted(View nuevaVista) {
        System.out.println("Vista actualizada: " + nuevaVista.getMembers());
    }

    //UTILIDAD                                                           
    private void enviarMensaje(MensajeVotacion mv) throws Exception {
        byte[] datos = Util.objectToByteBuffer(mv);
        Message msg  = new BytesMessage(null, datos); // null = broadcast
        canal.send(msg);
    }

    public void cerrar() {
        if (canal != null) canal.close();
    }
}