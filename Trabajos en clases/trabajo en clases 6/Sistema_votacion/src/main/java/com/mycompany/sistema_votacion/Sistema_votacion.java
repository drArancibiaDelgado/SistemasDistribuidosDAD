/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistema_votacion;

/**
 *
 * @author Animetx
 */
import java.util.Scanner;

public class Sistema_votacion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   SISTEMA DE VOTACIÓN EN RED (JGroups) ");
        System.out.println("========================================");
        System.out.print("Nombre de este nodo: ");
        String nombre = sc.nextLine().trim();

        System.out.print("¿Es este nodo el INICIADOR? (s/n): ");
        boolean esIniciador = sc.nextLine().trim().equalsIgnoreCase("s");

        NodoVotacion nodo = null;
        try {
            nodo = new NodoVotacion(nombre, esIniciador);
            nodo.iniciar();
        } catch (Exception e) {
            System.err.println("Error fatal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (nodo != null) nodo.cerrar();
            System.out.println("Nodo cerrado.");
        }
    }
}
