package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.db.ConexionBD;
import com.liceolapaz.dam.jvv.model.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAOImpl implements EquipoDAO {

    @Override
    public List<Equipo> obtenerTodos() {

        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipo e = new Equipo();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setCiudad(rs.getString("ciudad"));
                lista.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void insertar(Equipo equipo) {

        String sql = "INSERT INTO equipos (nombre, ciudad) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Equipo equipo) {

        String sql = "UPDATE equipos SET nombre=?, ciudad=? WHERE id=?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.setInt(3, equipo.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM equipos WHERE id=?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
