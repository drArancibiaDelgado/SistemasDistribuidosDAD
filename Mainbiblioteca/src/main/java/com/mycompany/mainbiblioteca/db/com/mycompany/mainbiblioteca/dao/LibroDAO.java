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
import com.mycompany.mainbiblioteca.Libro;
import java.sql.*;

public class LibroDAO {

    public void insertarLibro(Libro l) {
        String sql = "INSERT INTO libro (id_publicacion, autor) VALUES (?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, l.getId_publicacion());
            ps.setString(2, l.getAutor());

            ps.executeUpdate();
            System.out.println("✔ Libro insertado correctamente");

        } catch (SQLException e) {
            System.out.println("✘ Error insertando libro: " + e.getMessage());
        }
    }
}