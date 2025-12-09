package com.liceolapaz.dam.jvv.model;

import java.time.LocalDate;

public class Partido {

    private int idPartido;
    private LocalDate fecha;
    private String estadio;

    public Partido() {}

    public Partido(int idPartido, LocalDate fecha, String estadio) {
        this.idPartido = idPartido;
        this.fecha = fecha;
        this.estadio = estadio;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
}
