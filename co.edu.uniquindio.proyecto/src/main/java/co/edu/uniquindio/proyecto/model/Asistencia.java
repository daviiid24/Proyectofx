package co.edu.uniquindio.proyecto.model;

import java.time.LocalDate;

public class Asistencia {
    private final LocalDate fecha;
    private final String descripcion;
    private Gimnasio ownedByGimnasio;

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

    public Gimnasio getOwnedByGimnasio() {
        return ownedByGimnasio;
    }

    public void setOwnedByGimnasio(Gimnasio ownedByGimnasio) {
        this.ownedByGimnasio = ownedByGimnasio;
    }

}
