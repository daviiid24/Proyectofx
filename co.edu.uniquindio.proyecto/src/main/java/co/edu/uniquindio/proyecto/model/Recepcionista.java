package co.edu.uniquindio.proyecto.model;

public class Recepcionista {
    private String nombre;
    private Gimnasio ownedByGimnasio;

    public Recepcionista() {}


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Gimnasio getOwnedByGimnasio() {
        return ownedByGimnasio;
    }

    public void setOwnedByGimnasio(Gimnasio ownedByGimnasio) {
        this.ownedByGimnasio = ownedByGimnasio;
    }

    @Override
    public String toString() {
        return "Recepcionista{" +
                "nombre='" + nombre + '\'' +
                ", ownedByGimnasio=" + ownedByGimnasio +
                '}';
    }
}
