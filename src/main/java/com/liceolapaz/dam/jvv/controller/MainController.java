package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

    @FXML private Label lblNombre;
    @FXML private Label lblUsuario;
    @FXML private Label lblTipo;

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        lblNombre.setText("Nombre: " + usuario.getNombre());
        lblUsuario.setText("Usuario: " + usuario.getUsername());
        lblTipo.setText("Tipo: " + usuario.getTipo());
    }

    @FXML
    private void verEquipos() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/equipos-view.fxml")
            );

            Parent root = loader.load();

            EquipoController controller = loader.getController();
            controller.setUsuario(usuario);

            Stage stage = new Stage();
            stage.setTitle("Lista de Equipos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void verJugadores() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/jugadores-view.fxml")
            );

            Parent root = loader.load();

            JugadorController controller = loader.getController();
            controller.setUsuario(usuario);

            Stage stage = new Stage();
            stage.setTitle("Lista de Jugadores");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) lblNombre.getScene().getWindow();
        stage.close();
    }
}
