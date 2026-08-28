
/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package main.java.edu.jett.colegio.gotitas.service;
 
import javafx.collections.ObservableList;
import main.java.edu.jett.colegio.gotitas.model.Estudiante;
import main.java.edu.jett.colegio.gotitas.repository.EstudianteRepository;
 
 
/**
*
* @author informatica
*/
public class DashboardService {
    private EstudianteRepository estudianteRepository;
    public DashboardService(EstudianteRepository estudianteRepository){
        this.estudianteRepository = estudianteRepository;
}
 
    public ObservableList<Estudiante> listStudent(){
        if(estudianteRepository.findAll() == null ){
            throw new RuntimeException("Sin datos que mostrar");
        }else{
                    return estudianteRepository.findAll();
                    }
        }
    }