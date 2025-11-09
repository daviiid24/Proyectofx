package co.edu.uniquindio.proyecto.utils;

import co.edu.uniquindio.proyecto.model.*;

import java.util.ArrayList;


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

        Entrenador entrenador1 = new Entrenador();
        entrenador1.setNombre("Andrés López");
        entrenador1.setEdad(35);
        entrenador1.setIdentificacion("2001");
        entrenador1.setTelefono("3209876543");
        entrenador1.setOwnedByGimnasio(gimnasio);

        ArrayList<Clase> clasesEntrenador1 = new ArrayList<>();
        entrenador1.setClaseAsignadas(clasesEntrenador1);

        Entrenador entrenador2 = new Entrenador();
        entrenador2.setNombre("María Fernández");
        entrenador2.setEdad(29);
        entrenador2.setIdentificacion("2002");
        entrenador2.setTelefono("3004567890");
        entrenador2.setOwnedByGimnasio(gimnasio);

        ArrayList<Clase> clasesEntrenador2 = new ArrayList<>();
        entrenador2.setClaseAsignadas(clasesEntrenador2);

        gimnasio.getListaEntrenadores().add(entrenador1);
        gimnasio.getListaEntrenadores().add(entrenador2);



            return gimnasio;
        }
    }

