/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.com.practica2;

import java.io.Serializable;
import java.util.ArrayList;

public class RespuestaCuenta implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Cuenta> cuentas = new ArrayList<>();
    private String mensaje = "";

    public ArrayList<Cuenta> getCuentas() { return cuentas; }
    public void setCuentas(ArrayList<Cuenta> cuentas) { this.cuentas = cuentas; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
