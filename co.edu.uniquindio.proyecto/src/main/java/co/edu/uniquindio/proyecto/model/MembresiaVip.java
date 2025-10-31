package co.edu.uniquindio.proyecto.model;

public class MembresiaVip extends Membresia {
    private boolean accesoMaquinas;
    private boolean accesoClases;
    private boolean accesoSpa;
    private boolean entrenadorPersonal;

    public MembresiaVip() {}

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

    public boolean isAccesoSpa() {
        return accesoSpa;
    }

    public void setAccesoSpa(boolean accesoSpa) {
        this.accesoSpa = accesoSpa;
    }

    public boolean isEntrenadorPersonal() {
        return entrenadorPersonal;
    }

    public void setEntrenadorPersonal(boolean entrenadorPersonal) {
        this.entrenadorPersonal = entrenadorPersonal;
    }
}
