package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;

public class AsistenciaRecepcionistaController {
    ModelFactory modelFactory;

    public AsistenciaRecepcionistaController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public String validarUsuario(String idUsuario) {
        return modelFactory.validarUsuario(idUsuario);
    }
}
