package co.edu.uniquindio.proyecto.model;

import java.time.LocalDate;

public class Reporte {
    private String tipo;
    private LocalDate fechaGeneracion;
    private String descripcion;
    private Gimnasio ownedByGimnasio;

    public Reporte(){}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Gimnasio getOwnedByGimnasio() {
        return ownedByGimnasio;
    }

    public void setOwnedByGimnasio(Gimnasio ownedByGimnasio) {
        this.ownedByGimnasio = ownedByGimnasio;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "tipo='" + tipo + '\'' +
                ", fechaGeneracion=" + fechaGeneracion +
                ", descripcion='" + descripcion + '\'' +
                ", ownedByGimnasio=" + ownedByGimnasio +
                '}';
    }
}
