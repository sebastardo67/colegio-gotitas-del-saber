package main.java.edu.jett.colegio.gotitas.model;

import java.time.LocalDate;

public class Estudiante {

    private int idEstudiante;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String grado;
    private String seccion;
    private String telefono;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido,
                      LocalDate fechaNacimiento, String grado,
                      String seccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.grado = grado;
        this.seccion = seccion;
        this.telefono = telefono;
    }

    public Estudiante(int idEstudiante, String nombre, String apellido,
                      LocalDate fechaNacimiento, String grado,
                      String seccion, String telefono) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.grado = grado;
        this.seccion = seccion;
        this.telefono = telefono;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}