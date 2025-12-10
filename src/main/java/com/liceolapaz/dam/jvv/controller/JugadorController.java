package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.JugadorDAO;
import com.liceolapaz.dam.jvv.dao.JugadorDAOImpl;
import com.liceolapaz.dam.jvv.model.Jugador;
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

public class JugadorController implements Initializable {

    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, Integer> colId;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colPosicion;
    @FXML private TableColumn<Jugador, Integer> colEdad;
    @FXML private TableColumn<Jugador, Integer> colEquipoId;

    @FXML private TextField txtBuscar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPosicion;
    @FXML private TextField txtEdad;
    @FXML private TextField txtEquipo;

    @FXML private Button btnInsertar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;

    private JugadorDAO jugadorDAO = new JugadorDAOImpl();
    private ObservableList<Jugador> listaJugadores;
    private FilteredList<Jugador> listaFiltrada;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEquipoId.setCellValueFactory(new PropertyValueFactory<>("equipoId"));

        listaJugadores = FXCollections.observableArrayList(jugadorDAO.obtenerTodos());
        listaFiltrada = new FilteredList<>(listaJugadores, p -> true);
        tablaJugadores.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((obs, oldVal, newVal) -> {
            listaFiltrada.setPredicate(j -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String texto = newVal.toLowerCase();
                return j.getNombre().toLowerCase().contains(texto)
                        || j.getPosicion().toLowerCase().contains(texto)
                        || String.valueOf(j.getEquipoId()).contains(texto);
            });
        });

        tablaJugadores.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            if (n != null) {
                txtNombre.setText(n.getNombre());
                txtPosicion.setText(n.getPosicion());
                txtEdad.setText(String.valueOf(n.getEdad()));
                txtEquipo.setText(String.valueOf(n.getEquipoId()));
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
    private void insertarJugador() {
        Jugador j = new Jugador();
        j.setNombre(txtNombre.getText());
        j.setPosicion(txtPosicion.getText());
        j.setEdad(Integer.parseInt(txtEdad.getText()));
        j.setEquipoId(Integer.parseInt(txtEquipo.getText()));

        jugadorDAO.insertar(j);
        recargar();
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
            recargar();
        }
    }

    @FXML
    private void eliminarJugador() {
        Jugador j = tablaJugadores.getSelectionModel().getSelectedItem();

        if (j != null) {
            jugadorDAO.eliminar(j.getId());
            recargar();
        }
    }

    private void recargar() {
        listaJugadores.setAll(jugadorDAO.obtenerTodos());
    }

    @FXML
    private void volverMenu() {
        Stage stage = (Stage) tablaJugadores.getScene().getWindow();
        stage.close();
    }
}
