package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.EquipoDAO;
import com.liceolapaz.dam.jvv.dao.EquipoDAOImpl;
import com.liceolapaz.dam.jvv.model.Equipo;
import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EquipoController implements Initializable {

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

    private EquipoDAO equipoDAO = new EquipoDAOImpl();
    private ObservableList<Equipo> listaEquipos;
    private FilteredList<Equipo> listaFiltrada;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        listaEquipos = FXCollections.observableArrayList(equipoDAO.obtenerTodos());
        listaFiltrada = new FilteredList<>(listaEquipos, p -> true);
        tablaEquipos.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((obs, o, n) -> {
            listaFiltrada.setPredicate(e -> {
                if (n == null || n.isEmpty()) return true;
                String t = n.toLowerCase();
                return e.getNombre().toLowerCase().contains(t)
                        || e.getCiudad().toLowerCase().contains(t);
            });
        });

        tablaEquipos.getSelectionModel().selectedItemProperty().addListener((o, v, n) -> {
            if (n != null) {
                txtNombre.setText(n.getNombre());
                txtCiudad.setText(n.getCiudad());
            }
        });
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (usuario.getTipo().equalsIgnoreCase("USER")) {
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
        recargar();
    }

    @FXML
    private void editarEquipo() {
        Equipo e = tablaEquipos.getSelectionModel().getSelectedItem();

        if (e != null) {
            e.setNombre(txtNombre.getText());
            e.setCiudad(txtCiudad.getText());
            equipoDAO.actualizar(e);
            recargar();
        }
    }

    @FXML
    private void eliminarEquipo() {
        Equipo e = tablaEquipos.getSelectionModel().getSelectedItem();

        if (e != null) {
            equipoDAO.eliminar(e.getId());
            recargar();
        }
    }

    private void recargar() {
        listaEquipos.setAll(equipoDAO.obtenerTodos());
    }

    @FXML
    private void volverMenu() {
        Stage stage = (Stage) tablaEquipos.getScene().getWindow();
        stage.close();
    }
}
