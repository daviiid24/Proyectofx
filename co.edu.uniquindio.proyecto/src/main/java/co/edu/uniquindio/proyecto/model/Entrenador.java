package co.edu.uniquindio.proyecto.model;

import java.util.ArrayList;

public class Entrenador extends Persona {
    private ArrayList<Clase> claseAsignadas =new ArrayList();
    private Gimnasio ownedByGimnasio;

    public Entrenador() {
    }

    public ArrayList<Clase> getClasesAsignadas() {
        return claseAsignadas;
    }

    public void setClasesAsignadas(ArrayList<Clase> claseAsignadas) {
        this.claseAsignadas = claseAsignadas;
    }

    public ArrayList<Clase> getClaseAsignadas() {
        return claseAsignadas;
    }

    public void setClaseAsignadas(ArrayList<Clase> claseAsignadas) {
        this.claseAsignadas = claseAsignadas;
    }

    public Gimnasio getOwnedByGimnasio() {
        return ownedByGimnasio;
    }

    public void setOwnedByGimnasio(Gimnasio ownedByGimnasio) {
        this.ownedByGimnasio = ownedByGimnasio;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "clasesAsignadas=" + claseAsignadas +
                '}';
    }
}
