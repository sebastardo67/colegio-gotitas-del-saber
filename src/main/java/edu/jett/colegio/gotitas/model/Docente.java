/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.model;

public class Docente {

    private int idDocente;
    private String nombre;
    private String apellido;
    private String email;

    public Docente() {
    }

    public Docente(int idDocente, String nombre, String apellido, String email) {
        this.idDocente = idDocente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}