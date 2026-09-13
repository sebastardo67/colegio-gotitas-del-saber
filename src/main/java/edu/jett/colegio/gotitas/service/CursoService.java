/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.service;


import java.util.List;
import main.java.edu.jett.colegio.gotitas.model.Curso;
import main.java.edu.jett.colegio.gotitas.repository.CursoRepository;

public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService() {
        this.cursoRepository = new CursoRepository();
    }

    public void guardar(Curso curso) {

        if (curso == null) {
            throw new RuntimeException("El curso no puede ser nulo");
        }

        if (curso.getNombre() == null || curso.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del curso es obligatorio");
        }

        if (curso.getGrado() == null || curso.getGrado().isBlank()) {
            throw new RuntimeException("El grado es obligatorio");
        }

        cursoRepository.guardar(curso);
    }

    public void actualizar(Curso curso) {

        if (curso == null) {
            throw new RuntimeException("El curso no puede ser nulo");
        }

        if (curso.getIdCurso() <= 0) {
            throw new RuntimeException("Seleccione un curso válido");
        }

        if (curso.getNombre() == null || curso.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del curso es obligatorio");
        }

        if (curso.getGrado() == null || curso.getGrado().isBlank()) {
            throw new RuntimeException("El grado es obligatorio");
        }

        cursoRepository.actualizar(curso);
    }

    public void eliminar(int idCurso) {

        if (idCurso <= 0) {
            throw new RuntimeException("Seleccione un curso válido");
        }

        cursoRepository.eliminar(idCurso);
    }

    public List<Curso> listar() {
        return cursoRepository.listar();
    }

    public List<Curso> buscar(String texto) {

        if (texto == null || texto.isBlank()) {
            return listar();
        }

        return cursoRepository.buscar(texto);
    }
}
