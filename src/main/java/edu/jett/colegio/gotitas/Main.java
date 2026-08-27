
package main.java.edu.jett.colegio.gotitas;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import main.java.edu.jett.colegio.gotitas.config.ConnectionDb;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;
import java.sql.SQLException;

public class Main extends Application{

     private Stage primaryStage;
     
     @Override
     public void start(Stage primaryStage) throws IOException{
         this.primaryStage = primaryStage;
         SceneManager sceneManager = new SceneManager(primaryStage);
         sceneManager.showLoginView();
         primaryStage.show();
     }
    
     public static void main(String[] args) {
        launch();
    }
    
}
