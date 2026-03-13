/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hola.rmi;

import java.rmi.RemoteException;

/**
 *
 * @author Animetx
 */
public class Factorial implements ICalcular {

    int n;

    public Factorial(int n) {
        this.n = n;
    }

    public int factorial() {
        if (n < 0) {
            System.out.println("El factorial no existe para números negativos.");
            return 0;
        }

        long f = 1;
        for (int i = 1; i <= n; i++)
            f *= i;

        System.out.println("Factorial de " + n + " = " + f);
        return 0;
    }

    public int fibonachi() { return 0; }

    public int sumatoria() { return 0; }

    public String saludo() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int fibonacci(int n) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int factorial(int n) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int sumatoria(int n) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
