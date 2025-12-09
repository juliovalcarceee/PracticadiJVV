package com.liceolapaz.dam.jvv.model;

public class Jugador {

    private int idJugador;
    private String nombre;
    private int edad;
    private String posicion;
    private Equipo equipo;   // 1:N

    public Jugador() {}

    public Jugador(int idJugador, String nombre, int edad, String posicion, Equipo equipo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
