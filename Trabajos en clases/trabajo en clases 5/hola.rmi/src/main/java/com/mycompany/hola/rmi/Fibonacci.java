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
public class Fibonacci implements ICalcular {

    int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    public int fibonachi() {
        int t1 = 0, t2 = 1, st;

        System.out.println("Serie Fibonacci de " + n + " términos:");

        for (int i = 1; i <= n; i++) {
            System.out.print(t1 + (i == n ? "" : ", "));
            st = t1 + t2;
            t1 = t2;
            t2 = st;
        }
        System.out.println();
        return 0;
    }

    public int factorial() { return 0; }

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