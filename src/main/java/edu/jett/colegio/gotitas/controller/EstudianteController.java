package main.java.edu.jett.colegio.gotitas.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

import main.java.edu.jett.colegio.gotitas.model.Estudiante;
import main.java.edu.jett.colegio.gotitas.service.DashboardService;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;


public class EstudianteController implements Initializable {

    private final DashboardService dashboardService;
    private final SceneManager sceneManager;


    @FXML
    private TableView<Estudiante> tbEstudiante;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnIdEstudiante;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnNombreEstudiante;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnApellidoEstudiante;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnCorreoEstudiante;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnSeccion;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnCurso;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnNombreDocente;

    @FXML
    private TableColumn<Estudiante, String>
            tbColumnApellidoDocente;


    public EstudianteController(
            DashboardService dashboardService,
            SceneManager sceneManager) {

        this.dashboardService = dashboardService;
        this.sceneManager = sceneManager;
    }


    // =========================================================
    // INICIALIZAR
    // =========================================================

    @Override
    public void initialize(
            URL url,
            ResourceBundle rb) {

        configurarTabla();

        cargarEstudiantes();
    }


    // =========================================================
    // CONFIGURAR TABLA
    // =========================================================

    private void configurarTabla() {

        tbColumnIdEstudiante.setCellValueFactory(
                new PropertyValueFactory<>("idEstudiante")
        );

        tbColumnNombreEstudiante.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        tbColumnApellidoEstudiante.setCellValueFactory(
                new PropertyValueFactory<>("apellido")
        );

        tbColumnCorreoEstudiante.setCellValueFactory(
                new PropertyValueFactory<>("correoElectronico")
        );

        tbColumnSeccion.setCellValueFactory(
                new PropertyValueFactory<>("nombreSeccion")
        );

        tbColumnCurso.setCellValueFactory(
                new PropertyValueFactory<>("nombreCurso")
        );

        tbColumnNombreDocente.setCellValueFactory(
                new PropertyValueFactory<>("nombreDocente")
        );

        tbColumnApellidoDocente.setCellValueFactory(
                new PropertyValueFactory<>("apellidoDocente")
        );
    }


    // =========================================================
    // CARGAR ESTUDIANTES
    // =========================================================

    private void cargarEstudiantes() {

        tbEstudiante.setItems(
                dashboardService.listStudent()
        );
    }


    // =========================================================
    // AGREGAR
    // =========================================================

    @FXML
    private void agregarEstudiante() {

        // -----------------------------------------------
        // Nombre
        // -----------------------------------------------

        TextInputDialog nombreDialog =
                new TextInputDialog();

        nombreDialog.setTitle(
                "Agregar estudiante"
        );

        nombreDialog.setHeaderText(
                "Nuevo estudiante"
        );

        nombreDialog.setContentText(
                "Nombre:"
        );


        Optional<String> nombre =
                nombreDialog.showAndWait();


        if (nombre.isEmpty()
                || nombre.get().isBlank()) {

            return;
        }


        // -----------------------------------------------
        // Apellido
        // -----------------------------------------------

        TextInputDialog apellidoDialog =
                new TextInputDialog();

        apellidoDialog.setTitle(
                "Agregar estudiante"
        );

        apellidoDialog.setHeaderText(
                "Nuevo estudiante"
        );

        apellidoDialog.setContentText(
                "Apellido:"
        );


        Optional<String> apellido =
                apellidoDialog.showAndWait();


        if (apellido.isEmpty()
                || apellido.get().isBlank()) {

            return;
        }


        // -----------------------------------------------
        // Correo
        // -----------------------------------------------

        TextInputDialog correoDialog =
                new TextInputDialog();

        correoDialog.setTitle(
                "Agregar estudiante"
        );

        correoDialog.setHeaderText(
                "Nuevo estudiante"
        );

        correoDialog.setContentText(
                "Correo electrónico:"
        );


        Optional<String> correo =
                correoDialog.showAndWait();


        if (correo.isEmpty()
                || correo.get().isBlank()) {

            return;
        }


        // -----------------------------------------------
        // Crear estudiante
        // -----------------------------------------------

        Estudiante estudiante =
                new Estudiante(

                        null,

                        nombre.get().trim(),

                        apellido.get().trim(),

                        correo.get().trim(),

                        null,
                        null,
                        null,
                        null
                );


        try {

            dashboardService.addStudent(
                    estudiante
            );


            cargarEstudiantes();


            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estudiante agregado",
                    "El estudiante fue agregado correctamente."
            );


        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }


