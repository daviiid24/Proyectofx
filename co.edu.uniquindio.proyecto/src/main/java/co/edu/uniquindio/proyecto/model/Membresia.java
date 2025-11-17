package co.edu.uniquindio.proyecto.model;

import java.time.LocalDate;

public class Membresia {
    private String idMembresia;
    private TipoMembresia tipoMembresia;
    private Duracion duracion;
    private TipoUsuario tipoUsuario;
    private double costo;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private Estado estado;
    private Gimnasio ownedByGimnasio;

    public Membresia() {
    }

    public Membresia(String idMembresia, TipoMembresia tipoMembresia, Duracion duracion, double costo) {
        this.idMembresia=idMembresia;
        this.tipoMembresia = tipoMembresia;
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

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public Duracion getDuracion() {
        return duracion;
    }

    public void setDuracion(Duracion duracion) {
        this.duracion = duracion;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
                ", tipoMembresia=" + tipoMembresia +
                ", duracion=" + duracion +
                ", tipoUsuario=" + tipoUsuario +
                ", costo=" + costo +
                ", fechaInicio=" + fechaInicio +
                ", fechaVencimiento=" + fechaVencimiento +
                ", estado=" + estado +
                ", ownedByGimnasio=" + ownedByGimnasio +
                '}';
    }
}
