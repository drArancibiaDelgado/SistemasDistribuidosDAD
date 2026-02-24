/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca;

import java.util.ArrayList;

public abstract class Armario {

    protected int codigo;
    protected ArrayList<Publicacion> publicaciones = new ArrayList<>();

    public Armario(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public abstract int getIdBiblioteca();
    public abstract String getUbicacion();
}