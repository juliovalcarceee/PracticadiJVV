package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.db.ConexionBD;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private void onLogin() {

        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lblMensaje.setText("Bienvenido " + rs.getString("nombre"));
            } else {
                lblMensaje.setText("Usuario o contraseña incorrectos");
            }

        } catch (Exception e) {
            lblMensaje.setText("Error de conexión");
            e.printStackTrace();
        }
    }
}
