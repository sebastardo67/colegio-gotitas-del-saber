/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.security.jbcrypt;

public class GenerarHash {
    public static void main(String[] args) {
        String contraseña = "NuevaClave123";

        String hash = BCrypt.hashpw(
            contraseña,
            BCrypt.gensalt(12)
        );

        System.out.println(hash);
    }
}
