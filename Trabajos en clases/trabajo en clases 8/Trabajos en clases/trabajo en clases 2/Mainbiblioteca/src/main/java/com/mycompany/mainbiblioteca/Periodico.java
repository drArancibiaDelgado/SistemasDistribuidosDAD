/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca;

import java.util.ArrayList;

/**
 *
 * @author Animetx
 */
public class Periodico extends Publicacion {
     private String fecha;
    private ArrayList<Suplemento> suplementos = new ArrayList<>();

    public Periodico(String nombre, String fecha) {
        super(nombre, 0);
        this.fecha = fecha;
    }

    public void agregarSuplemento(Suplemento s) {
        suplementos.add(s);
    }

    public void mostrar() {
        System.out.println("PERIÓDICO: " + nombre + " - Fecha: " + fecha);
        System.out.println("Suplementos:");
        for (Suplemento s : suplementos) {
            System.out.println("  - " + s.getTipo());
        }
    }
    
}
