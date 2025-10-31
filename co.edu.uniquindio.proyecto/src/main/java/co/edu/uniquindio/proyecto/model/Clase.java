package co.edu.uniquindio.proyecto.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Clase {
        private String nombre;
        private TipoClase tipoClase;
        private LocalTime horario;
        private int cupoMaximo;
        private Entrenador entrenador;
        private ArrayList<Usuario> listaUsuariosRegistrados =new ArrayList();
    private Gimnasio ownedByGimnasio;

        public Clase() {
        }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoClase getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(TipoClase tipoClase) {
        this.tipoClase = tipoClase;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public int getNumeroReservas() {
        return listaUsuariosRegistrados.size();
    }


    public ArrayList<Usuario> getListaUsuariosRegistrados() {
        return listaUsuariosRegistrados;
    }

    public void setListaUsuariosRegistrados(ArrayList<Usuario> listaUsuariosRegistrados) {
        this.listaUsuariosRegistrados = listaUsuariosRegistrados;
    }

    public Gimnasio getOwnedByGimnasio() {
        return ownedByGimnasio;
    }

    public void setOwnedByGimnasio(Gimnasio ownedByGimnasio) {
        this.ownedByGimnasio = ownedByGimnasio;
    }
}
