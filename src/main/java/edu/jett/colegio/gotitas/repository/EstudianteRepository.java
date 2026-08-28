/*

* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license

* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

*/

package main.java.edu.jett.colegio.gotitas.repository;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import javafx.collections.FXCollections;

import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;

import main.java.edu.jett.colegio.gotitas.model.Estudiante;
 
    

public class EstudianteRepository {

    public ObservableList<Estudiante> findAll() {

        String sql = "select \n" +

"e.id_estudiante,\n" +

"e.nombre, \n" +

"e.apellido, \n" +

"e.correo_electronico,\n" +

"s.nombre_seccion,\n" +

"d.nombre,\n" +

"d.apellido,\n" +

"c.nombre_curso\n" +

"from asignacion_cursos as ac\n" +

"inner join matriculas as m\n" +

"on m.id_matricula = ac.id_matricula\n" +

"inner join secciones as s\n" +

"on s.id_seccion = ac.id_seccion\n" +

"inner join cursos as c\n" +

"on c.id_curso = ac.id_curso\n" +

"inner join docentes as d\n" +

"on d.id_docente = ac.id_docente\n" +

"inner join estudiantes as e\n" +

"on e.id_estudiante = m.id_estudiante;";

                try(PreparedStatement pstm = ConnectionDb.getconnectionDataBase().prepareStatement(sql)){

                ResultSet rs = pstm.executeQuery(); 

                ObservableList<Estudiante> studentList = FXCollections.observableArrayList();

                while(rs.next()){

                studentList.add(new Estudiante(

                rs.getString("id_estudiante"),

                rs.getString("nombre"),

                rs.getString("apellido"),

                rs.getString("correo_electronico"),

                rs.getString("nombre_seccion"),

                rs.getString("nombre_curso"),

                rs.getString("nombre"),

                rs.getString("apellido")

                )  );

                }

                return studentList;

    }catch(SQLException e) {

        throw new RuntimeException("error en la consulta: " + e.getMessage());

    }

    }

}

 