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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class JugadorController implements Initializable {

    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, Integer> colId;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colPosicion;
    @FXML private TableColumn<Jugador, Integer> colEdad;
    @FXML private TableColumn<Jugador, Integer> colEquipo;
    @FXML private TableColumn<Jugador, String> colCategoria; // NUEVA COLUMNA

    @FXML private TextField txtBuscar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPosicion;
    @FXML private TextField txtEdad;
    @FXML private TextField txtEquipo;

    @FXML private Button btnInsertar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;

    private JugadorDAO jugadorDAO = new JugadorDAOImpl();
    private ObservableList<Jugador> listaJugadores = FXCollections.observableArrayList();

    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        colPosicion.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("posicion"));
        colEdad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("edad"));
        colEquipo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("equipoId"));

        // ENLAZAMOS LA COLUMNA CON LA PROPIEDAD CALCULADA getCategoriaEdad()
        colCategoria.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("categoriaEdad"));

        cargarJugadores();
        configurarBuscador();
        configurarSeleccionTabla();
    }

    // ========= RECIBIR USUARIO Y PERMISOS =========
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (usuario.getTipo().equalsIgnoreCase("USER")) {
            btnInsertar.setDisable(true);
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    // ========= CARGA Y BUSCADOR =========
    private void cargarJugadores() {
        listaJugadores = FXCollections.observableArrayList(jugadorDAO.obtenerTodos());
        tablaJugadores.setItems(listaJugadores);
    }

    private void configurarBuscador() {
        FilteredList<Jugador> filtrada = new FilteredList<>(listaJugadores, p -> true);

        txtBuscar.textProperty().addListener((obs, oldVal, newVal) -> {
            String filtro = (newVal == null) ? "" : newVal.toLowerCase().trim();

            filtrada.setPredicate(jugador -> {
                if (filtro.isEmpty()) {
                    return true;
                }
                return jugador.getNombre().toLowerCase().contains(filtro);
            });
        });

        SortedList<Jugador> ordenada = new SortedList<>(filtrada);
        ordenada.comparatorProperty().bind(tablaJugadores.comparatorProperty());
        tablaJugadores.setItems(ordenada);
    }

    private void configurarSeleccionTabla() {
        tablaJugadores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNombre.setText(newSel.getNombre());
                txtPosicion.setText(newSel.getPosicion());
                txtEdad.setText(String.valueOf(newSel.getEdad()));
                txtEquipo.setText(String.valueOf(newSel.getEquipoId()));
            }
        });
    }

    // ========= CRUD =========
    @FXML
    private void insertarJugador() {
        try {
            Jugador j = new Jugador();
            j.setNombre(txtNombre.getText());
            j.setPosicion(txtPosicion.getText());
            j.setEdad(Integer.parseInt(txtEdad.getText()));
            j.setEquipoId(Integer.parseInt(txtEquipo.getText()));

            jugadorDAO.insertar(j);
            cargarJugadores();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo insertar el jugador");
        }
    }

    @FXML
    private void editarJugador() {
        Jugador seleccionado = tablaJugadores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Selecciona un jugador");
            return;
        }

        try {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setPosicion(txtPosicion.getText());
            seleccionado.setEdad(Integer.parseInt(txtEdad.getText()));
            seleccionado.setEquipoId(Integer.parseInt(txtEquipo.getText()));

            jugadorDAO.actualizar(seleccionado);
            cargarJugadores();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo editar el jugador");
        }
    }

    @FXML
    private void eliminarJugador() {
        Jugador seleccionado = tablaJugadores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Selecciona un jugador");
            return;
        }

        try {
            jugadorDAO.eliminar(seleccionado.getId());
            cargarJugadores();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo eliminar el jugador");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtPosicion.clear();
        txtEdad.clear();
        txtEquipo.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ========= VOLVER AL MENÚ =========
    @FXML
    private void volverMenu() {
        Stage stage = (Stage) tablaJugadores.getScene().getWindow();
        stage.close();
    }
}
