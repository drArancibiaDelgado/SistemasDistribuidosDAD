/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.hola.udp;

/**
 *
 * @author Animetx
 */
import java.net.*;

public class ClientHandlerUDP extends Thread {

    DatagramSocket server;
    DatagramPacket packet;

    public ClientHandlerUDP(DatagramSocket server, DatagramPacket packet) {
        this.server = server;
        this.packet = packet;
    }

    public void run() {

        try {

            String recibido = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Mensaje recibido: " + recibido);

            // formato esperado: numero,numero,operacion
            String[] partes = recibido.split(",");

            double n1 = Double.parseDouble(partes[0]);
            double n2 = Double.parseDouble(partes[1]);
            String op = partes[2];

            double resultado = 0;

            switch (op) {
                case "+":
                    resultado = n1 + n2;
                    break;
                case "-":
                    resultado = n1 - n2;
                    break;
                case "*":
                    resultado = n1 * n2;
                    break;
                case "/":
                    resultado = n1 / n2;
                    break;
                default:
                    break;
            }

            String respuesta = "Resultado: " + resultado;

            byte[] data = respuesta.getBytes();

            DatagramPacket resp = new DatagramPacket(
                    data,
                    data.length,
                    packet.getAddress(),
                    packet.getPort()
            );

            server.send(resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
