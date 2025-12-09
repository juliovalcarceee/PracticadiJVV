package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.db.ConexionBD;
import com.liceolapaz.dam.jvv.model.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAOImp implements EquipoDAO {

    @Override
    public List<Equipo> listar() {
        List<Equipo> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion()) {
            String sql = "SELECT * FROM equipos";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Equipo e = new Equipo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ciudad")
                );
                lista.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void insertar(Equipo equipo) {
        try (Connection con = ConexionBD.getConexion()) {
            String sql = "INSERT INTO equipos (nombre, ciudad) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Equipo equipo) {
        try (Connection con = ConexionBD.getConexion()) {
            String sql = "UPDATE equipos SET nombre=?, ciudad=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
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
        try (Connection con = ConexionBD.getConexion()) {
            String sql = "DELETE FROM equipos WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
