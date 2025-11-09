package co.edu.uniquindio.proyecto.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Clase {
    private String nombre;
    private TipoClase tipoClase;
    private String horario;
    private int cupoMaximo;
    private Entrenador entrenador;
    private ArrayList<Usuario> listaUsuariosRegistrados =new ArrayList();
    private String usuariosRegistradosTexto;
    private Gimnasio ownedByGimnasio;

    public Clase() {
    }
    public Clase(ArrayList<Usuario> listaUsuariosRegistrados) {
        actualizarUsuariosRegistradosTexto();
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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

    public String getUsuariosRegistradosTexto() {
        return usuariosRegistradosTexto;
    }

    public void setUsuariosRegistradosTexto(String usuariosRegistradosTexto) {
        this.usuariosRegistradosTexto = usuariosRegistradosTexto;
    }
    public void actualizarUsuariosRegistradosTexto() {
        if (listaUsuariosRegistrados == null || listaUsuariosRegistrados.isEmpty()) {
            usuariosRegistradosTexto = "Sin usuarios registrados";
        } else {
            String texto = "";
            for (int i = 0; i < listaUsuariosRegistrados.size(); i++) {
                texto += listaUsuariosRegistrados.get(i);
                if (i < listaUsuariosRegistrados.size() - 1) {
                    texto += ", ";
                }
            }
            usuariosRegistradosTexto = texto;
        }
    }
}
