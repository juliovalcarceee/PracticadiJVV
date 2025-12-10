package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.EquipoDAO;
import com.liceolapaz.dam.jvv.dao.EquipoDAOImp;
import com.liceolapaz.dam.jvv.model.Equipo;
import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EquipoController {

    @FXML private TableView<Equipo> tablaEquipos;
    @FXML private TableColumn<Equipo, Integer> colId;
    @FXML private TableColumn<Equipo, String> colNombre;
    @FXML private TableColumn<Equipo, String> colCiudad;

    @FXML private TextField txtBuscar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCiudad;

    @FXML private Button btnInsertar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;

    private Usuario usuario;
    private EquipoDAO equipoDAO = new EquipoDAOImp();
    private ObservableList<Equipo> lista;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        lista = FXCollections.observableArrayList(equipoDAO.listar());

        FilteredList<Equipo> filtrada = new FilteredList<>(lista, p -> true);

        txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            filtrada.setPredicate(equipo -> {
                if (newValue == null || newValue.isEmpty()) return true;
                return equipo.getNombre().toLowerCase().contains(newValue.toLowerCase());
            });
        });

        SortedList<Equipo> ordenada = new SortedList<>(filtrada);
        ordenada.comparatorProperty().bind(tablaEquipos.comparatorProperty());
        tablaEquipos.setItems(ordenada);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (!usuario.getTipo().equalsIgnoreCase("ADMIN")) {
            btnInsertar.setDisable(true);
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    @FXML
    private void insertarEquipo() {
        Equipo e = new Equipo();
        e.setNombre(txtNombre.getText());
        e.setCiudad(txtCiudad.getText());

        equipoDAO.insertar(e);
        refrescar();
    }

    @FXML
    private void editarEquipo() {
        Equipo e = tablaEquipos.getSelectionModel().getSelectedItem();
        if (e != null) {
            e.setNombre(txtNombre.getText());
            e.setCiudad(txtCiudad.getText());

            equipoDAO.actualizar(e);
            refrescar();
        }
    }

    @FXML
    private void eliminarEquipo() {
        Equipo e = tablaEquipos.getSelectionModel().getSelectedItem();
        if (e != null) {
            equipoDAO.eliminar(e.getId());
            refrescar();
        }
    }

    private void refrescar() {
        lista.setAll(equipoDAO.listar());
    }

    @FXML
    private void volverMenu() {
        Stage stage = (Stage) tablaEquipos.getScene().getWindow();
        stage.close();
    }
}
