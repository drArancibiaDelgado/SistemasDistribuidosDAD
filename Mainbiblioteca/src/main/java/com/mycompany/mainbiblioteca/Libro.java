/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca;

/**
 *
 * @author Animetx
 */
public class Libro extends Publicacion {
      private String autor;
    private String editorial;

    public Libro(String nombre, int año, String autor, String editorial) {
        super(nombre, año);
        this.autor = autor;
        this.editorial = editorial;
    }

    public void mostrar() {
        System.out.println("LIBRO: " + nombre + " (" + año + "), Autor: " + autor + ", Editorial: " + editorial);
    }

    public String getAutor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getPaginas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId_publicacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
