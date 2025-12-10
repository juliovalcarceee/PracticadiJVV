package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.JugadorDAO;
import com.liceolapaz.dam.jvv.dao.JugadorDAOImpl;
import com.liceolapaz.dam.jvv.model.Jugador;
import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class JugadorController {

    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, Integer> colId;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colPosicion;
    @FXML private TableColumn<Jugador, Integer> colEdad;
    @FXML private TableColumn<Jugador, Integer> colEquipo;

    @FXML private TextField txtBuscar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPosicion;
    @FXML private TextField txtEdad;
    @FXML private TextField txtEquipo;

    @FXML private Button btnInsertar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;

    private Usuario usuario;

    private JugadorDAO jugadorDAO = new JugadorDAOImpl();
    private ObservableList<Jugador> lista;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipoId"));

        cargarJugadores();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (!usuario.getTipo().equalsIgnoreCase("ADMIN")) {
            btnInsertar.setDisable(true);
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    private void cargarJugadores() {
        lista = FXCollections.observableArrayList(jugadorDAO.listar());

        FilteredList<Jugador> filtrada = new FilteredList<>(lista, p -> true);

        txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            filtrada.setPredicate(jugador -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String filtro = newValue.toLowerCase();
                return jugador.getNombre().toLowerCase().contains(filtro);
            });
        });

        SortedList<Jugador> ordenada = new SortedList<>(filtrada);
        ordenada.comparatorProperty().bind(tablaJugadores.comparatorProperty());
        tablaJugadores.setItems(ordenada);
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
