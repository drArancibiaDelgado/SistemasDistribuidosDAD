/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_votacion;

import java.io.Serializable;

public class MensajeVotacion implements Serializable {

    public enum Tipo {
        PREGUNTA,
        VOTO,
        RESULTADOS
    }

    private Tipo tipo;
    private String contenido;
    private String[] opciones;
    private String remitenteNombre;

    public MensajeVotacion(Tipo tipo, String contenido, String remitenteNombre) {
        this.tipo = tipo;
        this.contenido = contenido;
        this.remitenteNombre = remitenteNombre;
    }

    public Tipo getTipo() { return tipo; }
    public String getContenido() { return contenido; }
    public String[] getOpciones() { return opciones; }
    public String getRemitenteNombre() { return remitenteNombre; }
    public void setOpciones(String[] opciones) { this.opciones = opciones; }
}