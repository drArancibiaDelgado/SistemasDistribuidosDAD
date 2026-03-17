/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.com.practica2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerBCP {

    private static final int PORT = 6789;

    // Cuentas cargadas aparte:
    private static final HashMap<String, ArrayList<DatosPrueba.RegCuenta>> db = DatosPrueba.cargarBCP();

    public static void main(String[] args) {
        try {
            DatagramSocket socketUDP = new DatagramSocket(PORT);
            byte[] buffer = new byte[1000];
            System.out.println("Banco BCP (UDP) listo en puerto " + PORT);

            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);

                String msg = new String(peticion.getData(), 0, peticion.getLength()).trim();
                String response = procesar(msg);

                byte[] out = response.getBytes();
                DatagramPacket respuesta = new DatagramPacket(out, out.length, peticion.getAddress(), peticion.getPort());
                socketUDP.send(respuesta);
            }
        } catch (IOException e) {
            System.out.println("Error BCP: " + e.getMessage());
        }
    }

    private static String procesar(String msg) {
        try {
            // Formato del enunciado: Operación:ci [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)
            String[] p = msg.split(":");
            if (p.length < 2) return "";

            String op = p[0].trim();

            if (op.equalsIgnoreCase("buscar")) {
                String ci = p[1].trim();
                return DatosPrueba.toCadenaRespuesta(db, ci); // "cuenta-saldo:..." [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)

            } else if (op.equalsIgnoreCase("congelar")) {
                // congelar:ci:nro:monto
                if (p.length != 4) return "NO";
                String nro = p[2].trim();
                float monto = Float.parseFloat(p[3].trim());

                DatosPrueba.RegCuenta reg = DatosPrueba.buscarPorNro(db, nro);
                if (reg == null) return "NO";

                if (reg.saldo >= monto) {
                    reg.saldo = reg.saldo - monto;
                    return "OK";
                }
                return "NO";
            }

            return "";
        } catch (Exception e) {
            return "";
        }
    }
}
