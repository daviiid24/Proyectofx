package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.Reporte;
import co.edu.uniquindio.proyecto.model.Usuario;

import java.util.List;

public class ReporteController {
    ModelFactory modelFactory;
    public ReporteController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Reporte> obtenerReportes() {
        return modelFactory.obtenerReportes();
    }

    public Reporte generarReporte(String descripcion, String tipoReporteTexto){
        return modelFactory.generarReporte(descripcion, tipoReporteTexto);
    }

}
