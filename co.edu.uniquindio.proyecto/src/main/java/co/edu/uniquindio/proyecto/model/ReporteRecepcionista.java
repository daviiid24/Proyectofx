package co.edu.uniquindio.proyecto.model;

public class ReporteRecepcionista extends Reporte {

    private TipoReporte tipoRecepcionista;

    public ReporteRecepcionista() {
    }

    public TipoReporte getTipoRecepcionista() {
        return tipoRecepcionista;
    }

    public void setTipoRecepcionista(TipoReporte tipoRecepcionista) {
        this.tipoRecepcionista = tipoRecepcionista;
    }
}
