/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.server.hola.udp;

/**
 *
 * @author Animetx
 */
import java.net.*;

public class ServerHolaUdp {

    public static void main(String[] args) {
        int port = 5002;

        try {
            DatagramSocket server = new DatagramSocket(port);
            System.out.println("Servidor UDP iniciado en puerto " + port);

            while (true) {

                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                server.receive(packet);

                // crear hilo para procesar
                Thread t = new ClientHandlerUDP(server, packet);
                t.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}