/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.edu.jett.colegio.gotitas.controller.LoginController;
import main.java.edu.jett.colegio.gotitas.controller.RegisterController;
import main.java.edu.jett.colegio.gotitas.repository.AuthRepository;
import main.java.edu.jett.colegio.gotitas.service.AuthService;

/**
 *
 * @author informatica
 */
public class SceneManager {
    private static Stage primaryStage;
    private final AuthService authService;

    public SceneManager(Stage stage) {
        primaryStage = stage;
        // Instanciamos las dependencias base
        AuthRepository authRepository = new AuthRepository();
        this.authService = new AuthService(authRepository);
    }

    public void switchToLogin() {
        try {
            // En switchToLogin()
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login-view.fxml"));

            
            // Fábrica para inyectar dependencias al constructor del LoginController
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == LoginController.class) {
                    return new LoginController(authService, this);
                }
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Iniciar Sesión - Colegio Gotitas del Saber");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar la vista de login: " + e.getMessage());
        }
    }

    public void switchToRegister() {
        try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register-view.fxml"));


            
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == RegisterController.class) {
                    return new RegisterController(authService, this);
                }
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Registro - Colegio Gotitas del Saber");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar la vista de registro: " + e.getMessage());
        }
    }
}