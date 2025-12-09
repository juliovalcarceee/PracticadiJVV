package com.liceolapaz.dam.jvv.model;

import java.time.LocalDate;

// La clase Entity que representa la fila de la tabla Usuario
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;

    // El hash de la contraseña no se expone fuera del DAO por seguridad

    public Usuario(int id, String nombreUsuario, String nombreCompleto, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
    }

    // --- Getters ---
    public int getId() { return id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    // **DATO CALCULADO 1 (Ejemplo):** Cálculo de la edad
    public int getEdad() {
        if (fechaNacimiento == null) return 0;
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }
}