/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca.db.com.mycompany.mainbiblioteca.dao;

/**
 *
 * @author Animetx
 */

import com.mycompany.mainbiblioteca.db.ConexionBD;
import com.mycompany.mainbiblioteca.Biblioteca;
import java.sql.*;
import java.util.ArrayList;

public class BibliotecaDAO {

    public void insertarBiblioteca(Biblioteca b) {
        String sql = "INSERT INTO biblioteca (nombre, metros_cuadrados) VALUES (?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.getNombre());
            ps.setDouble(2, b.getMetrosCuadrados());
            ps.executeUpdate();

            System.out.println("✔ Biblioteca guardada en la BD");

        } catch (SQLException e) {
            System.out.println("✘ Error insertando biblioteca: " + e.getMessage());
        }
    }
}