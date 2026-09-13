package main.java.edu.jett.colegio.gotitas.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import main.java.edu.jett.colegio.gotitas.model.Curso;
import main.java.edu.jett.colegio.gotitas.service.CursoService;
import main.java.edu.jett.colegio.gotitas.util.SceneManager;

public class CursoController {

    private SceneManager sceneManager;
    private final CursoService cursoService;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtDocente;

    @FXML
    private TextField txtGrado;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Curso> tablaCursos;

    @FXML
    private TableColumn<Curso, Integer> colId;

    @FXML
    private TableColumn<Curso, String> colNombre;

    @FXML
    private TableColumn<Curso, String> colDescripcion;

    @FXML
    private TableColumn<Curso, String> colDocente;

    @FXML
    private TableColumn<Curso, String> colGrado;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnLimpiar;

    public CursoController() {
        this.cursoService = new CursoService();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idCurso")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion")
        );

        colDocente.setCellValueFactory(
                new PropertyValueFactory<>("docente")
        );

        colGrado.setCellValueFactory(
                new PropertyValueFactory<>("grado")
        );

        tablaCursos.setOnMouseClicked(event -> cargarCursoSeleccionado());

        cargarCursos();
    }

    private void cargarCursos() {

        try {

            tablaCursos.setItems(
                    FXCollections.observableArrayList(
                            cursoService.listar()
                    )
            );

        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudieron cargar los cursos."
            );

            e.printStackTrace();
        }
    }

    private void cargarCursoSeleccionado() {

        Curso curso = tablaCursos.getSelectionModel().getSelectedItem();

        if (curso == null) {
            return;
        }

        txtNombre.setText(curso.getNombre());
        txtDescripcion.setText(curso.getDescripcion());
        txtDocente.setText(curso.getDocente());
        txtGrado.setText(curso.getGrado());
    }

    @FXML
    private void handleGuardar() {

        try {

            Curso curso = new Curso(
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    txtDocente.getText(),
                    txtGrado.getText()
            );

            cursoService.guardar(curso);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Curso",
                    "Curso registrado correctamente."
            );

            limpiarCampos();
            cargarCursos();

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

        Curso seleccionado =
                tablaCursos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Actualizar",
                    "Seleccione un curso de la tabla."
            );

            return;
        }

        try {

            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setDescripcion(txtDescripcion.getText());
            seleccionado.setDocente(txtDocente.getText());
            seleccionado.setGrado(txtGrado.getText());

            cursoService.actualizar(seleccionado);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Curso",
                    "Curso actualizado correctamente."
            );

            limpiarCampos();
            cargarCursos();

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

        Curso seleccionado =
                tablaCursos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Eliminar",
                    "Seleccione un curso de la tabla."
            );

            return;
        }

        try {

            cursoService.eliminar(
                    seleccionado.getIdCurso()
            );

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Curso",
                    "Curso eliminado correctamente."
            );

            limpiarCampos();
            cargarCursos();

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

            tablaCursos.setItems(
                    FXCollections.observableArrayList(
                            cursoService.buscar(texto)
                    )
            );

        } catch (Exception e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudieron buscar los cursos."
            );
        }
    }

    @FXML
    private void handleRegresar() {

        if (sceneManager != null) {
            sceneManager.switchToMenu();
        }
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtDescripcion.clear();
        txtDocente.clear();
        txtGrado.clear();
        txtBuscar.clear();

        tablaCursos.getSelectionModel().clearSelection();
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