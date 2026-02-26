/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.figuras;

import java.util.ArrayList;
import java.util.Scanner;

public class Figuras {

    public static void main(String[] args) {
        ArrayList<IFigura> listaFiguras = new ArrayList<>();
        Scanner leer = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar figura");
            System.out.println("2. Calcular area total");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    agregar(listaFiguras, leer);
                    break;
                case 2:
                    calcular(listaFiguras);
                    break;
                case 3:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 3);
    }

    public static void agregar(ArrayList<IFigura> lista, Scanner sc) {
        System.out.println("1.Cuadrado 2.Rectangulo 3.Triangulo 4.Circulo");
        int tipo = sc.nextInt();

        if (tipo == 1) {
            System.out.print("Lado: ");
            lista.add(new Cuadrado(sc.nextInt()));
        } else if (tipo == 2) {
            System.out.print("Base: ");
            int b = sc.nextInt();
            System.out.print("Altura: ");
            lista.add(new Rectangulo(b, sc.nextInt()));
        } else if (tipo == 3) {
            System.out.print("Base: ");
            int b = sc.nextInt();
            System.out.print("Altura: ");
            lista.add(new Triangulo(b, sc.nextInt()));
        } else if (tipo == 4) {
            System.out.print("Radio: ");
            lista.add(new Circulo(sc.nextInt()));
        }
    }

    public static void calcular(ArrayList<IFigura> lista) {
        int total = 0;
        for (IFigura f : lista) {
            System.out.println(f.getClass().getSimpleName() + " = " + f.area());
            total += f.area();
        }
        System.out.println("Area total: " + total);
    }
}
