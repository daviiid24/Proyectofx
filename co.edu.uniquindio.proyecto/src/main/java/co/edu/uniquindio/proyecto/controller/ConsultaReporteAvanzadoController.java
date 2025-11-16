package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;

public class ConsultaReporteAvanzadoController {
    ModelFactory modelFactory;

    public ConsultaReporteAvanzadoController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public String buscarReporte(String idReporte) {
        return modelFactory.buscarReporteAdministrador(idReporte);
    }

}
