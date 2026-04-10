/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.emision_becas_ali;

/**
 *
 * @author USUARIO
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServidorBienestar extends UnicastRemoteObject implements BienestarInterface {
    
    public ServidorBienestar() throws RemoteException {
        super();
    }
    
    @Override
    public ArrayList<Nota> obtenerHistorial(String ci) throws RemoteException {
        System.out.println("BIENESTAR: historial " + ci);
        
        Map<String, ArrayList<Nota>> historiales = new HashMap<>();
        
  
        ArrayList<Nota> ana = new ArrayList<>();
        ana.add(new Nota("Matemáticas", 90));
        ana.add(new Nota("Física", 85));
        ana.add(new Nota("Química", 80));
        historiales.put("1234567", ana);
        
        
        ArrayList<Nota> juan = new ArrayList<>();
        juan.add(new Nota("Matemáticas", 60));
        juan.add(new Nota("Física", 55));
        juan.add(new Nota("Química", 65));
        historiales.put("7654321", juan);
        
      
        ArrayList<Nota> maria = new ArrayList<>();
        maria.add(new Nota("Historia", 92));
        maria.add(new Nota("Literatura", 88));
        maria.add(new Nota("Biología", 85));
        historiales.put("1122334", maria);
        
   
        historiales.put("4433221", new ArrayList<>());
        
        
        historiales.put("5566778", new ArrayList<>());
        
        return historiales.getOrDefault(ci, new ArrayList<>());
    }
    
    public static void main(String[] args) {
        try {
            ServidorBienestar obj = new ServidorBienestar();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("BienestarService", obj);
            System.out.println("BIENESTAR RMI:");
        } catch (Exception e) {
            System.err.println("Error Bienestar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}