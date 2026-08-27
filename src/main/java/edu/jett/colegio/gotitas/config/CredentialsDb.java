/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.config;

/**
 *
 * @author informatica
 */
public class CredentialsDb {
        public static final String DATA_BASE = System.getenv("DATA_BASE");   
        public static final String URL_DB = System.getenv("URL_MYSQL_DB") + DATA_BASE;
        public static final String USER_DB = System.getenv("USER_MYSQL_DB");
        public static final String PASS_DB = System.getenv("PASS_MYSQL_DB");
}
