package co.edu.uniquindio.proyecto.model;

import java.time.LocalDate;

public class Membresia {
    private String idMembresia;
    private TipoMembresia tipo;
    private Duracion duracion;
    private double costo;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private Estado estado;
    private Gimnasio ownedByGimnasio;

    public Membresia() {
    }

    public Membresia(String idMembresia, TipoMembresia tipo, Duracion duracion, double costo) {
        this.idMembresia=idMembresia;
        this.tipo = tipo;
        this.duracion = duracion;
        this.costo = costo;
        this.fechaInicio = LocalDate.now();
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(String idMembresia) {
        this.idMembresia = idMembresia;
    }

    public TipoMembresia getTipo() {
        return tipo;
    }

    public void setTipo(TipoMembresia tipo) {
        this.tipo = tipo;
    }

    public Duracion getDuracion() {
        return duracion;
    }

    public void setDuracion(Duracion duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Gimnasio getOwnedByGimnasio() {
        return ownedByGimnasio;
    }

    public void setOwnedByGimnasio(Gimnasio ownedByGimnasio) {
        this.ownedByGimnasio = ownedByGimnasio;
    }

    @Override
    public String toString() {
        return "Membresia{" +
                "idMembresia='" + idMembresia + '\'' +
                ", tipo=" + tipo +
                ", duracion=" + duracion +
                ", costo=" + costo +
                ", fechaInicio=" + fechaInicio +
                ", fechaVencimiento=" + fechaVencimiento +
                ", estado=" + estado +
                ", ownedByGimnasio=" + ownedByGimnasio +
                '}';
    }
}
