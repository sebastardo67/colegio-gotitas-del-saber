package main.java.edu.jett.colegio.gotitas.repository;

import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;
import main.java.edu.jett.colegio.gotitas.model.Estudiante;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository {

    public void guardar(Estudiante estudiante) {

        String sql = """
                INSERT INTO estudiantes
                (nombre, apellido, fecha_nacimiento, grado, seccion, telefono)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, estudiante.getNombre());
            statement.setString(2, estudiante.getApellido());

            if (estudiante.getFechaNacimiento() != null) {
                statement.setDate(
                        3,
                        Date.valueOf(estudiante.getFechaNacimiento())
                );
            } else {
                statement.setNull(3, java.sql.Types.DATE);
            }

            statement.setString(4, estudiante.getGrado());
            statement.setString(5, estudiante.getSeccion());
            statement.setString(6, estudiante.getTelefono());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al guardar el estudiante", e
            );
        }
    }

    public void actualizar(Estudiante estudiante) {

        String sql = """
                UPDATE estudiantes
                SET nombre = ?,
                    apellido = ?,
                    fecha_nacimiento = ?,
                    grado = ?,
                    seccion = ?,
                    telefono = ?
                WHERE id_estudiante = ?
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, estudiante.getNombre());
            statement.setString(2, estudiante.getApellido());

            if (estudiante.getFechaNacimiento() != null) {
                statement.setDate(
                        3,
                        Date.valueOf(estudiante.getFechaNacimiento())
                );
            } else {
                statement.setNull(3, java.sql.Types.DATE);
            }

            statement.setString(4, estudiante.getGrado());
            statement.setString(5, estudiante.getSeccion());
            statement.setString(6, estudiante.getTelefono());
            statement.setInt(7, estudiante.getIdEstudiante());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al actualizar el estudiante", e
            );
        }
    }

    public void eliminar(int idEstudiante) {

        String sql = """
                DELETE FROM estudiantes
                WHERE id_estudiante = ?
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idEstudiante);
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al eliminar el estudiante", e
            );
        }
    }

    public List<Estudiante> listar() {

        List<Estudiante> estudiantes = new ArrayList<>();

        String sql = """
                SELECT id_estudiante,
                       nombre,
                       apellido,
                       fecha_nacimiento,
                       grado,
                       seccion,
                       telefono
                FROM estudiantes
                ORDER BY id_estudiante
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Date fecha = resultSet.getDate("fecha_nacimiento");

                Estudiante estudiante = new Estudiante(
                        resultSet.getInt("id_estudiante"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        fecha != null ? fecha.toLocalDate() : null,
                        resultSet.getString("grado"),
                        resultSet.getString("seccion"),
                        resultSet.getString("telefono")
                );

                estudiantes.add(estudiante);
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al listar los estudiantes", e
            );
        }

        return estudiantes;
    }

    public List<Estudiante> buscar(String texto) {

        List<Estudiante> estudiantes = new ArrayList<>();

        String sql = """
                SELECT id_estudiante,
                       nombre,
                       apellido,
                       fecha_nacimiento,
                       grado,
                       seccion,
                       telefono
                FROM estudiantes
                WHERE nombre LIKE ?
                   OR apellido LIKE ?
                   OR grado LIKE ?
                   OR seccion LIKE ?
                ORDER BY id_estudiante
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String busqueda = "%" + texto + "%";

            statement.setString(1, busqueda);
            statement.setString(2, busqueda);
            statement.setString(3, busqueda);
            statement.setString(4, busqueda);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Date fecha = resultSet.getDate("fecha_nacimiento");

                    Estudiante estudiante = new Estudiante(
                            resultSet.getInt("id_estudiante"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            fecha != null ? fecha.toLocalDate() : null,
                            resultSet.getString("grado"),
                            resultSet.getString("seccion"),
                            resultSet.getString("telefono")
                    );

                    estudiantes.add(estudiante);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al buscar estudiantes", e
            );
        }

        return estudiantes;
    }
}