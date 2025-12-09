package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.EquipoDAO;
import com.liceolapaz.dam.jvv.dao.EquipoDAOImp;
import com.liceolapaz.dam.jvv.model.Equipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EquipoController {

    @FXML private TableView<Equipo> tablaEquipos;
    @FXML private TableColumn<Equipo, Integer> colId;
    @FXML private TableColumn<Equipo, String> colNombre;
    @FXML private TableColumn<Equipo, String> colCiudad;

    private final EquipoDAO equipoDAO = new EquipoDAOImp();
    private ObservableList<Equipo> lista;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        cargarDatos();
    }

    private void cargarDatos() {
        lista = FXCollections.observableArrayList(equipoDAO.listar());
        tablaEquipos.setItems(lista);
    }

    @FXML
    private void nuevoEquipo() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nuevo Equipo");
        dialog.setContentText("Nombre, Ciudad (separados por coma)");

        dialog.showAndWait().ifPresent(texto -> {
            String[] partes = texto.split(",");

            if (partes.length == 2) {
                Equipo e = new Equipo(0, partes[0].trim(), partes[1].trim());
                equipoDAO.insertar(e);
                cargarDatos();
            }
        });
    }

    @FXML
    private void editarEquipo() {
        Equipo seleccionado = tablaEquipos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) return;

        TextInputDialog dialog = new TextInputDialog(
                seleccionado.getNombre() + "," + seleccionado.getCiudad()
        );

        dialog.setHeaderText("Editar Equipo");
        dialog.setContentText("Nombre, Ciudad");

        dialog.showAndWait().ifPresent(texto -> {
            String[] partes = texto.split(",");

            if (partes.length == 2) {
                seleccionado.setNombre(partes[0].trim());
                seleccionado.setCiudad(partes[1].trim());
                equipoDAO.actualizar(seleccionado);
                cargarDatos();
            }
        });
    }

    @FXML
    private void eliminarEquipo() {
        Equipo seleccionado = tablaEquipos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText("¿Eliminar equipo?");
        alert.setContentText(seleccionado.getNombre());

        if (alert.showAndWait().get() == ButtonType.OK) {
            equipoDAO.eliminar(seleccionado.getId());
            cargarDatos();
        }
    }
}
