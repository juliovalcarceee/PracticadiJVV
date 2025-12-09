package com.liceolapaz.dam.jvv.controller;

// **ARREGLO DE COMPILACIÓN:** Necesitas estas líneas
import com.liceolapaz.dam.jvv.dao.UsuarioDAO;
import com.liceolapaz.dam.jvv.dao.impl.UsuarioDAOImpl;
import com.liceolapaz.dam.jvv.model.Usuario;

// Ya NO necesitas importar java.sql.*, Connection, PreparedStatement, etc. 
// ¡El DAO se encarga de eso!
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class LoginController {

    // Instancia del DAO para cumplir el REQUISITO OBLIGATORIO
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @FXML private TextField txtUser;
    @FXML private TextField txtPassword;
    @FXML private Label lblMensaje;

    @FXML
    private void onLogin(ActionEvent event) {
        String user = txtUser.getText();
        String password = txtPassword.getText();

        try {
            // Lógica limpia: Llamar al DAO y obtener el objeto Usuario
            Usuario usuario = usuarioDAO.findByUsernameAndPassword(user, password);

            if (usuario != null) {
                lblMensaje.setText("Login correcto. Bienvenido, " + usuario.getNombreCompleto());

                // **PASO CRUCIAL (REQUISITO):** Cargar la siguiente vista
                // y pasarle el objeto 'usuario' para mostrar su información personalizada.
                // cargarVistaPrincipal(usuario); 
                System.out.println("Login exitoso. Usuario ID: " + usuario.getId());
            } else {
                lblMensaje.setText("Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            lblMensaje.setText("Error al intentar iniciar sesión (Problema de BD).");
            e.printStackTrace();
        }
    }

    // Aquí puedes añadir un método auxiliar para cargar la vista principal
    // private void cargarVistaPrincipal(Usuario usuario) { ... }
}