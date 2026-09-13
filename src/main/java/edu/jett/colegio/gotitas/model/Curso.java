package main.java.edu.jett.colegio.gotitas.model;

public class Curso {

    private int idCurso;
    private String nombre;
    private String descripcion;
    private String docente;
    private String grado;

    public Curso() {
    }

    public Curso(String nombre, String descripcion, String docente, String grado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.docente = docente;
        this.grado = grado;
    }

    public Curso(int idCurso, String nombre, String descripcion, String docente, String grado) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.docente = docente;
        this.grado = grado;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}