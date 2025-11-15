package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.Reserva;
import co.edu.uniquindio.proyecto.model.Usuario;

import java.util.List;

public class ReservaController {
    ModelFactory modelFactory;
    public ReservaController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Reserva> obtenerReservas() {
        return modelFactory.obtenerReservas();
    }

    public Reserva crearReserva(String idReserva, String idUsuario, String nombreClase) {
        return modelFactory.crearReserva(idReserva, idUsuario, nombreClase);
    }

    public Reserva actualizarReserva(String idReserva, String idUsuario, String nombreClase) {
        return modelFactory.actualizarReserva(idReserva, idUsuario, nombreClase);
    }

    public Reserva eliminarReserva(String idReserva) {
        return modelFactory.eliminarReserva(idReserva);
    }
}
