package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.db.ConexionBD;
import com.liceolapaz.dam.jvv.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario login(String username, String password) {

        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        "ADMIN"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
