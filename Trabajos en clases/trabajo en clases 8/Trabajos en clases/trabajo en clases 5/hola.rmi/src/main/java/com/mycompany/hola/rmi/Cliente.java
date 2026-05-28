/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hola.rmi;

/**
 *
 * @author Animetx
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            ICalcular calculadora = (ICalcular) Naming.lookup("rmi://localhost/Hola");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Fibonacci");
                System.out.println("2. Factorial");
                System.out.println("3. Sumatoria");
                System.out.println("4. Salir");
                System.out.print("Ingrese una opción: ");
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese un número: ");
                        int n = scanner.nextInt();
                        System.out.println("Fibonacci de " + n + " es: " + calculadora.fibonacci(n));
                        break;
                    case 2:
                        System.out.print("Ingrese un número: ");
                        n = scanner.nextInt();
                        System.out.println("Factorial de " + n + " es: " + calculadora.factorial(n));
                        break;
                    case 3:
                        System.out.print("Ingrese un número: ");
                        n = scanner.nextInt();
                        System.out.println("Sumatoria de " + n + " es: " + calculadora.sumatoria(n));
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }
        } catch (NotBoundException ex) {
            System.err.println("Error en el cliente: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println("Error en el cliente: " + ex.getMessage());
        } catch (RemoteException ex) {
            System.err.println("Error en el cliente: " + ex.getMessage());
        }
    }
}
