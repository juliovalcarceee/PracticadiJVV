package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.db.ConexionBD;
import com.liceolapaz.dam.jvv.model.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAOImp implements EquipoDAO {

    @Override
    public List<Equipo> getAllEquipos() {
        List<Equipo> equipos = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion()) {
            String sql = "SELECT * FROM equipos";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Equipo e = new Equipo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ciudad")
                );
                equipos.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipos;
    }
}
