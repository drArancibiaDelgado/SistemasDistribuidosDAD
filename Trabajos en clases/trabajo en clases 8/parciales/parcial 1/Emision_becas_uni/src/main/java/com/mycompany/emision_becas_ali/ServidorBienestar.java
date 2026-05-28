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

    ArrayList<Nota> ana = new ArrayList<>();
    ana.add(new Nota("Matemáticas", 90));
    ana.add(new Nota("Física", 85));
    ana.add(new Nota("Química", 80));

    if ("1234567".equals(ci)) {
        return ana;
    }
    return new ArrayList<>();
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