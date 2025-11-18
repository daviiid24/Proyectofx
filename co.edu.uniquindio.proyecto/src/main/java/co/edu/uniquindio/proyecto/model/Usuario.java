package co.edu.uniquindio.proyecto.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Usuario extends Persona {
    private TipoUsuario tipoUsuario;
    private Membresia membresia;
    private ArrayList<Reserva> reservas = new ArrayList();
    private String reservasTexto;
    private ArrayList<Asistencia> asistencias = new ArrayList<>();
    private Gimnasio ownedByGimnasio;

    public Usuario() {
    }

    public Usuario(ArrayList<Reserva> reservas) {
        actualizarReservasTexto();
    }


    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public ArrayList getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList reservas) {
        this.reservas = reservas;
    }

    public String getReservasTexto() {
        return reservasTexto;
    }

    public void setReservasTexto(String reservasTexto) {
        this.reservasTexto = reservasTexto;
    }

    public ArrayList<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void registrarAsistencia(Asistencia asistencia) {
        asistencias.add(asistencia);
    }

    public Gimnasio getOwnedByGimnasio() {
        return ownedByGimnasio;
    }

    public void setOwnedByGimnasio(Gimnasio ownedByGimnasio) {
        this.ownedByGimnasio = ownedByGimnasio;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "tipoUsuario=" + tipoUsuario +
                ", membresia=" + membresia +
                ", reservas=" + reservas +
                ", ownedByGimnasio=" + ownedByGimnasio +
                '}';
    }

    public void actualizarReservasTexto() {
        if (reservas == null || reservas.isEmpty()) {
            reservasTexto = "Sin usuarios registrados";
        } else {
            String texto = "";
            for (int i = 0; i < reservas.size(); i++) {
                texto += reservas.get(i).getIdReserva();
                if (i < reservas.size() - 1) {
                    texto += ", ";
                }
            }
            reservasTexto = texto;
        }
    }
}


