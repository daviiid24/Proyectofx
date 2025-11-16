package co.edu.uniquindio.proyecto.model;

import java.time.LocalDate;

public class Reporte {

    private String idReporte;
    private TipoReporte tipo;
    private LocalDate fechaGeneracion;
    private String descripcion;
    private String contenidoGenerado;
    private Gimnasio ownedByGimnasio;

    public Reporte() {
        this.fechaGeneracion = LocalDate.now();
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public TipoReporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoReporte tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenidoGenerado() {
        return contenidoGenerado;
    }

    public void setContenidoGenerado(String contenidoGenerado) {
        this.contenidoGenerado = contenidoGenerado;
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
                "idReporte='" + idReporte + '\'' +
                ", tipo=" + tipo +
                ", fechaGeneracion=" + fechaGeneracion +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
