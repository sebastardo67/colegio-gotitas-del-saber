package main.java.edu.jett.colegio.gotitas.service;

import java.util.UUID;

import javafx.collections.ObservableList;

import main.java.edu.jett.colegio.gotitas.model.Estudiante;
import main.java.edu.jett.colegio.gotitas.repository.EstudianteRepository;

public class DashboardService {

    private final EstudianteRepository estudianteRepository;

    public DashboardService(
            EstudianteRepository estudianteRepository) {

        this.estudianteRepository = estudianteRepository;
    }


    // =========================================================
    // LISTAR
    // =========================================================

    public ObservableList<Estudiante> listStudent() {

        return estudianteRepository.findAll();
    }


    // =========================================================
    // AGREGAR
    // =========================================================

    public void addStudent(Estudiante estudiante) {

        String uuid =
                UUID.randomUUID().toString();

        estudiante.setIdEstudiante(uuid);

        estudianteRepository.save(estudiante);
    }


    // =========================================================
    // MODIFICAR
    // =========================================================

    public void updateStudent(Estudiante estudiante) {

        estudianteRepository.update(estudiante);
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    public void deleteStudent(String idEstudiante) {

        estudianteRepository.delete(idEstudiante);
    }
}   