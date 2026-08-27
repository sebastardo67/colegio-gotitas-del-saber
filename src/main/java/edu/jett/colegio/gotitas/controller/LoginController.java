/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.edu.jett.colegio.gotitas.service.AuthService;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;
import java.sql.SQLException;
import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class LoginController implements Initializable {
    private final AuthService authService;
    private final SceneManager sceneManager;
   @FXML
    private TextField txtFieldEmail;
    
    public LoginController(AuthService authService, SceneManager sceneManager){
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("TODO LO QUE ESTE ACA SE EJECUTA CUANDO SE MUESTRA LA VISTA");
    }    
    
    public void handleSayHello(){
        System.out.println("Hola mi nombre es: " + txtFieldEmail.getText());
    }
    
    public void handleTestDataBaseConnection() throws Exception{
        try{
            ConnectionDb.getconnectionDataBase();
            System.out.println("conectado");
        }catch(SQLException e){
            System.out.println("error al conectar: " + e.getMessage());
        }
    }
}
