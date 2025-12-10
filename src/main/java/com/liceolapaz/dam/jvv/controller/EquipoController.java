package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.EquipoDAO;
import com.liceolapaz.dam.jvv.dao.EquipoDAOImpl;
import com.liceolapaz.dam.jvv.model.Equipo;
import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EquipoController implements Initializable {

    @FXML private TableView<Equipo> tablaEquipos;
    @FXML private TableColumn<Equipo, Integer> colId;
    @FXML private TableColumn<Equipo, String> colNombre;
    @FXML private TableColumn<Equipo, String> colCiudad;
    @FXML private TableColumn<Equipo, String> colNombreCompleto; // NUEVA

    @FXML private TextField txtBuscar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCiudad;

    @FXML private Button btnInsertar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;

    private EquipoDAO equipoDAO = new EquipoDAOImpl();
    private ObservableList<Equipo> listaEquipos = FXCollections.observableArrayList();
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("ciudad"));

        // ENLAZAMOS LA COLUMNA CON LA PROPIEDAD CALCULADA getNombreCompleto()
        colNombreCompleto.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombreCompleto"));

        cargarEquipos();
        configurarBuscador();
        configurarSeleccionTabla();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (usuario.getTipo().equalsIgnoreCase("USER")) {
            btnInsertar.setDisable(true);
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    private void cargarEquipos() {
        listaEquipos = FXCollections.observableArrayList(equipoDAO.obtenerTodos());
        tablaEquipos.setItems(listaEquipos);
    }

    private void configurarBuscador() {
        FilteredList<Equipo> filtrada = new FilteredList<>(listaEquipos, e -> true);

        txtBuscar.textProperty().addListener((obs, oldVal, newVal) -> {
            String filtro = (newVal == null) ? "" : newVal.toLowerCase().trim();

            filtrada.setPredicate(equipo -> {
                if (filtro.isEmpty()) {
                    return true;
                }
                return equipo.getNombre().toLowerCase().contains(filtro)
                        || equipo.getCiudad().toLowerCase().contains(filtro);
            });
        });

        SortedList<Equipo> ordenada = new SortedList<>(filtrada);
        ordenada.comparatorProperty().bind(tablaEquipos.comparatorProperty());
        tablaEquipos.setItems(ordenada);
    }

    private void configurarSeleccionTabla() {
        tablaEquipos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNombre.setText(newSel.getNombre());
                txtCiudad.setText(newSel.getCiudad());
            }
        });
    }

    @FXML
    private void insertarEquipo() {
        try {
            Equipo e = new Equipo();
            e.setNombre(txtNombre.getText());
            e.setCiudad(txtCiudad.getText());

            equipoDAO.insertar(e);
            cargarEquipos();
            limpiarCampos();
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo insertar el equipo");
        }
    }

    @FXML
    private void editarEquipo() {
        Equipo seleccionado = tablaEquipos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Selecciona un equipo");
            return;
        }

        try {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setCiudad(txtCiudad.getText());

            equipoDAO.actualizar(seleccionado);
            cargarEquipos();
            limpiarCampos();
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo editar el equipo");
        }
    }

    @FXML
    private void eliminarEquipo() {
        Equipo seleccionado = tablaEquipos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Selecciona un equipo");
            return;
        }

        try {
            equipoDAO.eliminar(seleccionado.getId());
            cargarEquipos();
            limpiarCampos();
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo eliminar el equipo");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtCiudad.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void volverMenu() {
        Stage stage = (Stage) tablaEquipos.getScene().getWindow();
        stage.close();
    }
}
