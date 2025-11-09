package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.Clase;

import java.util.List;

public class ClasesController {
    ModelFactory modelFactory;
    public ClasesController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Clase> obtenerClases() {
        return modelFactory.obtenerClases();
    }
    public Clase crearClase(String nombre, String horario, String cupoMaximo, String TipoClaseTexto, String idEntrenador) {
        return modelFactory.crearClase(nombre, horario, cupoMaximo, TipoClaseTexto, idEntrenador);
    }

    public Clase actualizarClase(String nombre, String horario, String cupoMaximo, String TipoClaseTexto, String idEntrenador) {
        return modelFactory.actualizarClase(nombre, horario, cupoMaximo, TipoClaseTexto, idEntrenador);
    }

    public Clase eliminarClase(String nombre) {
        return modelFactory.eliminarClase(nombre);
    }

}
