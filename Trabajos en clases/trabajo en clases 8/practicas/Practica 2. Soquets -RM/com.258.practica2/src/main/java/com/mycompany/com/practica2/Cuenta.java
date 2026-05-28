/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.com.practica2;

import java.io.Serializable;

public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;

    private EBanco banco;
    private String nroCuenta;
    private String ci;
    private String nombres;
    private String apellidos;
    private float saldo;

    public Cuenta() {}

    public Cuenta(EBanco banco, String nroCuenta, String ci, String nombres, String apellidos, float saldo) {
        this.banco = banco;
        this.nroCuenta = nroCuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }

    public EBanco getBanco() { return banco; }
    public void setBanco(EBanco banco) { this.banco = banco; }

    public String getNroCuenta() { return nroCuenta; }
    public void setNroCuenta(String nroCuenta) { this.nroCuenta = nroCuenta; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public float getSaldo() { return saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }

    @Override
    public String toString() {
        return "Banco=" + banco + " | Nro=" + nroCuenta + " | Saldo=" + saldo;
    }
}
