package co.edu.uniquindio.proyecto.utils;

import co.edu.uniquindio.proyecto.model.Gimnasio;
import co.edu.uniquindio.proyecto.model.TipoUsuario;
import co.edu.uniquindio.proyecto.model.Usuario;

public class DataUtil {

    public static Gimnasio inicializarDatos() {
            Gimnasio gimnasio = new Gimnasio();
            Usuario usuario1 = new Usuario();
            usuario1.setNombre("Laura");
            usuario1.setEdad(25);
            usuario1.setIdentificacion("1001");
            usuario1.setTelefono("3105678901");
            usuario1.setTipoUsuario(TipoUsuario.ESTUDIANTE);

            Usuario usuario2 = new Usuario();
            usuario2.setNombre("Carlos");
            usuario2.setEdad(30);
            usuario2.setIdentificacion("1002");
            usuario2.setTelefono("3112345678");
            usuario2.setTipoUsuario(TipoUsuario.EXTERNO);

            gimnasio.getListaUsuarios().add(usuario1);
            gimnasio.getListaUsuarios().add(usuario2);

            return gimnasio;
        }
    }

