/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;

import main.java.edu.jett.colegio.gotitas.model.Docente;

public class DocenteRepository {

    public Docente findById(int idDocente) throws SQLException {

        String sql = "SELECT * FROM docentes WHERE id_docente = ?";

        try (PreparedStatement pstm =
                ConnectionDb.getconnectionDataBase().prepareStatement(sql)) {

            pstm.setInt(1, idDocente);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                Docente docente = new Docente();

                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setEmail(rs.getString("email"));

                return docente;
            }
        }

        return null;
    }
}
