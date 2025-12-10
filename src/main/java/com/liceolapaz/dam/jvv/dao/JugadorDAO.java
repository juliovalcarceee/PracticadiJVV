package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.model.Jugador;
import java.util.List;

public interface JugadorDAO {

    List<Jugador> listar();

    void insertar(Jugador jugador);

    void actualizar(Jugador jugador);

    void eliminar(int id);
}
