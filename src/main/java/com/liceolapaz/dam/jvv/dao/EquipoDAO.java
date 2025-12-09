package com.liceolapaz.dam.jvv.dao;

import com.liceolapaz.dam.jvv.model.Equipo;
import java.util.List;

public interface EquipoDAO {

    List<Equipo> listar();

    void insertar(Equipo equipo);

    void actualizar(Equipo equipo);

    void eliminar(int id);
}
