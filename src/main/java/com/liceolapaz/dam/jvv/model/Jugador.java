package com.liceolapaz.dam.jvv.model;

public class Jugador {

    private int id;
    private String nombre;
    private String posicion;
    private int edad;
    private int equipoId;

    public Jugador() {
    }

    public Jugador(int id, String nombre, String posicion, int edad, int equipoId) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        this.equipoId = equipoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    // ========= PROPIEDAD CALCULADA 1: CATEGORÍA SEGÚN LA EDAD =========
    // NO existe en la base de datos, se calcula en Java
    public String getCategoriaEdad() {
        if (edad < 25) {
            return "Joven";
        } else if (edad <= 30) {
            return "Prime";
        } else {
            return "Veterano";
        }
    }
}
