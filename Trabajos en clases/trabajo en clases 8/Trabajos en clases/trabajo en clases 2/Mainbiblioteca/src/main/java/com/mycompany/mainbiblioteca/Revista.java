/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca;

/**
 *
 * @author Animetx
 */
public class Revista extends Publicacion {
     private String mes;
    private String tipo;

    public Revista(String nombre, int año, String mes, String tipo) {
        super(nombre, año);
        this.mes = mes;
        this.tipo = tipo;
    }
    
    public void mostrar() {
        System.out.println("REVISTA: " + nombre + " - " + mes + " (" + año + "), Tipo: " + tipo);
    }

    public int getId_publicacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getPeriodicidad() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
