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
import com.mycompany.mainbiblioteca.Revista;
import java.sql.*;

public class RevistaDAO {

    public void insertarRevista(Revista r) {
        String sql = "INSERT INTO revista (id_publicacion, periodicidad) VALUES (?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getId_publicacion());
            ps.setString(2, r.getPeriodicidad());

            ps.executeUpdate();
            System.out.println("✔ Revista insertada correctamente");

        } catch (SQLException e) {
            System.out.println("✘ Error insertando revista: " + e.getMessage());
        }
    }
}