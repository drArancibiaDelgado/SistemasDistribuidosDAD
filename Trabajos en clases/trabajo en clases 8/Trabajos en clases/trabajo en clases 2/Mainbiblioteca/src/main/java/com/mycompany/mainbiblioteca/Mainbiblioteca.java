/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainbiblioteca;

import com.mycompany.mainbiblioteca.db.com.mycompany.mainbiblioteca.dao.ArmarioDAO;
import com.mycompany.mainbiblioteca.db.com.mycompany.mainbiblioteca.dao.PublicacionDAO;

import java.util.Scanner;

public class Mainbiblioteca {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", 350);

        ArmarioDAO armarioDAO = new ArmarioDAO();
        PublicacionDAO publicacionDAO = new PublicacionDAO();

        int op;

        do {
            System.out.println("\n--- MENU BIBLIOTECA ---");
            System.out.println("1. Crear armario");
            System.out.println("2. Añadir armario a la biblioteca");
            System.out.println("3. Crear publicación");
            System.out.println("4. Cargar publicación en un armario");
            System.out.println("5. Listar biblioteca");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {

                case 1 -> {
                    Armario arm = crearArmario(sc);

                    // agregar a la biblioteca
                    biblioteca.agregarArmario(arm);

                    // insertar en BD
                    armarioDAO.insertarArmario(arm);
                    System.out.println("Armario guardado en base de datos.");
                }

                case 2 ->
                    System.out.println("Los armarios se agregan automáticamente en la opción 1.");

                case 3 -> {
                    Publicacion pub = crearPublicacion(sc);

                    // guardar temporal en la memoria de la biblioteca
                    biblioteca.agregarPublicacionTemporal(pub);

                    // guardar en BD
                    if (pub instanceof Libro libro) {
                        publicacionDAO.insertarLibro(libro);

                    } else if (pub instanceof Revista revista) {
                        publicacionDAO.insertarRevista(revista);

                    } else if (pub instanceof Periodico periodico) {
                        publicacionDAO.insertarPeriodico(periodico);
                    }

                    System.out.println("Publicación guardada en base de datos.");
                }

                case 4 -> {
                    System.out.println("Ingrese código del armario:");
                    int code = sc.nextInt();
                    System.out.println("Seleccione publicación temporal a cargar:");
                    biblioteca.mostrarPublicacionesTemporales();
                    int index = sc.nextInt();
                    biblioteca.cargarPublicacionEnArmario(code, index);
                }

                case 5 ->
                    biblioteca.listarBiblioteca();

                case 6 ->
                    System.out.println("Saliendo...");

                default ->
                    System.out.println("Opción inválida");
            }

        } while (op != 6);

    }

    public static Armario crearArmario(Scanner sc) {
        System.out.println("Código del armario:");
        int code = sc.nextInt();

        System.out.println("Tipo de armario: 1 = Madera, 2 = Metálico");
        int tipo = sc.nextInt();

        if (tipo == 1) {
            return new ArmarioMadera(code);
        } else {
            return new ArmarioMetalico(code);
        }
    }

    public static Publicacion crearPublicacion(Scanner sc) {
        System.out.println("1. Libro 2. Revista 3. Periódico");
        int tipo = sc.nextInt();

        sc.nextLine(); // limpiar buffer

        switch (tipo) {

            case 1 -> {
                System.out.println("Nombre:");
                String nom = sc.nextLine();
                System.out.println("Autor:");
                String autor = sc.nextLine();
                System.out.println("Editorial:");
                String edit = sc.nextLine();
                System.out.println("Año:");
                int year = sc.nextInt();
                return new Libro(nom, year, autor, edit);
            }

            case 2 -> {
                System.out.println("Nombre:");
                String nom = sc.nextLine();
                System.out.println("Mes:");
                String mes = sc.nextLine();
                System.out.println("Año:");
                int year = sc.nextInt();
                sc.nextLine();
                System.out.println("Tipo (tecnica / moda / variedades):");
                String tipoR = sc.nextLine();
                return new Revista(nom, year, mes, tipoR);
            }

            case 3 -> {
                System.out.println("Nombre:");
                String nom = sc.nextLine();
                System.out.println("Fecha:");
                String fecha = sc.nextLine();

                Periodico p = new Periodico(nom, fecha);

                System.out.println("¿Cuántos suplementos?");
                int cant = sc.nextInt();
                sc.nextLine();

                for (int i = 0; i < cant; i++) {
                    System.out.println("Suplemento " + (i + 1) + ": revista / crucigrama / afiche");
                    String tipoS = sc.nextLine();
                    p.agregarSuplemento(new Suplemento(tipoS));
                }
                return p;
            }
        }

        return null;
    }
}