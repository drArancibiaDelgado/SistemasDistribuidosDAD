/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.com.practica2;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerJusticia {

    public static void main(String[] args) {
        try {
            Justicia justicia = new Justicia();
            LocateRegistry.createRegistry(1099);
            Naming.bind("Justicia", justicia);
            System.out.println("Servidor Justicia RMI listo. (bind: Justicia, puerto 1099)");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException ex) {
            System.out.println("Error ServerJusticia: " + ex.getMessage());
        }
    }
}


