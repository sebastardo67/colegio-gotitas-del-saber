/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.dto.response;

/**
 *
 * @author informatica
 */
public class LoginResponse {
    private String nombre;
    private String apellido;
    private String contrasena_hash;

    public LoginResponse(String nombre, String apellido, String contrasena_hash) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena_hash = contrasena_hash;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasena_hash() {
        return contrasena_hash;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContrasena_hash(String contrasena_hash) {
        this.contrasena_hash = contrasena_hash;
    }
    
    
    
    
}
