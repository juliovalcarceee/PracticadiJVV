package com.liceolapaz.dam.jvv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // **ACTUALIZA ESTOS VALORES** con la configuración de tu BD
    private static final String URL = "jdbc:mysql://localhost:3306/practicajvv";
    private static final String USER = "root";
    private static final String PASSWORD = " ";


    public static Connection getConexion() {
        Connection con = null;
        try {

            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la BD. Revisa la URL, usuario y contraseña.");
            e.printStackTrace();
        }
        return con;
    }
}