package co.edu.uniquindio.proyecto.model;

import java.util.ArrayList;

public class Entrenador extends Persona {
    private ArrayList<Clase> claseAsignadas =new ArrayList();
    private String clasesAsignadasTexto;
    private Gimnasio ownedByGimnasio;

    public Entrenador() {
    }
    public Entrenador(ArrayList<Clase> claseAsignadas) {
        actualizarClasesAsignadasTexto();
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

    public String getClasesAsignadasTexto() {
        return clasesAsignadasTexto;
    }

    public void setClasesAsignadasTexto(String clasesAsignadasTexto) {
        this.clasesAsignadasTexto = clasesAsignadasTexto;
    }
    public void actualizarClasesAsignadasTexto() {
        if (claseAsignadas == null || claseAsignadas.isEmpty()) {
            clasesAsignadasTexto = "Sin clases";
        } else {
            String texto = "";
            for (int i = 0; i < claseAsignadas.size(); i++) {
                texto += claseAsignadas.get(i).getNombre();
                if (i < claseAsignadas.size() - 1) {
                    texto += ", ";
                }
            }
            clasesAsignadasTexto = texto;
        }
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "clasesAsignadas=" + claseAsignadas +
                '}';
    }
}
