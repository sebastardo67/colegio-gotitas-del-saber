package main.java.edu.jett.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.edu.jett.colegio.gotitas.service.AuthService;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;

public class RegisterController implements Initializable {
    private final AuthService authService;
    private final SceneManager sceneManager;

    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldIdDocente; 
    @FXML
    private TextField txtFieldIdRol;     
    @FXML
    private PasswordField txtFieldPassword; 

    public RegisterController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("RegisterController inicializado.");
    }

    @FXML
    public void handleRegisterUser() {
        try {
            String email = txtFieldEmail.getText().trim();
            String password = txtFieldPassword.getText();
            
            // Validar campos vacíos básicos
            if(email.isEmpty() || password.isEmpty() || txtFieldIdDocente.getText().isEmpty() || txtFieldIdRol.getText().isEmpty()){
                System.out.println("Error: Todos los campos son obligatorios.");
                return;
            }

            int idDocente = Integer.parseInt(txtFieldIdDocente.getText().trim());
            int idRol = Integer.parseInt(txtFieldIdRol.getText().trim());

            // Llamar al servicio
            boolean registrado = authService.registerUser(idDocente, email, password, idRol);
            
            if (registrado) {
                System.out.println("¡Usuario registrado exitosamente en la base de datos!");
                // Opcional: Redirigir al login usando el sceneManager
                // sceneManager.switchToLogin();
            } else {
                System.out.println("No se pudo registrar el usuario.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: El ID de docente y el ID de rol deben ser valores numéricos.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}