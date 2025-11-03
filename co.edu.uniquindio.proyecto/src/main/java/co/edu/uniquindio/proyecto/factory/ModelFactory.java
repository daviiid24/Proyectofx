package co.edu.uniquindio.proyecto.factory;

import co.edu.uniquindio.proyecto.model.Gimnasio;
import co.edu.uniquindio.proyecto.model.TipoUsuario;
import co.edu.uniquindio.proyecto.model.Usuario;
import co.edu.uniquindio.proyecto.utils.DataUtil;

import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Gimnasio gimnasio;

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
}
