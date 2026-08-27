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
import main.java.edu.jett.colegio.gotitas.repository.AuthRepository;
import main.java.edu.jett.colegio.gotitas.service.AuthService;

public class SceneManager {
        private Stage primaryStage;
        
        public SceneManager(Stage primaryStage){
            this.primaryStage = primaryStage;
        }
        
        public void showLoginView() throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/login-view.fxml"));
            loader.setControllerFactory(
            clazz -> {
                if(clazz == LoginController.class){
                    AuthRepository authRepository = new AuthRepository();
                    AuthService authService = new AuthService(authRepository);
                    return new LoginController(authService, this);
                }
                try{
                    return clazz.getDeclaredConstructor().newInstance();
                }catch(Exception e){
                    throw new RuntimeException("error al crear el constructor" + e.getMessage());
                }
            });
            Parent root = loader.load();
            Scene scene = new Scene(root,600,600);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        }
}
