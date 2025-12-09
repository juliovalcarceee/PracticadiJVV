package com.liceolapaz.dam.jvv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // **ACTUALIZA ESTOS VALORES** con la configuración de tu BD
    private static final String URL = "jdbc:mysql://localhost:3306/practicajvv";
    private static final String USER = "root";
    private static final String PASSWORD = " ";

    /**
     * Devuelve una conexión activa a la base de datos.
     * Es estático para facilitar el acceso desde los DAOs.
     * @return Objeto Connection o null si hay un error.
     */
    public static Connection getConexion() {
        Connection con = null;
        try {
            // Se puede comentar si usas un driver moderno,
            // pero es una buena práctica asegurar la carga.
            // Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la BD. Revisa la URL, usuario y contraseña.");
            e.printStackTrace();
        }
        return con;
    }
}