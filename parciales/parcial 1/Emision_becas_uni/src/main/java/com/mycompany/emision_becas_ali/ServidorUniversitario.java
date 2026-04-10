/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.emision_becas_ali;

/**
 *
 * @author USUARIO
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServidorUniversitario extends UnicastRemoteObject 
    implements ServidorUniversitarioInterface {
    
    public ServidorUniversitario() throws Exception {
        super();
    }
    
    @Override
    public RespuestaBeca solicitarBeca(String ci, String nombres, String apellidos) {
        System.out.println("\nSOLICITUD: " + ci + " - " + nombres + " " + apellidos);
        
    
        if (!consultarSEGIP(ci)) {
            return new RespuestaBeca(false, "No encontrado en SEGIP", 0);
        }
        
      
        ArrayList<Nota> notas = consultarBienestar(ci);
        double promedio = calcularPromedio(notas);
        if (promedio <= 70) {
            return new RespuestaBeca(false, "Promedio insuficiente: " + promedio, promedio);
        }
        
       
        String deuda = consultarFinanciero(ci);
        if (!deuda.isEmpty()) {
            return new RespuestaBeca(false, "Deuda: " + deuda, promedio);
        }
        
        return new RespuestaBeca(true, "Elegible para beca", promedio);
    }
    
    boolean consultarSEGIP(String ci) {
        try (Socket s = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            out.println("verificar " + ci);
            return "encontrado".equals(in.readLine());
        } catch (Exception e) { return false; }
    }
    
    ArrayList<Nota> consultarBienestar(String ci) {
        try {
            BienestarInterface bien = (BienestarInterface) 
                Naming.lookup("rmi://localhost/BienestarService");
            return bien.obtenerHistorial(ci);
        } catch (Exception e) { return new ArrayList<>(); }
    }
    
    String consultarFinanciero(String ci) {
        try (DatagramSocket s = new DatagramSocket()) {
            String msg = "deuda:" + ci;
            byte[] data = msg.getBytes();
            InetAddress addr = InetAddress.getByName("localhost");
            s.send(new DatagramPacket(data, data.length, addr, 12346));
            
            byte[] buf = new byte[1024];
            DatagramPacket resp = new DatagramPacket(buf, buf.length);
            s.receive(resp);
            return new String(resp.getData(), 0, resp.getLength()).trim();
        } catch (Exception e) { return "ERROR"; }
    }
    
    double calcularPromedio(ArrayList<Nota> notas) {
        if (notas.isEmpty()) return 0;
        int suma = 0;
        for (Nota n : notas) suma += n.calificacion;
        return suma / (double)notas.size();
    }
    
   public static void main(String[] args) throws Exception {
        ServidorUniversitario obj = new ServidorUniversitario();
        
        Registry reg = LocateRegistry.getRegistry(1099);
        reg.rebind("ServidorUniversitario", obj);
        
        System.out.println("UNIVERSITARIO RMI");
    }
}
