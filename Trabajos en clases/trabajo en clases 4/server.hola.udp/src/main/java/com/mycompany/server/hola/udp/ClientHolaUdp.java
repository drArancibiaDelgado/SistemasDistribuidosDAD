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
import java.util.Scanner;

public class ClientHolaUdp {

    public static void main(String[] args) {

        int port = 5002;

        try {

            DatagramSocket socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");

            Scanner sc = new Scanner(System.in);

            System.out.print("Primer numero: ");
            double n1 = sc.nextDouble();

            System.out.print("Segundo numero: ");
            double n2 = sc.nextDouble();

            System.out.print("Operacion (+ - * /): ");
            String op = sc.next();

            String mensaje = n1 + "," + n2 + "," + op;

            byte[] sendData = mensaje.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    sendData,
                    sendData.length,
                    ip,
                    port
            );

            socket.send(packet);

            byte[] buffer = new byte[1024];

            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

            socket.receive(respuesta);

            String resultado = new String(respuesta.getData(), 0, respuesta.getLength());

            System.out.println(resultado);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}