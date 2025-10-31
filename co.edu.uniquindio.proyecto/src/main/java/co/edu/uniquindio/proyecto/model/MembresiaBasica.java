package co.edu.uniquindio.proyecto.model;

public class MembresiaBasica extends Membresia {
    private boolean accesoMaquinas;
    public MembresiaBasica() {}

    public boolean isAccesoMaquinas() {
        return accesoMaquinas;
    }

    public void setAccesoMaquinas(boolean accesoMaquinas) {
        this.accesoMaquinas = accesoMaquinas;
    }
}
