/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emision_becas_ali;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */

public class Nota implements Serializable {
    public String materia;
    public int calificacion;
    
    public Nota(String materia, int calificacion) {
        this.materia = materia;
        this.calificacion = calificacion;
    }
}
