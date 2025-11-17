package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.Membresia;
import co.edu.uniquindio.proyecto.model.Usuario;

import java.util.List;

public class MembresiaController {

    ModelFactory modelFactory;
    public MembresiaController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Membresia> obtenerMembresias() {
        return modelFactory.obtenerMembresias();
    }

    public Membresia crearMembresia(String idMembresia, String tipoMembresiaTexto, String duracionTexto, String tipoUsuarioTexto) {
        return modelFactory.crearMembresia(idMembresia, tipoMembresiaTexto, duracionTexto, tipoUsuarioTexto);
    }

    public Membresia actualizarMembresia(String idMembresia, String tipoMembresiaTexto, String duracionTexto, String tipoUsuarioTexto) {
        return modelFactory.actualizarMembresia(idMembresia, tipoMembresiaTexto, duracionTexto, tipoUsuarioTexto);
    }

    public Membresia eliminarMembresia(String idMembresia) {
        return modelFactory.eliminarMembresia(idMembresia);
    }

}
