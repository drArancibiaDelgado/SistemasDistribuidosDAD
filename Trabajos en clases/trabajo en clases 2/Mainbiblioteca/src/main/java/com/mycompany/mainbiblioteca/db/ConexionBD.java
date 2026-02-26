package com.mycompany.mainbiblioteca.db;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Animetx
 */





import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliotecadb";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection conectar() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✔ Conexión establecida");
            return con;
        } catch (Exception e) {
            System.out.println("✘ Error conectando: " + e.getMessage());
            return null;
        }
    }
}