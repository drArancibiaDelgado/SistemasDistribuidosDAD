/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca;

public class ArmarioMadera extends Armario {

    public ArmarioMadera(int codigo) {
        super(codigo);
    }

    @Override
    public int getIdBiblioteca() {
        return 0; // o lo que quieras manejar luego
    }

    @Override
    public String getUbicacion() {
        return "";
    }
}