package main.java.edu.jett.colegio.gotitas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;

public class MenuController {

    private SceneManager sceneManager;

    @FXML
    private Button btnIrEstudiantes;

    @FXML
    private Button btnIrDocentes;

    @FXML
    private Button btnIrCursos;

    @FXML
    private Button btnCerrarSesion;

    public MenuController() {
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void handleEstudiantes() {
        if (sceneManager != null) {
            sceneManager.switchToEstudiante();
        }
    }

    @FXML
    private void handleDocentes() {
        if (sceneManager != null) {
            sceneManager.switchToDocente();
        }
    }

    @FXML
    private void handleCursos() {
        if (sceneManager != null) {
            sceneManager.switchToCurso();
        }
    }

    @FXML
    private void handleCerrarSesion() {
        if (sceneManager != null) {
            sceneManager.switchToLogin();
        }
    }
}