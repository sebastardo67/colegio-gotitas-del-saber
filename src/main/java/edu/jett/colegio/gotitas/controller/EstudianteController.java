package main.java.edu.jett.colegio.gotitas.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import main.java.edu.jett.colegio.gotitas.model.Estudiante;
import main.java.edu.jett.colegio.gotitas.service.EstudianteService;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;

public class EstudianteController {

    private SceneManager sceneManager;
    private final EstudianteService estudianteService;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtFechaNacimiento;

    @FXML
    private TextField txtGrado;

    @FXML
    private TextField txtSeccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Estudiante> tablaEstudiantes;

    @FXML
    private TableColumn<Estudiante, Integer> colId;

    @FXML
    private TableColumn<Estudiante, String> colNombre;

    @FXML
    private TableColumn<Estudiante, String> colApellido;

    @FXML
    private TableColumn<Estudiante, String> colFechaNacimiento;

    @FXML
    private TableColumn<Estudiante, String> colGrado;

    @FXML
    private TableColumn<Estudiante, String> colSeccion;

    @FXML
    private TableColumn<Estudiante, String> colTelefono;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnLimpiar;

    public EstudianteController() {
        this.estudianteService = new EstudianteService();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idEstudiante")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colApellido.setCellValueFactory(
                new PropertyValueFactory<>("apellido")
        );

        colFechaNacimiento.setCellValueFactory(
                new PropertyValueFactory<>("fechaNacimiento")
        );

        colGrado.setCellValueFactory(
                new PropertyValueFactory<>("grado")
        );

        colSeccion.setCellValueFactory(
                new PropertyValueFactory<>("seccion")
        );

        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("telefono")
        );

        tablaEstudiantes.setOnMouseClicked(
                event -> cargarEstudianteSeleccionado()
        );

        cargarEstudiantes();
    }

    private void cargarEstudiantes() {

        try {

            tablaEstudiantes.setItems(
                    FXCollections.observableArrayList(
                            estudianteService.listar()
                    )
            );

        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudieron cargar los estudiantes."
            );

            e.printStackTrace();
        }
    }

    private void cargarEstudianteSeleccionado() {

        Estudiante estudiante =
                tablaEstudiantes.getSelectionModel().getSelectedItem();

        if (estudiante == null) {
            return;
        }

        txtNombre.setText(estudiante.getNombre());
        txtApellido.setText(estudiante.getApellido());

        if (estudiante.getFechaNacimiento() != null) {
            txtFechaNacimiento.setText(
                    estudiante.getFechaNacimiento().toString()
            );
        } else {
            txtFechaNacimiento.clear();
        }

        txtGrado.setText(estudiante.getGrado());
        txtSeccion.setText(estudiante.getSeccion());
        txtTelefono.setText(estudiante.getTelefono());
    }

    @FXML
    private void handleGuardar() {

        try {

            Estudiante estudiante = crearEstudiante();

            estudianteService.guardar(estudiante);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estudiante",
                    "Estudiante registrado correctamente."
            );

            limpiarCampos();
            cargarEstudiantes();

        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }

    @FXML
    private void handleActualizar() {

        Estudiante seleccionado =
                tablaEstudiantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Actualizar",
                    "Seleccione un estudiante de la tabla."
            );

            return;
        }

        try {

            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setApellido(txtApellido.getText());

            if (txtFechaNacimiento.getText().isBlank()) {
                seleccionado.setFechaNacimiento(null);
            } else {
                seleccionado.setFechaNacimiento(
                        java.time.LocalDate.parse(
                                txtFechaNacimiento.getText()
                        )
                );
            }

            seleccionado.setGrado(txtGrado.getText());
            seleccionado.setSeccion(txtSeccion.getText());
            seleccionado.setTelefono(txtTelefono.getText());

            estudianteService.actualizar(seleccionado);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estudiante",
                    "Estudiante actualizado correctamente."
            );

            limpiarCampos();
            cargarEstudiantes();

        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }

    @FXML
    private void handleEliminar() {

        Estudiante seleccionado =
                tablaEstudiantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Eliminar",
                    "Seleccione un estudiante de la tabla."
            );

            return;
        }

        try {

            estudianteService.eliminar(
                    seleccionado.getIdEstudiante()
            );

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estudiante",
                    "Estudiante eliminado correctamente."
            );

            limpiarCampos();
            cargarEstudiantes();

        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    e.getMessage()
            );
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarCampos();
    }

    @FXML
    private void handleBuscar() {

        try {

            String texto = txtBuscar.getText();

            tablaEstudiantes.setItems(
                    FXCollections.observableArrayList(
                            estudianteService.buscar(texto)
                    )
            );

        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudieron buscar los estudiantes."
            );
        }
    }

    @FXML
    private void handleRegresar() {

        if (sceneManager != null) {
            sceneManager.switchToMenu();
        }
    }

    private Estudiante crearEstudiante() {

        java.time.LocalDate fecha = null;

        if (!txtFechaNacimiento.getText().isBlank()) {

            fecha = java.time.LocalDate.parse(
                    txtFechaNacimiento.getText()
            );
        }

        return new Estudiante(
                txtNombre.getText(),
                txtApellido.getText(),
                fecha,
                txtGrado.getText(),
                txtSeccion.getText(),
                txtTelefono.getText()
        );
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtApellido.clear();
        txtFechaNacimiento.clear();
        txtGrado.clear();
        txtSeccion.clear();
        txtTelefono.clear();
        txtBuscar.clear();

        tablaEstudiantes.getSelectionModel().clearSelection();
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