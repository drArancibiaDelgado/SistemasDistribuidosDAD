/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.hola.rmi;

/**
 Realizar un sistema utilizando la tecnología RMI para realizar el calculo de las operación de factorial, Fibonacci, 
 * sumatoria de un numero , el cliente a través de un menú solicitara la opción 
 * y llamara al método pertinente luego mostrara el resultado
 */
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
        try {
            Calculadora calculadora = new Calculadora();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost/Hola", calculadora);
            System.out.println("Servidor listo...");
        } catch (RemoteException ex) {
            System.err.println("Error en el servidor: " + ex.getMessage());
        } catch (AlreadyBoundException ex) {
            System.err.println("Error en el servidor: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println("Error en el servidor: " + ex.getMessage());
        }
    }
}