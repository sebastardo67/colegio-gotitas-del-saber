package main.java.edu.jett.colegio.gotitas.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;
import main.java.edu.jett.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.jett.colegio.gotitas.dto.response.LoginResponse;
import main.java.edu.jett.colegio.gotitas.service.AuthService;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;

public class LoginController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldPassword;

    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("LoginController inicializado.");
    }

    @FXML
    public void handleLogin() {
        try {
            String email = txtFieldEmail.getText().trim();
            String password = txtFieldPassword.getText();

            if (email.isEmpty() || password.isEmpty()) {
                System.out.println("El correo y la contraseña no pueden estar vacíos.");
                return;
            }

            LoginRequest request = new LoginRequest(email, password);
            LoginResponse response = authService.login(request);

if (response != null) {
    System.out.println(
        "¡Inicio de sesión exitoso! Bienvenido "
        + response.getNombre()
        + " "
        + response.getApellido()
    );

    sceneManager.switchToDocente();

} else {
    System.out.println("Credenciales incorrectas o usuario no encontrado.");
}

        } catch (Exception e) {
            System.out.println(
                "Error al iniciar sesión: " + e.getMessage()
            );
        }
    }

    @FXML
    public void handleOpenRegister() {
        try {
            sceneManager.switchToRegister();
        } catch (Exception e) {
            System.out.println(
                "Error al cambiar a la vista de registro: "
                + e.getMessage()
            );
        }
    }

    public void handleTestDataBaseConnection() throws Exception {
        try {
            ConnectionDb.getconnectionDataBase();
            System.out.println("conectado");
        } catch (SQLException e) {
            System.out.println(
                "error al conectar: " + e.getMessage()
            );
        }
    }
}