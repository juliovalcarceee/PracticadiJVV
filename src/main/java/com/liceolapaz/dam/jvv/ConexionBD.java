package com.liceolapaz.dam.jvv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/practicafutboljvv";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("CONEXIÓN OK");
        } catch (SQLException e) {
            System.err.println("ERROR DE CONEXIÓN A LA BD");
            e.printStackTrace();
        }
        return con;
    }
}
