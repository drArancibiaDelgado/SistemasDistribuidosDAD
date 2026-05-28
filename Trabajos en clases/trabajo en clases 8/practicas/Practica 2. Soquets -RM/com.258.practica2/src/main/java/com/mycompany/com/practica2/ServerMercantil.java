/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.com.practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerMercantil {

    private static final int PORT = 5002;

    // Cuentas cargadas aparte:
    private static final HashMap<String, ArrayList<DatosPrueba.RegCuenta>> db = DatosPrueba.cargarMercantil();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Banco Mercantil (TCP) listo en puerto " + PORT);

            while (true) {
                Socket client = server.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream());

                String ci = fromClient.readLine();      // paso 1
                String op = fromClient.readLine();      // paso 2 (buscar)
                if (op == null) op = "";
                op = op.trim();

                if (op.equalsIgnoreCase("buscar")) {
                    String resp = DatosPrueba.toCadenaRespuesta(db, ci); // "cuenta-saldo:..." [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)
                    toClient.println(resp);

                } else if (op.equalsIgnoreCase("congelar")) {
                    // payload: "nro-monto"
                    String payload = fromClient.readLine();
                    toClient.println(procesarCongelamiento(payload));

                } else {
                    toClient.println("");
                }

                client.close();
            }
        } catch (IOException ex) {
            System.out.println("Error Mercantil: " + ex.getMessage());
        }
    }

    private static String procesarCongelamiento(String payload) {
        try {
            if (payload == null) return "NO";
            payload = payload.trim();
            String[] parts = payload.split("-");
            if (parts.length != 2) return "NO";

            String nro = parts[0].trim();
            float monto = Float.parseFloat(parts[1].trim());

            DatosPrueba.RegCuenta reg = DatosPrueba.buscarPorNro(db, nro);
            if (reg == null) return "NO";

            if (reg.saldo >= monto) {
                reg.saldo = reg.saldo - monto; // simulación de retención
                return "OK";
            }
            return "NO";
        } catch (Exception e) {
            return "NO";
        }
    }
}

