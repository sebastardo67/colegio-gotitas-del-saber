    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
     */
    package main.java.edu.jett.colegio.gotitas.controller;

    import java.net.URL;
    import java.util.ResourceBundle;
import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.edu.jett.colegio.gotitas.model.Estudiante;
    import main.java.edu.jett.colegio.gotitas.service.DashboardService;
    import main.java.edu.jett.colegio.gotitas.util.SceneManager;

    /**
     * FXML Controller class
     *
     * @author informatica
     */
    public class DashboardController implements Initializable {
        private DashboardService dashboardService;
        private final SceneManager sceneManager;
        @FXML   
        private TableView<Estudiante> tbEstudiante;
        @FXML
        private TableColumn<Estudiante, String> tbColumnIdEstudiante;
        @FXML
         private TableColumn<Estudiante, String> tbColumnNombreEstudiante;
        @FXML
         private TableColumn<Estudiante, String>tbColumnApellidoEstudiante;
        @FXML
         private TableColumn<Estudiante, String> tbColumnCorreoEstudiante;
        @FXML
          private TableColumn<Estudiante, String> tbColumnSeccion;
        @FXML
        private TableColumn<Estudiante, String> tbColumnCurso;
        @FXML
        private TableColumn<Estudiante, String> tbColumnNombreDocente;
        @FXML
           private TableColumn<Estudiante, String>tbColumnApellidoDocente;
        public DashboardController(DashboardService dashboardService, SceneManager sceneManager){
            this.dashboardService = dashboardService;
            this.sceneManager = sceneManager;
        }
        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO
        }    

        private void handleLoadTableStudent(){
            tbColumnIdEstudiante.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
            tbColumnNombreEstudiante.setCellValueFactory(new PropertyValueFactory<>("nombre") );
            tbColumnApellidoEstudiante.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            tbColumnCorreoEstudiante.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
            tbColumnSeccion.setCellValueFactory(new PropertyValueFactory<>("nombreSeccion"));
            tbColumnCurso.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
            tbColumnNombreDocente.setCellValueFactory(new PropertyValueFactory<>("nombreDocente"));
            tbColumnApellidoDocente.setCellValueFactory(new PropertyValueFactory<>("apellidoDocente"));
            tbEstudiante.setItems(dashboardService.listStudent());
        }
    }
