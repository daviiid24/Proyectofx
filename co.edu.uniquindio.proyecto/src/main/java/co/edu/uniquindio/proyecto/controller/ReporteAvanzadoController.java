package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.ReporteAdministrador;

import java.util.List;

public class ReporteAvanzadoController {
    ModelFactory modelFactory;
    public ReporteAvanzadoController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<ReporteAdministrador> obtenerReportesAdministrador() {
        return modelFactory.obtenerReportesAdministrador();
    }

    public ReporteAdministrador generarReporteAdministrador(String descripcion, String tipoReporteTexto){
        return modelFactory.generarReporteAdministrador(descripcion, tipoReporteTexto);
    }

    public ReporteAdministrador actualizarReporteAdministrador(String idReporte, String descripcion, String tipoReporteTexto) {
        return modelFactory.actualizarReporteAdministrador(idReporte, descripcion, tipoReporteTexto);
    }

    public ReporteAdministrador eliminarReporteAdministrador(String idReporte) {
        return modelFactory.eliminarReporteAdministrador(idReporte);
    }
}
