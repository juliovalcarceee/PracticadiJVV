package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.EquipoDAO;
import com.liceolapaz.dam.jvv.dao.EquipoDAOImp;
import com.liceolapaz.dam.jvv.model.Equipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EquipoController {

    @FXML private TableView<Equipo> tablaEquipos;
    @FXML private TableColumn<Equipo, Integer> colId;
    @FXML private TableColumn<Equipo, String> colNombre;
    @FXML private TableColumn<Equipo, String> colCiudad;

    @FXML private TextField txtNombre;
    @FXML private TextField txtCiudad;

    private EquipoDAO equipoDAO = new EquipoDAOImp();
    private ObservableList<Equipo> lista;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("ciudad"));

        cargarEquipos();
    }

    private void cargarEquipos() {
        lista = FXCollections.observableArrayList(equipoDAO.listar());
        tablaEquipos.setItems(lista);
    }

    @FXML
    private void insertarEquipo() {
        Equipo e = new Equipo();
        e.setNombre(txtNombre.getText());
        e.setCiudad(txtCiudad.getText());

        equipoDAO.insertar(e);
        cargarEquipos();
    }

    @FXML
    private void editarEquipo() {
        Equipo e = tablaEquipos.getSelectionModel().getSelectedItem();
        if (e != null) {
            e.setNombre(txtNombre.getText());
            e.setCiudad(txtCiudad.getText());

            equipoDAO.actualizar(e);
            cargarEquipos();
        }
    }

    @FXML
    private void eliminarEquipo() {
        Equipo e = tablaEquipos.getSelectionModel().getSelectedItem();
        if (e != null) {
            equipoDAO.eliminar(e.getId());
            cargarEquipos();
        }
    }

    @FXML
    private void volverMenu() {
        Stage stage = (Stage) tablaEquipos.getScene().getWindow();
        stage.close();
    }
}
