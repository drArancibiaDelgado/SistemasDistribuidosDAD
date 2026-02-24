/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca;

/**
 *
 * @author Animetx
 */
public abstract class Publicacion {
    protected String nombre;
    protected int año;

    public Publicacion(String nombre, int año) {
        this.nombre = nombre;
        this.año = año;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void mostrar();

    public int getAnio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
