/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca;

public class ArmarioMetalico extends Armario {

    public ArmarioMetalico(int codigo) {
        super(codigo);
    }

    @Override
    public int getIdBiblioteca() {
        return 0;
    }

    @Override
    public String getUbicacion() {
        return "";
    }
}