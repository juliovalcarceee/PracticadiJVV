package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.UsuarioDAO;
import com.liceolapaz.dam.jvv.dao.UsuarioDAOImpl;
import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @FXML
    private void onLogin() {

        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        Usuario u = usuarioDAO.login(usuario, password);

        if (u != null) {
            lblMensaje.setText("Bienvenido " + u.getNombre());
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
        }
    }
}
