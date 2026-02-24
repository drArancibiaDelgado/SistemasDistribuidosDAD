/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainbiblioteca.db.com.mycompany.mainbiblioteca.dao;

import com.mycompany.mainbiblioteca.db.ConexionBD;
import java.sql.*;

public class ArmarioPublicacionDAO {

    public void insertarRelacion(String codigoArmario, int idPublicacion) {
        String sql = "INSERT INTO armario_publicacion (id_armario, id_publicacion) VALUES (?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigoArmario);
            ps.setInt(2, idPublicacion);

            ps.executeUpdate();
            System.out.println("✔ Publicación asociada al armario correctamente");

        } catch (SQLException e) {
            System.out.println("✘ Error insertando relación armario-publicación: " + e.getMessage());
        }
    }
}