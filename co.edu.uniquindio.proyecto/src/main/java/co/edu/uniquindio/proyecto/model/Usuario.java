package co.edu.uniquindio.proyecto.model;

import java.util.ArrayList;

public class Usuario extends Persona {
    private TipoUsuario tipoUsuario;
    private Membresia membresia;
    private ArrayList<Reserva> reservas=new ArrayList();
    private ArrayList<Asistencia> asistencias = new ArrayList<>();
    private Gimnasio ownedByGimnasio;

    public Usuario() {}

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Membresia getMembresia() { return membresia; }
    public void setMembresia(Membresia membresia) { this.membresia = membresia; }

    public ArrayList getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList reservas) {
        this.reservas = reservas;
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
}
