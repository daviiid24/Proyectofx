package co.edu.uniquindio.proyecto.factory;

import co.edu.uniquindio.proyecto.model.*;
import co.edu.uniquindio.proyecto.utils.DataUtil;

import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private final Gimnasio gimnasio;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private ModelFactory(){
        gimnasio = DataUtil.inicializarDatos();
    }

    public List<Usuario> obtenerUsuarios() {
        return gimnasio.getListaUsuarios();
    }

    public Usuario crearUsuario(String nombre, String identificacion, String edad, String telefono, String tipoUsuario) {
        return gimnasio.crearUsuario(nombre, identificacion, Integer.parseInt(edad), telefono, TipoUsuario.valueOf(tipoUsuario));
    }

    public Usuario actualizarUsuario(String nombre, String identificacion, String edad, String telefono, String tipoUsuarioStr) {
        return gimnasio.actualizarUsuario(nombre, identificacion, Integer.parseInt(edad), telefono, TipoUsuario.valueOf(tipoUsuarioStr));
    }

    public Usuario eliminarUsuario(String identificacion) {
        return gimnasio.eliminarUsuario(identificacion);
    }

    public List<Entrenador> obtenerEntrenadores() {
        return gimnasio.getListaEntrenadores();
    }

    public Entrenador crearEntrenador(String nombre, String identificacion, String edad, String telefono) {
        return gimnasio.crearEntrenador(nombre, identificacion, Integer.parseInt(edad), telefono);
    }

    public Entrenador actualizarEntrenador(String nombre, String identificacion, String edad, String telefono) {
        return gimnasio.actualizarEntrenador(nombre, identificacion, Integer.parseInt(edad), telefono);
    }

    public Entrenador eliminarEntrenador(String identificacion) {
        return gimnasio.eliminarEntrenador(identificacion);
    }
    public List<Clase> obtenerClases() {
        return gimnasio.getListaClases();
    }

    public Clase crearClase(String nombre, String horario, String cupoMaximo, String TipoClaseTexto, String idEntrenador) {
        return gimnasio.crearClase(nombre, Integer.parseInt(cupoMaximo), idEntrenador, TipoClase.valueOf(TipoClaseTexto), horario);
    }

    public Clase actualizarClase(String nombre, String horario, String cupoMaximo, String TipoClaseTexto, String idEntrenador) {
        return gimnasio.actualizarClase(nombre, Integer.parseInt(cupoMaximo), idEntrenador, TipoClase.valueOf(TipoClaseTexto), horario);
    }

    public Clase eliminarClase(String nombre) {
        return gimnasio.eliminarClase(nombre);
    }

}
