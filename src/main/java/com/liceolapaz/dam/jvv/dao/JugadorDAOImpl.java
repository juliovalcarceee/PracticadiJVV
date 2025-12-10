package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.db.ConexionBD;
import com.liceolapaz.dam.jvv.model.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAOImpl implements JugadorDAO {

    @Override
    public List<Jugador> listar() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setId(rs.getInt("id"));
                j.setNombre(rs.getString("nombre"));
                j.setPosicion(rs.getString("posicion"));
                j.setEdad(rs.getInt("edad"));
                j.setEquipoId(rs.getInt("equipo_id"));
                lista.add(j);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void insertar(Jugador j) {
        String sql = "INSERT INTO jugadores (nombre, posicion, edad, equipo_id) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getNombre());
            ps.setString(2, j.getPosicion());
            ps.setInt(3, j.getEdad());
            ps.setInt(4, j.getEquipoId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Jugador j) {
        String sql = "UPDATE jugadores SET nombre=?, posicion=?, edad=?, equipo_id=? WHERE id=?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getNombre());
            ps.setString(2, j.getPosicion());
            ps.setInt(3, j.getEdad());
            ps.setInt(4, j.getEquipoId());
            ps.setInt(5, j.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM jugadores WHERE id=?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
