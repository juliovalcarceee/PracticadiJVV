package com.liceolapaz.dam.jvv.model;

public class JugadorPartido {

    private Jugador jugador;
    private Partido partido;
    private int minutosJugados;

    public JugadorPartido() {}

    public JugadorPartido(Jugador jugador, Partido partido, int minutosJugados) {
        this.jugador = jugador;
        this.partido = partido;
        this.minutosJugados = minutosJugados;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }
}
