package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblTipo;

    private Usuario usuario;

    // ========= RECIBE EL USUARIO DEL LOGIN =========
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        lblNombre.setText("Nombre: " + usuario.getNombre());
        lblUsuario.setText("Usuario: " + usuario.getUsername());
        lblTipo.setText("Tipo: " + usuario.getTipo());
    }

    // ========= BOTÓN VER EQUIPOS =========
    @FXML
    private void verEquipos() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/equipos-view.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Lista de Equipos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========= BOTÓN SALIR =========
    @FXML
    private void salir() {
        Stage stage = (Stage) lblNombre.getScene().getWindow();
        stage.close();
    }
}
