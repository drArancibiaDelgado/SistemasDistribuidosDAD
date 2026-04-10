/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.emision_becas_ali;

/**
 *
 * @author USUARIO
 */
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ServidorFinanciero {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(12346);
        System.out.println("FINANCIERO UDP");
        
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket req = new DatagramPacket(buffer, buffer.length);
            socket.receive(req);
            
            String msg = new String(req.getData(), 0, req.getLength());
            String ci = msg.replace("deuda:", "");
            
            Map<String, String> deudas = new HashMap<>();
            deudas.put("1234567", "");        
            deudas.put("7654321", "");        
            deudas.put("1122334", "300.50");  
            deudas.put("4433221", "");        
            deudas.put("5566778", "150.00");  
            
            String deuda = deudas.getOrDefault(ci, "1000.00");
            byte[] respBytes = deuda.getBytes();
            DatagramPacket reply = new DatagramPacket(respBytes, respBytes.length, 
                req.getAddress(), req.getPort());
            socket.send(reply);
            System.out.println("FINANCIERO → CI:" + ci + " deuda:'" + deuda + "'");
        }
    }
}