    // =========================================================
    // MODIFICAR
    // =========================================================

    @FXML
    private void editarDatosEstudiante() {

        Estudiante seleccionado =
                tbEstudiante
                        .getSelectionModel()
                        .getSelectedItem();


        // -----------------------------------------------
        // Verificar selección
        // -----------------------------------------------

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Sin selección",
                    "Selecciona un estudiante."
            );

            return;
        }


        // -----------------------------------------------
        // Nuevo nombre
        // -----------------------------------------------

        TextInputDialog nombreDialog =
                new TextInputDialog(
                        seleccionado.getNombre()
                );

        nombreDialog.setTitle(
                "Modificar estudiante"
        );

        nombreDialog.setHeaderText(
                "Modificar datos"
        );

        nombreDialog.setContentText(
                "Nombre:"
        );


        Optional<String> nombre =
                nombreDialog.showAndWait();


        if (nombre.isEmpty()
                || nombre.get().isBlank()) {

            return;
        }


        // -----------------------------------------------
        // Nuevo apellido
        // -----------------------------------------------

        TextInputDialog apellidoDialog =
                new TextInputDialog(
                        seleccionado.getApellido()
                );

        apellidoDialog.setTitle(
                "Modificar estudiante"
        );

        apellidoDialog.setHeaderText(
                "Modificar datos"
        );

        apellidoDialog.setContentText(
                "Apellido:"
        );


        Optional<String> apellido =
                apellidoDialog.showAndWait();


        if (apellido.isEmpty()
                || apellido.get().isBlank()) {

            return;
        }


        // -----------------------------------------------
        // Nuevo correo
        // -----------------------------------------------

        TextInputDialog correoDialog =
                new TextInputDialog(
                        seleccionado.getCorreoElectronico()
                );

        correoDialog.setTitle(
                "Modificar estudiante"
        );

        correoDialog.setHeaderText(
                "Modificar datos"
        );

        correoDialog.setContentText(
                "Correo electrónico:"
        );


        Optional<String> correo =
                correoDialog.showAndWait();


        if (correo.isEmpty()
                || correo.get().isBlank()) {

            return;
        }


        // -----------------------------------------------
        // Crear objeto actualizado
        // -----------------------------------------------

        Estudiante actualizado =
                new Estudiante(

                        seleccionado.getIdEstudiante(),

                        nombre.get().trim(),

                        apellido.get().trim(),

                        correo.get().trim(),

                        seleccionado.getNombreSeccion(),

                        seleccionado.getNombreCurso(),

                        seleccionado.getNombreDocente(),

                        seleccionado.getApellidoDocente()
                );


        try {

            dashboardService.updateStudent(
                    actualizado
            );


            cargarEstudiantes();


            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estudiante actualizado",
                    "Los datos fueron modificados correctamente."
            );


        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    @FXML
    private void eliminarEstudiante() {

        Estudiante seleccionado =
                tbEstudiante
                        .getSelectionModel()
                        .getSelectedItem();


        // -----------------------------------------------
        // Verificar selección
        // -----------------------------------------------

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Sin selección",
                    "Selecciona un estudiante."
            );

            return;
        }


        // -----------------------------------------------
        // Confirmación
        // -----------------------------------------------

        Alert confirmacion =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );


        confirmacion.setTitle(
                "Eliminar estudiante"
        );

        confirmacion.setHeaderText(
                "¿Eliminar estudiante?"
        );

        confirmacion.setContentText(
                "Nombre: "
                + seleccionado.getNombre()
                + " "
                + seleccionado.getApellido()
                + "\n\n"
                + "Esta acción no se puede deshacer."
        );


        Optional<ButtonType> respuesta =
                confirmacion.showAndWait();


        if (respuesta.isEmpty()
                || respuesta.get() != ButtonType.OK) {

            return;
        }


        // -----------------------------------------------
        // Eliminar
        // -----------------------------------------------

        try {

            dashboardService.deleteStudent(
                    seleccionado.getIdEstudiante()
            );


            cargarEstudiantes();


            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estudiante eliminado",
                    "El estudiante fue eliminado correctamente."
            );


        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error al eliminar",
                    e.getMessage()
            );
        }
    }


    // =========================================================
    // ALERTA
    // =========================================================

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alert =
                new Alert(tipo);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}