package co.edu.uniquindio.proyecto.model;

public class MembresiaPremium extends Membresia{
    private boolean accesoMaquinas;
    private boolean accesoClases;
    public MembresiaPremium() {}

    public boolean isAccesoMaquinas() {
        return accesoMaquinas;
    }

    public void setAccesoMaquinas(boolean accesoMaquinas) {
        this.accesoMaquinas = accesoMaquinas;
    }

    public boolean isAccesoClases() {
        return accesoClases;
    }

    public void setAccesoClases(boolean accesoClases) {
        this.accesoClases = accesoClases;
    }
}
