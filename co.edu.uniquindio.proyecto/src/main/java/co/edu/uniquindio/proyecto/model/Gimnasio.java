package co.edu.uniquindio.proyecto.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Gimnasio {
    private String nombre;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ArrayList<Membresia> listaMembresias = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    private ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();
    private ArrayList<Clase> listaClases = new ArrayList<>();
    private ArrayList<ReporteRecepcionista> listaReportesRecepcionista = new ArrayList<>();
    private ArrayList<ReporteAdministrador> listaReportesAdministrador = new ArrayList<>();
    private Administrador administrador;
    private Recepcionista recepcionista;

    public Gimnasio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Entrenador> getListaEntrenadores() {
        return listaEntrenadores;
    }

    public void setListaEntrenadores(ArrayList<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    public ArrayList<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(ArrayList<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    public ArrayList<Membresia> getListaMembresias() {
        return listaMembresias;
    }

    public void setListaMembresias(ArrayList<Membresia> listaMembresias) {
        this.listaMembresias = listaMembresias;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public ArrayList<ReporteRecepcionista> getListaReportesRecepcionista() {
        return listaReportesRecepcionista;
    }

    public void setListaReportesRecepcionista(ArrayList<ReporteRecepcionista> listaReportesRecepcionista) {
        this.listaReportesRecepcionista = listaReportesRecepcionista;
    }

    public ArrayList<ReporteAdministrador> getListaReportesAdministrador() {
        return listaReportesAdministrador;
    }

    public void setListaReportesAdministrador(ArrayList<ReporteAdministrador> listaReportesAdministrador) {
        this.listaReportesAdministrador = listaReportesAdministrador;
    }

    @Override
    public String toString() {
        return "Gimnasio{" +
                "nombre='" + nombre + '\'' +
                ", listaUsuarios=" + listaUsuarios +
                ", listaMembresias=" + listaMembresias +
                ", listaReservas=" + listaReservas +
                ", listaEntrenadores=" + listaEntrenadores +
                ", listaClases=" + listaClases +
                ", listaReportesRecepcionista=" + listaReportesRecepcionista +
                ", listaReportesAdministrador=" + listaReportesAdministrador +
                ", administrador=" + administrador +
                ", recepcionista=" + recepcionista +
                '}';
    }

    public String validarUsuario(String idUsuario) {
        Usuario usuarioEncontrado = obtenerUsuario(idUsuario);

        if (usuarioEncontrado == null) {
            return "El usuario no existe.";
        }

        Membresia membresia = usuarioEncontrado.getMembresia();
        if (membresia == null) {
            return "El usuario no tiene una membresía asignada.";
        }

        if (membresia.getFechaVencimiento().isBefore(LocalDate.now()) ||
                membresia.getEstado() == Estado.INACTIVA) {
            return "La membresía del usuario está vencida o inactiva.";
        }

        usuarioEncontrado.registrarAsistencia(
                new Asistencia(LocalDate.now(), "Ingreso al gimnasio validado")
        );

        return "Ingreso al gimnasio validado.";
    }

    public ReporteRecepcionista generarReporteRecepcionista(TipoReporte tipo, String descripcionIngresada) {

        ReporteRecepcionista reporteRecepcionista = new ReporteRecepcionista();
        reporteRecepcionista.setIdReporte("REP" + (listaReportesRecepcionista.size() + 1));
        reporteRecepcionista.setTipoRecepcionista(tipo);

        switch (tipo) {
            case USUARIOS_ACTIVOS ->
                    reporteRecepcionista.setContenidoGenerado(generarReporteUsuariosActivos());

            case CLASES_MAS_RESERVADAS ->
                    reporteRecepcionista.setContenidoGenerado(generarReporteClasesMasReservadas());

            case VENCIMIENTO_MEMBRESIAS ->
                    reporteRecepcionista.setContenidoGenerado(generarReporteVencimientoMembresias());
        }

        reporteRecepcionista.setDescripcion(descripcionIngresada);

        listaReportesRecepcionista.add(reporteRecepcionista);

        return reporteRecepcionista;
    }

    public ReporteRecepcionista eliminarReporteRecepcionista(String idReporte) {
        ReporteRecepcionista reporteEncontrado = obtenerReporteRecepcionista(idReporte);
        if (reporteEncontrado != null) {
            getListaReportesRecepcionista().remove(reporteEncontrado);
            return reporteEncontrado;
        } else {
            return null;
        }
    }

    public ReporteRecepcionista actualizarReporteRecepcionista(String idReporte, TipoReporte tipo, String descripcionIngresada) {

        ReporteRecepcionista reporteEncontrado = obtenerReporteRecepcionista(idReporte);

        reporteEncontrado.setIdReporte(idReporte);
        reporteEncontrado.setTipoRecepcionista(tipo);

        switch (tipo) {
            case USUARIOS_ACTIVOS ->
                    reporteEncontrado.setContenidoGenerado(generarReporteUsuariosActivos());

            case CLASES_MAS_RESERVADAS ->
                    reporteEncontrado.setContenidoGenerado(generarReporteClasesMasReservadas());

            case VENCIMIENTO_MEMBRESIAS ->
                    reporteEncontrado.setContenidoGenerado(generarReporteVencimientoMembresias());
        }

        reporteEncontrado.setDescripcion(descripcionIngresada);

        return reporteEncontrado;
    }

    public ReporteRecepcionista obtenerReporteRecepcionista(String idReporte) {
        ReporteRecepcionista reporteEncontrado = null;
        for (ReporteRecepcionista reporteRecepcionista : getListaReportesRecepcionista()) {
            if (reporteRecepcionista.getIdReporte().equalsIgnoreCase(idReporte)) {
                reporteEncontrado = reporteRecepcionista;
                break;
            }
        }

        return reporteEncontrado;
    }

    private String generarReporteUsuariosActivos() {

        String resultado = "Usuarios activos:\n";

        for (Usuario usuario : listaUsuarios) {

            if (usuario.getMembresia() != null &&
                    usuario.getMembresia().getFechaVencimiento().isAfter(LocalDate.now())) {

                resultado = resultado
                        + usuario.getNombre()
                        + " - "
                        + usuario.getIdentificacion()
                        + "\n";
            }
        }
        return resultado;
    }


    private String generarReporteClasesMasReservadas() {

        String reporte = "Clases más reservadas:\n";

        for (int i = 0; i < listaClases.size() - 1; i++) {
            for (int j = i + 1; j < listaClases.size(); j++) {

                if (listaClases.get(i).getNumeroReservas() < listaClases.get(j).getNumeroReservas()) {
                    Clase temp = listaClases.get(i);
                    listaClases.set(i, listaClases.get(j));
                    listaClases.set(j, temp);
                }
            }
        }

        for (Clase clase : listaClases) {
            reporte = reporte
                    + clase.getNombre()
                    + " - Reservas: "
                    + clase.getNumeroReservas()
                    + "\n";
        }

        return reporte;
    }


    private String generarReporteVencimientoMembresias() {
        String reporte = "Vencimiento de membresías:\n";

        LocalDate hoy = LocalDate.now();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getMembresia() != null) {
                LocalDate vencimiento = usuario.getMembresia().getFechaVencimiento();
                long diasRestantes = ChronoUnit.DAYS.between(hoy, vencimiento);

                if (diasRestantes <= 10 && diasRestantes >= 0) {
                    reporte += "La membresia de " + usuario.getNombre() + " con el ID " + usuario.getIdentificacion() + " vence en "
                            + diasRestantes + " días\n";
                }
            }
        }

        return reporte;
    }

    public ReporteAdministrador generarReporteAdministrador(TipoReporteAvanzado tipo, String descripcionIngresada) {

        ReporteAdministrador reporteAdministrador = new ReporteAdministrador();
        reporteAdministrador.setIdReporte("REP" + (listaReportesAdministrador.size() + 1) + "A");
        reporteAdministrador.setTipoAdministrador(tipo);

        switch (tipo) {
            case ASISTENCIAS_USUARIO ->
                    reporteAdministrador.setContenidoGenerado(generarReporteAsistenciasUsuario());

            case INGRESOS_POR_MEMBRESIA ->
                    reporteAdministrador.setContenidoGenerado(generarReporteIngresosPorMembresia());

            case CLASES_POPULARES ->
                    reporteAdministrador.setContenidoGenerado(generarReporteClasesPopulares());
        }

        reporteAdministrador.setDescripcion(descripcionIngresada);

        listaReportesAdministrador.add(reporteAdministrador);

        return reporteAdministrador;
    }

    public ReporteAdministrador eliminarReporteAdministrador(String idReporte) {
        ReporteAdministrador reporteEncontrado = obtenerReporteAdministrador(idReporte);
        if (reporteEncontrado != null) {
            getListaReportesAdministrador().remove(reporteEncontrado);
            return reporteEncontrado;
        } else {
            return null;
        }
    }

    public ReporteAdministrador actualizarReporteAdministrador(String idReporte, TipoReporteAvanzado tipo, String descripcionIngresada) {

        ReporteAdministrador reporteEncontrado = obtenerReporteAdministrador(idReporte);

        reporteEncontrado.setIdReporte(idReporte);
        reporteEncontrado.setTipoAdministrador(tipo);

        switch (tipo) {
            case ASISTENCIAS_USUARIO ->
                    reporteEncontrado.setContenidoGenerado(generarReporteUsuariosActivos());

            case INGRESOS_POR_MEMBRESIA ->
                    reporteEncontrado.setContenidoGenerado(generarReporteClasesMasReservadas());

            case CLASES_POPULARES ->
                    reporteEncontrado.setContenidoGenerado(generarReporteVencimientoMembresias());
        }

        reporteEncontrado.setDescripcion(descripcionIngresada);

        return reporteEncontrado;
    }

    public ReporteAdministrador obtenerReporteAdministrador(String idReporte) {
        ReporteAdministrador reporteEncontrado = null;
        for (ReporteAdministrador reporteAdministrador : getListaReportesAdministrador()) {
            if (reporteAdministrador.getIdReporte().equalsIgnoreCase(idReporte)) {
                reporteEncontrado = reporteAdministrador;
                break;
            }
        }

        return reporteEncontrado;
    }

    public String generarReporteAsistenciasUsuario() {

        String reporte = "REPORTE DE ASISTENCIAS POR USUARIO\n\n";

        if (listaUsuarios.isEmpty()) {
            return "No hay usuarios registrados en el gimnasio.";
        }

        for (Usuario usuario : listaUsuarios) {

            reporte += "--------------------------------------------\n";
            reporte += "Usuario: " + usuario.getNombre() + "\n";
            reporte += "ID: " + usuario.getIdentificacion() + "\n\n";

            if (usuario.getAsistencias().isEmpty()) {
                reporte += "No tiene asistencias registradas.\n\n";
            } else {
                reporte += "Total asistencias: " + usuario.getAsistencias().size() + "\n";
                reporte += "Fechas de asistencia:\n";

                for (Asistencia asistencia : usuario.getAsistencias()) {
                    reporte += "  - " + asistencia.getFecha() + "\n";
                }

                reporte += "\n";
            }
        }

        return reporte;
    }


    public String generarReporteIngresosPorMembresia() {

        double totalMensual = 0;
        double totalTrimestral = 0;
        double totalAnual = 0;

        for (Usuario usuario : listaUsuarios) {
            Membresia membresia = usuario.getMembresia();
            if (membresia != null) {
                switch (membresia.getDuracion()) {
                    case MENSUAL:
                        totalMensual += membresia.getCosto();
                        break;
                    case TRIMESTRAL:
                        totalTrimestral += membresia.getCosto();
                        break;
                    case ANUAL:
                        totalAnual += membresia.getCosto();
                        break;
                }
            }
        }

        double total = totalMensual + totalTrimestral + totalAnual;

        String reporte = "REPORTE DE INGRESOS POR MEMBRESÍA\n\n";

        reporte += "Total ingresos mensuales: $" + totalMensual + "\n";
        reporte += "Total ingresos trimestrales: $" + totalTrimestral + "\n";
        reporte += "Total ingresos anuales: $" + totalAnual + "\n";
        reporte += "Total ingresos por todas las membresías: $" + total + "\n";

        return reporte;
    }

    public String generarReporteClasesPopulares() {

        String reporte = "REPORTE DE CLASES MÁS POPULARES\n\n";

        if (listaClases == null || listaClases.isEmpty()) {
            reporte += "No hay clases registradas en el sistema.\n";
            return reporte;
        }

        ArrayList<Clase> clasesOrdenadas = new ArrayList<>(listaClases);

        for (int i = 0; i < clasesOrdenadas.size() - 1; i++) {
            for (int j = i + 1; j < clasesOrdenadas.size(); j++) {
                if (clasesOrdenadas.get(i).getNumeroReservas() < clasesOrdenadas.get(j).getNumeroReservas()) {
                    Clase temp = clasesOrdenadas.get(i);
                    clasesOrdenadas.set(i, clasesOrdenadas.get(j));
                    clasesOrdenadas.set(j, temp);
                }
            }
        }

        for (Clase clase : clasesOrdenadas) {
            reporte += "Clase: " + clase.getNombre() + "\n";
            reporte += "Tipo: " + clase.getTipoClase() + "\n";
            reporte += "Entrenador: " + (clase.getEntrenador() != null ? clase.getEntrenador().getNombre() : "Sin asignar") + "\n";
            reporte += "Horario: " + clase.getHorario() + "\n";
            reporte += "Cupo máximo: " + clase.getCupoMaximo() + "\n";
            reporte += "Usuarios inscritos: " + clase.getNumeroReservas() + "\n\n";
        }

        return reporte;
    }

    public String validarUsuarioAvanzado(String nombre, String identificacion, String telefono) {

        Usuario usuarioEncontrado = obtenerUsuario(identificacion);
        if (usuarioEncontrado == null) {
            return "El usuario no existe.";
        }

        Membresia membresia = usuarioEncontrado.getMembresia();
        if (membresia == null) {
            return "El usuario no tiene una membresía asignada.";
        }

        if (membresia.getFechaVencimiento().isBefore(LocalDate.now()) ||
                membresia.getEstado() == Estado.INACTIVA) {
            return "La membresía del usuario está vencida o inactiva.";
        }

        boolean datosCorrectos =
                usuarioEncontrado.getNombre().equals(nombre) &&
                        usuarioEncontrado.getIdentificacion().equals(identificacion) &&
                        usuarioEncontrado.getTelefono().equals(telefono);

        if (!datosCorrectos) {
            return "Los datos ingresados no coinciden con los registros.";
        }

        usuarioEncontrado.registrarAsistencia(
                new Asistencia(LocalDate.now(), "Ingreso al gimnasio validado")
        );

        return "Ingreso al gimnasio validado.";
    }




    public Usuario crearUsuario(String nombre, String identificacion, int edad,
                                String telefono, TipoUsuario tipoUsuario) {
        Usuario usuarioEncontrado = obtenerUsuario(identificacion);
        if (usuarioEncontrado == null) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setIdentificacion(identificacion);
            usuario.setEdad(edad);
            usuario.setTelefono(telefono);
            usuario.setTipoUsuario(tipoUsuario);
            getListaUsuarios().add(usuario);

            return usuario;
        } else {
            return null;
        }
    }

    public Usuario eliminarUsuario(String idEliminar) {
        Usuario usuarioEncontrado = obtenerUsuario(idEliminar);
        if (usuarioEncontrado != null) {
            getListaUsuarios().remove(usuarioEncontrado);
            return usuarioEncontrado;
        } else {
            return null;
        }
    }

    public Usuario actualizarUsuario(String nombre, String identificacion, int edad,
                                     String telefono, TipoUsuario tipoUsuario) {
        Usuario usuarioEncontrado = obtenerUsuario(identificacion);
        if (usuarioEncontrado.getIdentificacion().equalsIgnoreCase(identificacion)) {
            usuarioEncontrado.setNombre(nombre);
            usuarioEncontrado.setIdentificacion(identificacion);
            usuarioEncontrado.setEdad(edad);
            usuarioEncontrado.setTelefono(telefono);
            usuarioEncontrado.setTipoUsuario(tipoUsuario);

            return usuarioEncontrado;
        } else {
            return null;
        }
    }

    public Usuario obtenerUsuario(String identificacion) {
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : getListaUsuarios()) {
            if (usuario.getIdentificacion().equalsIgnoreCase(identificacion)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        return usuarioEncontrado;
    }

    //CRUD ENTRENADOR
    public Entrenador crearEntrenador(String nombre, String identificacion, int edad,
                                String telefono) {
        Entrenador entrenadorEncontrado = obtenerEntrenador(identificacion);
        if (entrenadorEncontrado == null) {
            Entrenador entrenador = new Entrenador();
            entrenador.setNombre(nombre);
            entrenador.setIdentificacion(identificacion);
            entrenador.setEdad(edad);
            entrenador.setTelefono(telefono);
            getListaEntrenadores().add(entrenador);

            entrenador.actualizarClasesAsignadasTexto();

            return entrenador;
        } else {
            return null;
        }
    }

    public Entrenador eliminarEntrenador(String idEliminar) {
        Entrenador entrenadorEncontrado = obtenerEntrenador(idEliminar);
        if (entrenadorEncontrado != null) {
            getListaEntrenadores().remove(entrenadorEncontrado);
            return entrenadorEncontrado;
        } else {
            return null;
        }
    }

    public Entrenador actualizarEntrenador(String nombre, String identificacion, int edad,
                                     String telefono) {
        Entrenador entrenadorEncontrado = obtenerEntrenador(identificacion);
        if (entrenadorEncontrado.getIdentificacion().equalsIgnoreCase(identificacion)) {
            entrenadorEncontrado.setNombre(nombre);
            entrenadorEncontrado.setIdentificacion(identificacion);
            entrenadorEncontrado.setEdad(edad);
            entrenadorEncontrado.setTelefono(telefono);

            entrenadorEncontrado.actualizarClasesAsignadasTexto();

            return entrenadorEncontrado;
        } else {
            return null;
        }
    }

    public Entrenador obtenerEntrenador(String identificacion) {
        Entrenador entrenadorEncontrado = null;
        for (Entrenador entrenador : getListaEntrenadores()) {
            if (entrenador.getIdentificacion().equalsIgnoreCase(identificacion)) {
                entrenadorEncontrado = entrenador;
                break;
            }
        }

        return entrenadorEncontrado;
    }

    //CRUD CLASE
    public Clase crearClase(String nombre,
                              int cupoMaximo,
                              String identificacion,
                              TipoClase tipoClase, String horario) {
        Clase claseEncontrada = obtenerClase(nombre);

        Entrenador entrenadorEncontrado = obtenerEntrenador(identificacion);
        if (entrenadorEncontrado == null) {
            return null;
        }

        if (claseEncontrada == null) {
            Clase clase = new Clase();


            clase.setNombre(nombre);
            clase.setCupoMaximo(cupoMaximo);
            clase.setHorario(horario);
            clase.setTipoClase(tipoClase);
            clase.setEntrenador(entrenadorEncontrado);
            getListaClases().add(clase);
            if (!entrenadorEncontrado.getClaseAsignadas().contains(clase)) {
                entrenadorEncontrado.getClaseAsignadas().add(clase);
            }

            return clase;
        } else {
            return null;
        }
    }

    public Clase eliminarClase(String nombre) {
        Clase claseEncontrada = obtenerClase(nombre);
        if (claseEncontrada != null) {
            getListaClases().remove(claseEncontrada);
            return claseEncontrada;
        } else {
            return null;
        }
    }

    public Clase actualizarClase(String nombre,
                                 int cupoMaximo,
                                 String identificacion,
                                 TipoClase tipoClase, String horario) {

        Entrenador entrenadorEncontrado = obtenerEntrenador(identificacion);
        if (entrenadorEncontrado == null) {
            return null;
        }

        Clase claseEncontrada = obtenerClase(nombre);
        if (claseEncontrada.getNombre().equalsIgnoreCase(nombre)) {
            claseEncontrada.setNombre(nombre);
            claseEncontrada.setCupoMaximo(cupoMaximo);
            claseEncontrada.setHorario(horario);
            claseEncontrada.setTipoClase(tipoClase);
            claseEncontrada.setEntrenador(entrenadorEncontrado);

            return claseEncontrada;
        } else {
            return null;
        }
    }

    public Clase obtenerClase(String nombre) {
        Clase claseEncontrada = null;
        for (Clase clase : getListaClases()) {
            if (clase.getNombre().equalsIgnoreCase(nombre)) {
                claseEncontrada = clase;
                break;
            }
        }

        return claseEncontrada;
    }
    //CRUD MEMBRESIA
    public Membresia crearMembresia(String idMembresia, TipoMembresia tipoMembresia, Duracion duracion, TipoUsuario tipoUsuario) {

        Usuario usuarioEncontrado = obtenerUsuario(idMembresia);

        if (usuarioEncontrado == null) {
            System.out.println("No existe un usuario con ese ID");
            return null;
        }

        Membresia membresiaEncontrada = obtenerMembresia(idMembresia);

        if (membresiaEncontrada != null) {
            return null;
        }

        Membresia membresia;

        switch (tipoMembresia) {
            case BASICA -> membresia = new MembresiaBasica();
            case PREMIUM -> membresia = new MembresiaPremium();
            case VIP -> membresia = new MembresiaVip();
            default -> {
                System.out.println("Tipo de membresía inválido");
                return null;
            }
        }


        double costo = calcularCosto(tipoMembresia, duracion, tipoUsuario);

        membresia.setIdMembresia(idMembresia);
        membresia.setTipoMembresia(tipoMembresia);
        membresia.setDuracion(duracion);
        membresia.setTipoUsuario(tipoUsuario);
        membresia.setCosto(costo);
        membresia.setFechaInicio(LocalDate.now());
        membresia.setEstado(Estado.ACTIVA);

        LocalDate fechaVencimiento;

        if (duracion==Duracion.MENSUAL) {
                fechaVencimiento=LocalDate.now().plusMonths(1);
            } else if (duracion == Duracion.TRIMESTRAL) {
                fechaVencimiento=LocalDate.now().plusMonths(3);
            } else if (duracion == Duracion.ANUAL) {
                fechaVencimiento=LocalDate.now().plusYears(1);
            } else {
                fechaVencimiento=LocalDate.now().plusMonths(1);
            }
            membresia.setFechaVencimiento(fechaVencimiento);
            membresia.setEstado(Estado.ACTIVA);

            getListaMembresias().add(membresia);
            usuarioEncontrado.setMembresia(membresia);

            return membresia;
    }

    public Membresia eliminarMembresia(String idEliminar) {
            Membresia membresiaEncontrada = obtenerMembresia(idEliminar);
            Usuario usuarioEncontrado = obtenerUsuario(idEliminar);
            if (membresiaEncontrada != null) {
                getListaMembresias().remove(membresiaEncontrada);
                usuarioEncontrado.setMembresia(null);
                return membresiaEncontrada;
            } else {
                return null;
            }
        }

    public Membresia actualizarMembresia(String idMembresia, TipoMembresia tipoMembresia, Duracion duracion, TipoUsuario tipoUsuario) {

        Usuario usuarioEncontrado = obtenerUsuario(idMembresia);

        if (usuarioEncontrado == null) {
            System.out.println("No existe un usuario con ese ID");
            return null;
        }

        Membresia membresiaEncontrada = obtenerMembresia(idMembresia);
        if (membresiaEncontrada == null) {
            return null;
        }

        double costo = calcularCosto(tipoMembresia, duracion, tipoUsuario);

        membresiaEncontrada.setTipoMembresia(tipoMembresia);
        membresiaEncontrada.setIdMembresia(idMembresia);
        membresiaEncontrada.setTipoMembresia(tipoMembresia);
        membresiaEncontrada.setDuracion(duracion);
        membresiaEncontrada.setTipoUsuario(tipoUsuario);
        membresiaEncontrada.setCosto(costo);
        membresiaEncontrada.setFechaInicio(LocalDate.now());
        membresiaEncontrada.setEstado(Estado.ACTIVA);

        LocalDate fechaVencimiento;

        if (duracion==Duracion.MENSUAL) {
            fechaVencimiento=LocalDate.now().plusMonths(1);
        } else if (duracion == Duracion.TRIMESTRAL) {
            fechaVencimiento=LocalDate.now().plusMonths(3);
        } else if (duracion == Duracion.ANUAL) {
            fechaVencimiento=LocalDate.now().plusYears(1);
        } else {
            fechaVencimiento=LocalDate.now().plusMonths(1);
        }
        membresiaEncontrada.setFechaVencimiento(fechaVencimiento);
        membresiaEncontrada.setEstado(Estado.ACTIVA);
        usuarioEncontrado.setMembresia(membresiaEncontrada);

        return membresiaEncontrada;
    }

    public Membresia obtenerMembresia(String idMembresia) {
        Membresia membresiaEncontrada=null;
        for (Membresia membresia : getListaMembresias()) {
            if (membresia.getIdMembresia().equalsIgnoreCase(idMembresia)) {
                membresiaEncontrada=membresia;
                break;
            }
        }
        return membresiaEncontrada;
    }

    private double calcularCosto(TipoMembresia tipoMembresia, Duracion duracion, TipoUsuario tipoUsuario) {
        double base = 0;

        switch (tipoMembresia) {
            case BASICA -> base = 25000;
            case PREMIUM -> base = 50000;
            case VIP -> base = 80000;
        }

        switch (duracion) {
            case MENSUAL -> base *= 1;
            case TRIMESTRAL -> base *= 3;
            case ANUAL -> base *= 12;
        }

        switch (tipoUsuario) {
            case ESTUDIANTE -> base *= 0.8;
            case TRABAJADOR_UQ -> base *= 0.9;
            case EXTERNO -> base *= 1.0;
        }

        return base;
    }

    //CRUD RESERVA
    public Reserva crearReserva(String idReserva, String idUsuario, String nombreClase) {
        Reserva reservaEncontrada=obtenerReserva(idReserva);
        if (reservaEncontrada == null) {
        Usuario usuario = obtenerUsuario(idUsuario);
        Clase clase = obtenerClase(nombreClase);
        Membresia membresia = usuario.getMembresia();

        if (membresia.equals(TipoMembresia.BASICA)){
            return null;
        }

        if (usuario == null || clase == null) {
            return null;
        }
        if (clase.getListaUsuariosRegistrados().size() >= clase.getCupoMaximo()) {
            System.out.println("No hay cupos para esta clase.");
            return null;
        }
            Reserva reserva=new Reserva();
            reserva.setIdReserva(idReserva);
            reserva.setUsuario(usuario);
            reserva.setClase(clase);
            reserva.setFechaReserva(LocalDate.now());
            reserva.setEstado(Estado.ACTIVA);
            getListaReservas().add(reserva);

            clase.getListaUsuariosRegistrados().add(usuario);
            usuario.getReservas().add(reserva);
            usuario.actualizarReservasTexto();
            return reserva;
        } else {
            return null;
        }
    }
    public Reserva eliminarReserva(String idReserva) {
        Reserva reservaEncontrada = obtenerReserva(idReserva);
        if (reservaEncontrada != null) {
        Clase clase = reservaEncontrada.getClase();
        Usuario usuario = reservaEncontrada.getUsuario();
        if (clase != null && usuario != null) {
            clase.getListaUsuariosRegistrados().remove(usuario);
        }
            getListaReservas().remove(reservaEncontrada);
            return reservaEncontrada;
        } else {
            return null;
        }
    }
    public Reserva actualizarReserva(String idReserva, String idUsuario, String nombreClase) {
        Reserva reservaEncontrada=obtenerReserva(idReserva);
        Usuario usuario = obtenerUsuario(idUsuario);
        if (reservaEncontrada != null) {
            Clase claseAnterior = reservaEncontrada.getClase();
            Usuario usuarioAnterior = reservaEncontrada.getUsuario();
            if (claseAnterior != null && usuarioAnterior != null) {
                claseAnterior.getListaUsuariosRegistrados().remove(usuarioAnterior);
            }
            Usuario nuevoUsuario = obtenerUsuario(idUsuario);
            Clase nuevaClase = obtenerClase(nombreClase);
            if (nuevoUsuario == null || nuevaClase == null) {
                return null;
            }
            reservaEncontrada.setIdReserva(idReserva);
            reservaEncontrada.setUsuario(nuevoUsuario);
            reservaEncontrada.setClase(nuevaClase);
            reservaEncontrada.setFechaReserva(LocalDate.now());
            reservaEncontrada.setEstado(Estado.ACTIVA);

            nuevaClase.getListaUsuariosRegistrados().add(nuevoUsuario);
            usuario.actualizarReservasTexto();
            return reservaEncontrada;
        } else {
            return null;
        }
    }
    public Reserva obtenerReserva(String idReserva) {
        Reserva reservaEncontrada=null;
        for (Reserva reserva : getListaReservas()) {
            if (reserva.getIdReserva().equalsIgnoreCase(idReserva)) {
                reservaEncontrada=reserva;
                break;
            }
        }
        return reservaEncontrada;
    }

    public String buscarReporteRecepcionista(String idReporte) {
        ReporteRecepcionista reporteEncontrado=obtenerReporteRecepcionista(idReporte);
        String resultado="";
        if (reporteEncontrado != null) {
            resultado="INFORMACIÓN DEL REPORTE\n\n" +
                    "ID del Reporte: " + reporteEncontrado.getIdReporte() + "\n" +
                    "Tipo de Reporte: " + reporteEncontrado.getTipoRecepcionista() + "\n" +
                    "Fecha de Generación: " + reporteEncontrado.getFechaGeneracion() + "\n" +
                    "Descripción: " + reporteEncontrado.getDescripcion() + "\n\n" +
                    "Contenido Generado:\n" +
                    reporteEncontrado.getContenidoGenerado();
        } else {
            resultado="El reporte no ha sido generado o el id fue mal digitado.";
        }
        return resultado;
    }

    public String buscarReporteAdministrador(String idReporte) {
        ReporteAdministrador reporteEncontrado=obtenerReporteAdministrador(idReporte);
        String resultado="";
        if (reporteEncontrado != null) {
            resultado="INFORMACIÓN DEL REPORTE\n\n" +
                    "ID del Reporte: " + reporteEncontrado.getIdReporte() + "\n" +
                    "Tipo de Reporte: " + reporteEncontrado.getTipoAdministrador() + "\n" +
                    "Fecha de Generación: " + reporteEncontrado.getFechaGeneracion() + "\n" +
                    "Descripción: " + reporteEncontrado.getDescripcion() + "\n\n" +
                    "Contenido Generado:\n" +
                    reporteEncontrado.getContenidoGenerado();
        } else {
            resultado="El reporte no ha sido generado o el id fue mal digitado.";
        }
        return resultado;
    }

}














