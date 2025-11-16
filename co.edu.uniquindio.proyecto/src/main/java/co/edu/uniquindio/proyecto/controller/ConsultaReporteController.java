package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;

public class ConsultaReporteController {
    ModelFactory modelFactory;

    public ConsultaReporteController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public String buscarReporte(String idReporte) {
        return modelFactory.buscarReporteRecepcionista(idReporte);
    }
}
