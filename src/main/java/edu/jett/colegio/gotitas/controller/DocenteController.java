package main.java.edu.jett.colegio.gotitas.controller;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import main.java.edu.jett.colegio.gotitas.model.Docente;
import main.java.edu.jett.colegio.gotitas.service.DocenteService;

public class DocenteController {

    @FXML
    private TableView<Docente> tablaDocentes;

    @FXML
    private TableColumn<Docente, Integer> colIdDocente;

    @FXML
    private TableColumn<Docente, String> colNombre;

    @FXML
    private TableColumn<Docente, String> colApellido;

    @FXML
    private TableColumn<Docente, String> colCorreo;

    @FXML
    private TextField txtIdDocente;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    private final DocenteService docenteService = new DocenteService();

    @FXML
    public void initialize() {

        colIdDocente.setCellValueFactory(
                dato -> new javafx.beans.property.SimpleIntegerProperty(
                        dato.getValue().getIdDocente()
                ).asObject()
        );

        colNombre.setCellValueFactory(
                dato -> new javafx.beans.property.SimpleStringProperty(
                        dato.getValue().getNombre()
                )
        );

        colApellido.setCellValueFactory(
                dato -> new javafx.beans.property.SimpleStringProperty(
                        dato.getValue().getApellido()
                )
        );

        colCorreo.setCellValueFactory(
                dato -> new javafx.beans.property.SimpleStringProperty(
                        dato.getValue().getEmail()
                )
        );

        cargarDocentes();
    }

    

    @FXML
    private void handleLeer(ActionEvent event) {
        cargarDocentes();
    }

    private void cargarDocentes() {

        try {

            ObservableList<Docente> docentes =
                    FXCollections.observableArrayList(
                            docenteService.listarDocentes()
                    );

            tablaDocentes.setItems(docentes);

        } catch (SQLException e) {

            mostrarError(
                    "Error al cargar docentes",
                    e.getMessage()
            );
        }
    }

    

    @FXML
    private void handleAñadir(ActionEvent event) {

        try {

            int id = Integer.parseInt(txtIdDocente.getText());

            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                mostrarError(
                        "Datos incompletos",
                        "Completa todos los campos."
                );
                return;
            }

            Docente docente = new Docente(
                    id,
                    nombre,
                    apellido,
                    correo
            );

            boolean resultado =
                    docenteService.agregarDocente(docente);

            if (resultado) {

                mostrarInformacion(
                        "Docente agregado",
                        "El docente se agregó correctamente."
                );

                limpiarCampos();
                cargarDocentes();
            }

        } catch (NumberFormatException e) {

            mostrarError(
                    "ID inválido",
                    "El ID del docente debe ser un número."
            );

        } catch (SQLException e) {

            mostrarError(
                    "Error al agregar docente",
                    e.getMessage()
            );
        }
    }

   

    @FXML
    private void handleActualizar(ActionEvent event) {

        try {

            int id = Integer.parseInt(txtIdDocente.getText());

            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                mostrarError(
                        "Datos incompletos",
                        "Completa todos los campos."
                );
                return;
            }

            Docente docente = new Docente(
                    id,
                    nombre,
                    apellido,
                    correo
            );

            boolean resultado =
                    docenteService.actualizarDocente(docente);

            if (resultado) {

                mostrarInformacion(
                        "Docente actualizado",
                        "El docente se actualizó correctamente."
                );

                limpiarCampos();
                cargarDocentes();

            } else {

                mostrarError(
                        "No encontrado",
                        "No existe un docente con ese ID."
                );
            }

        } catch (NumberFormatException e) {

            mostrarError(
                    "ID inválido",
                    "El ID del docente debe ser un número."
            );

        } catch (SQLException e) {

            mostrarError(
                    "Error al actualizar docente",
                    e.getMessage()
            );
        }
    }

    

    @FXML
    private void handleBorrar(ActionEvent event) {

        try {

            int id = Integer.parseInt(txtIdDocente.getText());

            boolean resultado =
                    docenteService.eliminarDocente(id);

            if (resultado) {

                mostrarInformacion(
                        "Docente eliminado",
                        "El docente se eliminó correctamente."
                );

                limpiarCampos();
                cargarDocentes();

            } else {

                mostrarError(
                        "No encontrado",
                        "No existe un docente con ese ID."
                );
            }

        } catch (NumberFormatException e) {

            mostrarError(
                    "ID inválido",
                    "Escribe un ID válido."
            );

        } catch (SQLException e) {

            mostrarError(
                    "Error al eliminar docente",
                    e.getMessage()
            );
        }
    }

   

    private void limpiarCampos() {

        txtIdDocente.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
    }

    

    private void mostrarInformacion(String titulo, String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    private void mostrarError(String titulo, String mensaje) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}