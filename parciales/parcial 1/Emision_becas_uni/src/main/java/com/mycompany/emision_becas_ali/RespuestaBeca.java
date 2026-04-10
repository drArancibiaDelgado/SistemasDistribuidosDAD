/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emision_becas_ali;

/**
 *
 * @author USUARIO
 */
public class RespuestaBeca implements java.io.Serializable {
    public boolean aprobado;
    public String motivo;
    public double promedio;
    public RespuestaBeca(boolean a, String m, double p) { 
        aprobado = a; motivo = m; promedio = p; 
    }
    public String toString() {
        return "Aprobado: " + aprobado + " | " + motivo + " | Promedio: " + promedio;
    }
}