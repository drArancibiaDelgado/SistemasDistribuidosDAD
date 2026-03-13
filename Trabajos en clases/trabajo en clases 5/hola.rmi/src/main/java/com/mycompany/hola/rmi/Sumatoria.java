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
public class Sumatoria implements ICalcular {

    int n;

    public Sumatoria(int n) {
        this.n = n;
    }

    public int sumatoria() {
        int s = 0;

        for (int i = 1; i <= n; i++)
            s += i;

        System.out.println("Sumatoria de 1 hasta " + n + " = " + s);
        return 0;
    }

    public int fibonachi() { return 0; }

    public int factorial() { return 0; }

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

