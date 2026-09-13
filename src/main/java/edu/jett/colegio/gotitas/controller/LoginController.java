
package main.java.edu.jett.colegio.gotitas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.edu.jett.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.jett.colegio.gotitas.dto.response.LoginResponse;
import main.java.edu.jett.colegio.gotitas.service.AuthService;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;

public class LoginController {

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private PasswordField txtFieldPassword;

    private final AuthService authService;
    private final SceneManager sceneManager;

    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }
    @FXML private void handleOpenRegister(ActionEvent event) { sceneManager.switchToRegister(); }

    @FXML
    private void handleLogin(ActionEvent event) {

        String email = txtFieldEmail.getText();
        String password = txtFieldPassword.getText();

        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos vacíos",
                    "Ingrese su correo y contraseña."
            );
            return;
        }

        try {

            LoginRequest request = new LoginRequest(email, password);

            LoginResponse response = authService.login(request);

            if (response == null) {
                mostrarAlerta(
                        Alert.AlertType.ERROR,
                        "Inicio de sesión",
                        "Correo o contraseña incorrectos."
                );
                return;
            }

            System.out.println(
                    "Inicio de sesión exitoso! Bienvenido "
                    + response.getNombre()
                    + " "
                    + response.getApellido()
            );

            sceneManager.switchToMenu();

        } catch (Exception e) {

            System.out.println("ERROR AL INICIAR SESIÓN:");
            e.printStackTrace();

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "Ocurrió un error al iniciar sesión."
            );
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        sceneManager.switchToRegister();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

