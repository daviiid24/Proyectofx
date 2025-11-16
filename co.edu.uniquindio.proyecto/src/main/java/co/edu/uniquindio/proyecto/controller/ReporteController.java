package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.ReporteRecepcionista;

import java.util.List;

public class ReporteController {
    ModelFactory modelFactory;
    public ReporteController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<ReporteRecepcionista> obtenerReportesRecepcionista() {
        return modelFactory.obtenerReportesRecepcionista();
    }

    public ReporteRecepcionista generarReporteRecepcionista(String descripcion, String tipoReporteTexto){
        return modelFactory.generarReporteRecepcionista(descripcion, tipoReporteTexto);
    }

    public ReporteRecepcionista actualizarReporteRecepcionista(String idReporte, String descripcion, String tipoReporteTexto) {
        return modelFactory.actualizarReporteRecepcionista(idReporte, descripcion, tipoReporteTexto);
    }

    public ReporteRecepcionista eliminarReporteRecepcionista(String idReporte) {
        return modelFactory.eliminarReporteRecepcionista(idReporte);
    }
}
