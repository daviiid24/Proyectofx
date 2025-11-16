package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;

public class AsistenciaAdministradorController {
    ModelFactory modelFactory;

    public AsistenciaAdministradorController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public String validarUsuarioAvanzado(String nombre, String idUsuario, String telefono) {
        return modelFactory.validarUsuarioAvanzado(nombre, idUsuario, telefono);
    }
}
