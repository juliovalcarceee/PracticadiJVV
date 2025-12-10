package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.JugadorDAO;
import com.liceolapaz.dam.jvv.dao.JugadorDAOImpl;
import com.liceolapaz.dam.jvv.model.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class JugadorController {

    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, Integer> colId;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colPosicion;
    @FXML private TableColumn<Jugador, Integer> colEdad;
    @FXML private TableColumn<Jugador, Integer> colEquipo;

    @FXML private TextField txtNombre;
    @FXML private TextField txtPosicion;
    @FXML private TextField txtEdad;
    @FXML private TextField txtEquipo;

    private JugadorDAO jugadorDAO = new JugadorDAOImpl();
    private ObservableList<Jugador> lista;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        colPosicion.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("posicion"));
        colEdad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("edad"));
        colEquipo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("equipoId"));

        cargarJugadores();
    }

    private void cargarJugadores() {
        lista = FXCollections.observableArrayList(jugadorDAO.listar());
        tablaJugadores.setItems(lista);
    }

    @FXML
    private void insertarJugador() {
        Jugador j = new Jugador();
        j.setNombre(txtNombre.getText());
        j.setPosicion(txtPosicion.getText());
        j.setEdad(Integer.parseInt(txtEdad.getText()));
        j.setEquipoId(Integer.parseInt(txtEquipo.getText()));

        jugadorDAO.insertar(j);
        cargarJugadores();
    }

    @FXML
    private void editarJugador() {
        Jugador j = tablaJugadores.getSelectionModel().getSelectedItem();
        if (j != null) {
            j.setNombre(txtNombre.getText());
            j.setPosicion(txtPosicion.getText());
            j.setEdad(Integer.parseInt(txtEdad.getText()));
            j.setEquipoId(Integer.parseInt(txtEquipo.getText()));

            jugadorDAO.actualizar(j);
            cargarJugadores();
        }
    }

    @FXML
    private void eliminarJugador() {
        Jugador j = tablaJugadores.getSelectionModel().getSelectedItem();
        if (j != null) {
            jugadorDAO.eliminar(j.getId());
            cargarJugadores();
        }
    }

    @FXML
    private void volverMenu() {
        Stage stage = (Stage) tablaJugadores.getScene().getWindow();
        stage.close();
    }
}
