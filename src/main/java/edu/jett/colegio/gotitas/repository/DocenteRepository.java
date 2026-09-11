package main.java.edu.jett.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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



    public List<Docente> findAll() throws SQLException {

        List<Docente> docentes = new ArrayList<>();

        String sql = "SELECT * FROM docentes";

        try (PreparedStatement pstm =
                ConnectionDb.getconnectionDataBase().prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {

                Docente docente = new Docente();

                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setEmail(rs.getString("email"));

                docentes.add(docente);
            }
        }

        return docentes;
    }



    public boolean insert(Docente docente) throws SQLException {

        String sql = "INSERT INTO docentes "
                + "(id_docente, nombre, apellido, email) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstm =
                ConnectionDb.getconnectionDataBase().prepareStatement(sql)) {

            pstm.setInt(1, docente.getIdDocente());
            pstm.setString(2, docente.getNombre());
            pstm.setString(3, docente.getApellido());
            pstm.setString(4, docente.getEmail());

            return pstm.executeUpdate() > 0;
        }
    }


    
    public boolean update(Docente docente) throws SQLException {

        String sql = "UPDATE docentes "
                + "SET nombre = ?, apellido = ?, email = ? "
                + "WHERE id_docente = ?";

        try (PreparedStatement pstm =
                ConnectionDb.getconnectionDataBase().prepareStatement(sql)) {

            pstm.setString(1, docente.getNombre());
            pstm.setString(2, docente.getApellido());
            pstm.setString(3, docente.getEmail());
            pstm.setInt(4, docente.getIdDocente());

            return pstm.executeUpdate() > 0;
        }
    }


    
    public boolean delete(int idDocente) throws SQLException {

        String sql = "DELETE FROM docentes WHERE id_docente = ?";

        try (PreparedStatement pstm =
                ConnectionDb.getconnectionDataBase().prepareStatement(sql)) {

            pstm.setInt(1, idDocente);

            return pstm.executeUpdate() > 0;
        }
    }
}