package main.java.edu.jett.colegio.gotitas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;

public class MenuController {

    private final SceneManager sceneManager;

    @FXML private Button btnIrDocentes;
    @FXML private Button btnCerrarSesion;

    public MenuController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        // Acción para ir a la vista de docentes que ya configuramos
 if (btnIrDocentes != null) {
    btnIrDocentes.setOnAction(e -> sceneManager.switchToDocente());
}

        // Acción para regresar al login
        if (btnCerrarSesion != null) {
            btnCerrarSesion.setOnAction(e -> sceneManager.switchToLogin());
        }
    }
}