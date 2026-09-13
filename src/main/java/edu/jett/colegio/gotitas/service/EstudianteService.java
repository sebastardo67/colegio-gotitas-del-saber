package main.java.edu.jett.colegio.gotitas.service;

import java.util.List;
import main.java.edu.jett.colegio.gotitas.model.Estudiante;
import main.java.edu.jett.colegio.gotitas.repository.EstudianteRepository;

public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService() {
        this.estudianteRepository = new EstudianteRepository();
    }

    public void guardar(Estudiante estudiante) {

        validar(estudiante);

        estudianteRepository.guardar(estudiante);
    }

    public void actualizar(Estudiante estudiante) {

        if (estudiante == null || estudiante.getIdEstudiante() <= 0) {
            throw new RuntimeException("Seleccione un estudiante válido");
        }

        validar(estudiante);

        estudianteRepository.actualizar(estudiante);
    }

    public void eliminar(int idEstudiante) {

        if (idEstudiante <= 0) {
            throw new RuntimeException("Seleccione un estudiante válido");
        }

        estudianteRepository.eliminar(idEstudiante);
    }

    public List<Estudiante> listar() {
        return estudianteRepository.listar();
    }

    public List<Estudiante> buscar(String texto) {

        if (texto == null || texto.isBlank()) {
            return listar();
        }

        return estudianteRepository.buscar(texto);
    }

    private void validar(Estudiante estudiante) {

        if (estudiante == null) {
            throw new RuntimeException(
                    "El estudiante no puede ser nulo"
            );
        }

        if (estudiante.getNombre() == null
                || estudiante.getNombre().isBlank()) {

            throw new RuntimeException(
                    "El nombre es obligatorio"
            );
        }

        if (estudiante.getApellido() == null
                || estudiante.getApellido().isBlank()) {

            throw new RuntimeException(
                    "El apellido es obligatorio"
            );
        }

        if (estudiante.getGrado() == null
                || estudiante.getGrado().isBlank()) {

            throw new RuntimeException(
                    "El grado es obligatorio"
            );
        }
    }
}