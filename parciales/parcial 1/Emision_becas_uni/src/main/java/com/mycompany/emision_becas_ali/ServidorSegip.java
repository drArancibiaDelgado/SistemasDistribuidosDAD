/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.emision_becas_ali;


/**
 *
 * @author USUARIO
 */
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ServidorSegip {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(12345)) {
            System.out.println("SEGIP TCP");
            while (true) {
                Socket s = ss.accept();
                new Thread(() -> manejarCliente(s)).start();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private static void manejarCliente(Socket clientSocket) {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

        String request = in.readLine();
        String ci = request.split(" ")[1].split("-")[0];

        // Solo Ana Gomez está registrada
        String respuesta = "1234567".equals(ci) ? "encontrado" : "no_encontrado";
        out.println(respuesta);
        System.out.println("SEGIP → CI:" + ci + " = " + respuesta);

    } catch (Exception e) {}
}
}
