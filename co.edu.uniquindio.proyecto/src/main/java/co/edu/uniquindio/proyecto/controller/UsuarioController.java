package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.Usuario;

import java.util.List;

public class UsuarioController {
 ModelFactory modelFactory;
 public UsuarioController(){
     modelFactory = ModelFactory.getInstancia();
 }

    public List<Usuario> obtenerUsuarios() {
     return modelFactory.obtenerUsuarios();
    }

    public Usuario crearUsuario(String nombre,
                                String identificacion,
                                String edad,
                                String telefono,
                                String tipoUsuario) {
     return modelFactory.crearUsuario(nombre, identificacion, edad, telefono, tipoUsuario);
    }

    public Usuario actualizarUsuario(String nombre,
                                     String identificacion,
                                     String edad,
                                     String telefono,
                                     String tipoUsuarioStr) {
        return modelFactory.actualizarUsuario(nombre, identificacion, edad, telefono, tipoUsuarioStr);
    }

    public Usuario eliminarUsuario(String identificacion) {
     return modelFactory.eliminarUsuario(identificacion);
    }
}
