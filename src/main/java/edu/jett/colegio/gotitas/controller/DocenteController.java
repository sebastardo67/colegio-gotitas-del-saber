/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.edu.jett.colegio.gotitas.controller;

/**
 *
 * @author informatica
 */
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.edu.jett.colegio.gotitas.model.Docente;

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

        System.out.println("DocenteController iniciado");
    }
}