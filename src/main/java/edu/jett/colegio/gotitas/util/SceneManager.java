package main.java.edu.jett.colegio.gotitas.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.edu.jett.colegio.gotitas.controller.CursoController;
import main.java.edu.jett.colegio.gotitas.controller.DocenteController;
import main.java.edu.jett.colegio.gotitas.controller.EstudianteController;
import main.java.edu.jett.colegio.gotitas.controller.LoginController;
import main.java.edu.jett.colegio.gotitas.controller.MenuController;
import main.java.edu.jett.colegio.gotitas.controller.RegisterController;
import main.java.edu.jett.colegio.gotitas.repository.AuthRepository;
import main.java.edu.jett.colegio.gotitas.service.AuthService;

public class SceneManager {

    private final Stage primaryStage;
    private final AuthService authService;

  public SceneManager(Stage primaryStage) {
    this.primaryStage = primaryStage;

    AuthRepository authRepository = new AuthRepository();
    this.authService = new AuthService(authRepository);
}

    public void switchToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/login-view.fxml")
            );

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

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Iniciar Sesión - Colegio Gotitas del Saber");
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR LOGIN:");
            e.printStackTrace();
        }
    }

    public void switchToRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/register-view.fxml")
            );

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

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Registro - Colegio Gotitas del Saber");
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR REGISTRO:");
            e.printStackTrace();
        }
    }

public void switchToMenu() {
    try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/menu-view.fxml")
        );

        Parent root = loader.load();

        MenuController controller = loader.getController();
        controller.setSceneManager(this);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Colegio Gotitas del Saber");
        primaryStage.show();

    } catch (Exception e) {
        System.out.println("ERROR AL CARGAR MENU:");
        e.printStackTrace();
    }
}

    public void switchToDocente() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/docente-view.fxml")
            );

            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == DocenteController.class) {
                    return new DocenteController(this);
                }

                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestión de Docentes - Colegio Gotitas del Saber");
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR DOCENTES:");
            e.printStackTrace();
        }
    }
    public void switchToEstudiante() {
    try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/estudiante-view.fxml")
        );

        Parent root = loader.load();

        EstudianteController controller = loader.getController();
        controller.setSceneManager(this);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de Estudiantes - Colegio Gotitas del Saber");
        primaryStage.show();

    } catch (Exception e) {
        System.out.println("ERROR AL CARGAR ESTUDIANTES:");
        e.printStackTrace();
    }
}
   public void switchToCurso() {
    try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/curso-view.fxml")
        );

        Parent root = loader.load();

        CursoController controller = loader.getController();
        controller.setSceneManager(this);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de Cursos - Colegio Gotitas del Saber");
        primaryStage.show();

    } catch (Exception e) {
        System.out.println("ERROR AL CARGAR CURSOS:");
        e.printStackTrace();
    }
}
}
