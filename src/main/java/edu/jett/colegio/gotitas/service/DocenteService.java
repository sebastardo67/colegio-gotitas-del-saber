package main.java.edu.jett.colegio.gotitas.service;

import java.sql.SQLException;
import java.util.List;

import main.java.edu.jett.colegio.gotitas.model.Docente;
import main.java.edu.jett.colegio.gotitas.repository.DocenteRepository;

public class DocenteService {

    private final DocenteRepository docenteRepository;

    public DocenteService() {
        this.docenteRepository = new DocenteRepository();
    }

  
    public List<Docente> listarDocentes() throws SQLException {
        return docenteRepository.findAll();
    }

    
    public Docente buscarDocente(int idDocente) throws SQLException {
        return docenteRepository.findById(idDocente);
    }

    
    public boolean agregarDocente(Docente docente) throws SQLException {
        return docenteRepository.insert(docente);
    }

    
    public boolean actualizarDocente(Docente docente) throws SQLException {
        return docenteRepository.update(docente);
    }

    
    public boolean eliminarDocente(int idDocente) throws SQLException {
        return docenteRepository.delete(idDocente);
    }
}