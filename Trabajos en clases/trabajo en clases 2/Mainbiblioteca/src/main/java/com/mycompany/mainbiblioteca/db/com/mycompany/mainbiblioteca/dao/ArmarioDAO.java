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
import com.mycompany.mainbiblioteca.Armario;
import java.sql.*;

public class ArmarioDAO {

    public void insertarArmario(Armario ar) {
        String sql = "INSERT INTO armario (codigo, ubicacion, id_biblioteca) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ar.getCodigo());      // CORREGIDO
            ps.setString(2, ar.getUbicacion());
            ps.setInt(3, ar.getIdBiblioteca());

            ps.executeUpdate();
            System.out.println("✔ Armario guardado en la BD");

        } catch (SQLException e) {
            System.out.println("✘ Error insertando armario: " + e.getMessage());
        }
    }
}