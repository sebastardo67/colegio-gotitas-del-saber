package main.java.edu.jett.colegio.gotitas.repository;

import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;
import main.java.edu.jett.colegio.gotitas.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {

    public void guardar(Curso curso) {

        String sql = """
                INSERT INTO cursos
                (nombre, descripcion, docente, grado)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, curso.getNombre());
            statement.setString(2, curso.getDescripcion());
            statement.setString(3, curso.getDocente());
            statement.setString(4, curso.getGrado());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el curso", e);
        }
    }

    public void actualizar(Curso curso) {

        String sql = """
                UPDATE cursos
                SET nombre = ?,
                    descripcion = ?,
                    docente = ?,
                    grado = ?
                WHERE id_curso = ?
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, curso.getNombre());
            statement.setString(2, curso.getDescripcion());
            statement.setString(3, curso.getDocente());
            statement.setString(4, curso.getGrado());
            statement.setInt(5, curso.getIdCurso());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el curso", e);
        }
    }

    public void eliminar(int idCurso) {

        String sql = "DELETE FROM cursos WHERE id_curso = ?";

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCurso);

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el curso", e);
        }
    }

    public List<Curso> listar() {

        List<Curso> cursos = new ArrayList<>();

        String sql = """
                SELECT id_curso, nombre, descripcion, docente, grado
                FROM cursos
                ORDER BY id_curso
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Curso curso = new Curso(
                        resultSet.getInt("id_curso"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("docente"),
                        resultSet.getString("grado")
                );

                cursos.add(curso);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al listar los cursos", e);
        }

        return cursos;
    }

    public List<Curso> buscar(String texto) {

        List<Curso> cursos = new ArrayList<>();

        String sql = """
                SELECT id_curso, nombre, descripcion, docente, grado
                FROM cursos
                WHERE nombre LIKE ?
                   OR docente LIKE ?
                   OR grado LIKE ?
                ORDER BY id_curso
                """;

        try (Connection connection = ConnectionDb.getconnectionDataBase();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String busqueda = "%" + texto + "%";

            statement.setString(1, busqueda);
            statement.setString(2, busqueda);
            statement.setString(3, busqueda);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Curso curso = new Curso(
                            resultSet.getInt("id_curso"),
                            resultSet.getString("nombre"),
                            resultSet.getString("descripcion"),
                            resultSet.getString("docente"),
                            resultSet.getString("grado")
                    );

                    cursos.add(curso);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar cursos", e);
        }

        return cursos;
    }
}