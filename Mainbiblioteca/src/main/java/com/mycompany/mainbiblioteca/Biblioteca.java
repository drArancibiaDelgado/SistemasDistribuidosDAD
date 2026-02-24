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
public class Biblioteca {
    private int id;
    private String nombre;
    private double metrosCuadrados;

    private ArrayList<Armario> armarios = new ArrayList<>();
    private ArrayList<Publicacion> publicacionesTemporales = new ArrayList<>();

    public Biblioteca(int id, String nombre, double metrosCuadrados) {
        this.id = id;
        this.nombre = nombre;
        this.metrosCuadrados = metrosCuadrados;
    }

    public Biblioteca(String nombre, double metrosCuadrados) {
        this.nombre = nombre;
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getMetrosCuadrados() { return metrosCuadrados; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMetrosCuadrados(double m) { this.metrosCuadrados = m; }

    public void agregarArmario(Armario a) {
        armarios.add(a);
        System.out.println("Armario agregado correctamente.");
    }

    void agregarPublicacionTemporal(Publicacion pub) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void mostrarPublicacionesTemporales() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void cargarPublicacionEnArmario(int code, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void listarBiblioteca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}