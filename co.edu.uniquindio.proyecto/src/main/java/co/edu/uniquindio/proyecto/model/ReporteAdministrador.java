package co.edu.uniquindio.proyecto.model;

public class ReporteAdministrador extends Reporte {

    private TipoReporteAvanzado tipoAdministrador;

    public ReporteAdministrador() {
    }

    public TipoReporteAvanzado getTipoAdministrador() {
        return tipoAdministrador;
    }

    public void setTipoAdministrador(TipoReporteAvanzado tipoAdministrador) {
        this.tipoAdministrador = tipoAdministrador;
    }
}
