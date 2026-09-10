package main.java.edu.jett.colegio.gotitas.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;
import main.java.edu.jett.colegio.gotitas.model.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudianteRepository {

    // =========================================================
    // LISTAR
    // =========================================================

    public ObservableList<Estudiante> findAll() {

        String sql = """
            SELECT DISTINCT
                e.id_estudiante,
                e.nombre AS nombre_estudiante,
                e.apellido AS apellido_estudiante,
                e.correo_electronico,
                s.nombre_seccion,
                c.nombre_curso,
                d.nombre AS nombre_docente,
                d.apellido AS apellido_docente

            FROM estudiantes e

            LEFT JOIN matriculas m
                ON m.id_estudiante = e.id_estudiante

            LEFT JOIN asignacion_cursos ac
                ON ac.id_matricula = m.id_matricula

            LEFT JOIN secciones s
                ON s.id_seccion = ac.id_seccion

            LEFT JOIN cursos c
                ON c.id_curso = ac.id_curso

            LEFT JOIN docentes d
                ON d.id_docente = ac.id_docente

            ORDER BY e.apellido, e.nombre
            """;

        ObservableList<Estudiante> lista =
                FXCollections.observableArrayList();

        try (
            Connection connection =
                    ConnectionDb.getconnectionDataBase();

            PreparedStatement pstm =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    pstm.executeQuery()
        ) {

            while (rs.next()) {

                Estudiante estudiante = new Estudiante(

                        rs.getString("id_estudiante"),

                        rs.getString("nombre_estudiante"),

                        rs.getString("apellido_estudiante"),

                        rs.getString("correo_electronico"),

                        rs.getString("nombre_seccion"),

                        rs.getString("nombre_curso"),

                        rs.getString("nombre_docente"),

                        rs.getString("apellido_docente")
                );

                lista.add(estudiante);
            }

            return lista;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al consultar estudiantes: "
                    + e.getMessage(), e
            );
        }
    }


    // =========================================================
    // INSERTAR
    // =========================================================

    public void save(Estudiante estudiante) {

        String sql = """
            INSERT INTO estudiantes
            (
                id_estudiante,
                id_ciudad,
                nombre,
                apellido,
                correo_electronico,
                fecha_nacimiento
            )
            VALUES
            (?, NULL, ?, ?, ?, NULL)
            """;

        try (
            Connection connection =
                    ConnectionDb.getconnectionDataBase();

            PreparedStatement pstm =
                    connection.prepareStatement(sql)
        ) {

            pstm.setString(
                    1,
                    estudiante.getIdEstudiante()
            );

            pstm.setString(
                    2,
                    estudiante.getNombre()
            );

            pstm.setString(
                    3,
                    estudiante.getApellido()
            );

            pstm.setString(
                    4,
                    estudiante.getCorreoElectronico()
            );

            pstm.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al insertar estudiante: "
                    + e.getMessage(), e
            );
        }
    }


    // =========================================================
    // ACTUALIZAR
    // =========================================================

    public void update(Estudiante estudiante) {

        String sql = """
            UPDATE estudiantes
            SET
                nombre = ?,
                apellido = ?,
                correo_electronico = ?
            WHERE id_estudiante = ?
            """;

        try (
            Connection connection =
                    ConnectionDb.getconnectionDataBase();

            PreparedStatement pstm =
                    connection.prepareStatement(sql)
        ) {

            pstm.setString(
                    1,
                    estudiante.getNombre()
            );

            pstm.setString(
                    2,
                    estudiante.getApellido()
            );

            pstm.setString(
                    3,
                    estudiante.getCorreoElectronico()
            );

            pstm.setString(
                    4,
                    estudiante.getIdEstudiante()
            );

            int filas =
                    pstm.executeUpdate();

            if (filas == 0) {

                throw new RuntimeException(
                        "No se encontró el estudiante con ID: "
                        + estudiante.getIdEstudiante()
                );
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al actualizar estudiante: "
                    + e.getMessage(), e
            );
        }
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    public void delete(String idEstudiante) {

        Connection connection = null;

        try {

            connection =
                    ConnectionDb.getconnectionDataBase();

            connection.setAutoCommit(false);


            // -----------------------------------------------
            // 1. Eliminar asignaciones de cursos
            // -----------------------------------------------

            String sqlAsignacion = """
                DELETE FROM asignacion_cursos
                WHERE id_matricula IN
                (
                    SELECT id_matricula
                    FROM matriculas
                    WHERE id_estudiante = ?
                )
                """;

            try (
                PreparedStatement pstm =
                        connection.prepareStatement(sqlAsignacion)
            ) {

                pstm.setString(1, idEstudiante);
                pstm.executeUpdate();
            }


            // -----------------------------------------------
            // 2. Eliminar matrícula
            // -----------------------------------------------

            String sqlMatricula = """
                DELETE FROM matriculas
                WHERE id_estudiante = ?
                """;

            try (
                PreparedStatement pstm =
                        connection.prepareStatement(sqlMatricula)
            ) {

                pstm.setString(1, idEstudiante);
                pstm.executeUpdate();
            }


            // -----------------------------------------------
            // 3. Eliminar estudiante
            // -----------------------------------------------

            String sqlEstudiante = """
                DELETE FROM estudiantes
                WHERE id_estudiante = ?
                """;

            try (
                PreparedStatement pstm =
                        connection.prepareStatement(sqlEstudiante)
            ) {

                pstm.setString(1, idEstudiante);

                int filas =
                        pstm.executeUpdate();

                if (filas == 0) {

                    throw new RuntimeException(
                            "No se encontró el estudiante con ID: "
                            + idEstudiante
                    );
                }
            }


            // -----------------------------------------------
            // Confirmar transacción
            // -----------------------------------------------

            connection.commit();

        } catch (SQLException | RuntimeException e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            throw new RuntimeException(
                    "Error al eliminar estudiante: "
                    + e.getMessage(), e
            );

        } finally {

            if (connection != null) {

                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
