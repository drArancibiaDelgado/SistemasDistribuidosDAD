/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.figuras;

/**
 *
 * @author Animetx
 */
public class Circulo implements IFigura {


    float PI =(float) 3.14;

    public float getPI() {
        return PI;
    }

    public float getRadio() {
        return Radio;
    }

    public void setPI(float PI) {
        this.PI = PI;
    }

    public void setRadio(float Radio) {
        this.Radio = Radio;
    }

    public Circulo(float Radio) {
        this.Radio = Radio;
    }
    float Radio;
    
    
    
    public int area() {
        return (int) (PI*(Radio*Radio));
    }

    public int perimetro() {
        return (int) (2*PI*Radio);

    }
    
}
