package co.edu.uniquindio.proyecto.utils;

import co.edu.uniquindio.proyecto.model.*;

import java.time.LocalDate;
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

        Clase clase1 = new Clase();
        clase1.setNombre("Entrenamiento Funcional");
        clase1.setTipoClase(TipoClase.CROSSFIT);
        clase1.setHorario("06:00 AM - 07:00 AM");
        clase1.setCupoMaximo(15);
        clase1.setEntrenador(entrenador1);
        clase1.setOwnedByGimnasio(gimnasio);
        clase1.getListaUsuariosRegistrados().add(usuario1);
        clase1.actualizarUsuariosRegistradosTexto();

        Clase clase2 = new Clase();
        clase2.setNombre("Yoga Relajante");
        clase2.setTipoClase(TipoClase.YOGA);
        clase2.setHorario("07:30 AM - 08:30 AM");
        clase2.setCupoMaximo(10);
        clase2.setEntrenador(entrenador2);
        clase2.setOwnedByGimnasio(gimnasio);
        clase2.getListaUsuariosRegistrados().add(usuario2);
        clase2.actualizarUsuariosRegistradosTexto();

        Reserva reserva = new Reserva();
        reserva.setIdReserva("R001");
        reserva.setUsuario(usuario1);
        reserva.setClase(clase1);
        reserva.setFechaReserva(LocalDate.of(2024, 5, 20));
        reserva.setEstado(Estado.ACTIVA);

        gimnasio.getListaReservas().add(reserva);



        gimnasio.getListaClases().add(clase1);
        gimnasio.getListaClases().add(clase2);

        clasesEntrenador1.add(clase1);
        clasesEntrenador2.add(clase2);


        return gimnasio;
    }
}

