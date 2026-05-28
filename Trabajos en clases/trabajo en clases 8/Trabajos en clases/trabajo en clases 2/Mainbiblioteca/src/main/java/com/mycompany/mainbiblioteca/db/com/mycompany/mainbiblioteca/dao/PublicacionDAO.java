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
import com.mycompany.mainbiblioteca.*;

import java.sql.*;

public class PublicacionDAO {

    public int insertarPublicacion(Publicacion p) {

        String sql = "INSERT INTO publicacion (nombre, anio, tipo) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getAnio());
            ps.setString(3, p.getTipo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("✘ Error insertando publicación: " + e.getMessage());
        }
        return -1;
    }

    public void insertarLibro(Libro l) {
        int id = insertarPublicacion(l);
        if (id == -1) return;

        String sql = "INSERT INTO libro (id_publicacion, autor, paginas) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, l.getAutor());
            ps.setInt(3, l.getPaginas());

            ps.executeUpdate();
            System.out.println("✔ Libro guardado en BD");

        } catch (SQLException e) {
            System.out.println("✘ Error insertando libro: " + e.getMessage());
        }
    }

    // Puedes agregar revista y periódico igual que libro

    public void insertarRevista(Revista revista) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void insertarPeriodico(Periodico periodico) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}