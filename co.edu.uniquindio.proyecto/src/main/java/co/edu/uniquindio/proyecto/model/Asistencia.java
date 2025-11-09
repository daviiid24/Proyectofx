package co.edu.uniquindio.proyecto.model;

import java.time.LocalDate;

public class Asistencia {
    private final LocalDate fecha;
    private final String descripcion;

    public Asistencia(LocalDate fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
