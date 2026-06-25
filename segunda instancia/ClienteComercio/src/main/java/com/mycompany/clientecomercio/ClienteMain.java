/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.clientecomercio;

/**
 *
 * @author USUARIO
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import orquestador.IOrquestador;
import java.util.Scanner;

public class ClienteMain {
    public static void main(String[] args) {
        try {
            // Nos conectamos al registro RMI en localhost (puerto 1099)
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            
            // Buscamos el servicio por el nombre que le dimos en el servidor
            IOrquestador servicio = (IOrquestador) registro.lookup("ServicioIntercambio");
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("--- SITIO COMERCIO (CLIENTE RMI) ---");
            System.out.print("Ingrese token de autenticación: ");
            String token = scanner.nextLine();
            
            System.out.print("Ingrese cuenta origen: ");
            String ctaOrigen = scanner.nextLine();
            
            System.out.print("Ingrese cuenta destino: ");
            String ctaDestino = scanner.nextLine();
            
            System.out.print("Ingrese monto a transferir: ");
            double monto = scanner.nextDouble();
            
            // ¡Invocamos el método remoto!
            System.out.println("\nEnviando petición al Orquestador...");
            String respuesta = servicio.realizarTransferencia(token, ctaOrigen, ctaDestino, monto);
            
            System.out.println("\nRESULTADO: " + respuesta);
            
        } catch (Exception e) {
            System.err.println("Error en el cliente RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
