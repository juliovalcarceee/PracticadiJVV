package com.liceolapaz.dam.jvv.controller;

import com.liceolapaz.dam.jvv.dao.UsuarioDAO;
import com.liceolapaz.dam.jvv.dao.UsuarioDAOImpl;
import com.liceolapaz.dam.jvv.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Rellena todos los campos");
            return;
        }

        Usuario user = usuarioDAO.login(usuario, password);

        if (user != null) {
            lblMensaje.setText("Login correcto");
            abrirPanel(user);
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
        }
    }

    private void abrirPanel(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/main-view.fxml")
            );

            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.setUsuario(usuario);   // ✅ PASO CLAVE (YA LO TENÍAS BIEN)

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel Principal");
            stage.show();

            Stage actual = (Stage) txtUsuario.getScene().getWindow();
            actual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
