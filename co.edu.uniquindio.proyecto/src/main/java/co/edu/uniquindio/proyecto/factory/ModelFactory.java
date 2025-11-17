package co.edu.uniquindio.proyecto.factory;

import co.edu.uniquindio.proyecto.model.*;
import co.edu.uniquindio.proyecto.utils.DataUtil;

import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private final Gimnasio gimnasio;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private ModelFactory(){
        gimnasio = DataUtil.inicializarDatos();
    }

    public List<Usuario> obtenerUsuarios() {
        return gimnasio.getListaUsuarios();
    }

    public Usuario crearUsuario(String nombre, String identificacion, String edad, String telefono, String tipoUsuario) {
        return gimnasio.crearUsuario(nombre, identificacion, Integer.parseInt(edad), telefono, TipoUsuario.valueOf(tipoUsuario));
    }

    public Usuario actualizarUsuario(String nombre, String identificacion, String edad, String telefono, String tipoUsuarioStr) {
        return gimnasio.actualizarUsuario(nombre, identificacion, Integer.parseInt(edad), telefono, TipoUsuario.valueOf(tipoUsuarioStr));
    }

    public Usuario eliminarUsuario(String identificacion) {
        return gimnasio.eliminarUsuario(identificacion);
    }

    public List<Entrenador> obtenerEntrenadores() {
        return gimnasio.getListaEntrenadores();
    }

    public Entrenador crearEntrenador(String nombre, String identificacion, String edad, String telefono) {
        return gimnasio.crearEntrenador(nombre, identificacion, Integer.parseInt(edad), telefono);
    }

    public Entrenador actualizarEntrenador(String nombre, String identificacion, String edad, String telefono) {
        return gimnasio.actualizarEntrenador(nombre, identificacion, Integer.parseInt(edad), telefono);
    }

    public Entrenador eliminarEntrenador(String identificacion) {
        return gimnasio.eliminarEntrenador(identificacion);
    }
    public List<Clase> obtenerClases() {
        return gimnasio.getListaClases();
    }

    public Clase crearClase(String nombre, String horario, String cupoMaximo, String TipoClaseTexto, String idEntrenador) {
        return gimnasio.crearClase(nombre, Integer.parseInt(cupoMaximo), idEntrenador, TipoClase.valueOf(TipoClaseTexto), horario);
    }

    public Clase actualizarClase(String nombre, String horario, String cupoMaximo, String TipoClaseTexto, String idEntrenador) {
        return gimnasio.actualizarClase(nombre, Integer.parseInt(cupoMaximo), idEntrenador, TipoClase.valueOf(TipoClaseTexto), horario);
    }

    public Clase eliminarClase(String nombre) {
        return gimnasio.eliminarClase(nombre);
    }

    public List<Reserva> obtenerReservas() {
        return gimnasio.getListaReservas();
    }

    public Reserva crearReserva(String idReserva, String idUsuario, String nombreClase) {
        return gimnasio.crearReserva(idReserva, idUsuario, nombreClase);
    }

    public Reserva actualizarReserva(String idReserva, String idUsuario, String nombreClase) {
        return gimnasio.actualizarReserva(idReserva, idUsuario, nombreClase);
    }

    public Reserva eliminarReserva(String idReserva) {
        return gimnasio.eliminarReserva(idReserva);
    }

    public String validarUsuario(String idUsuario) {
        return gimnasio.validarUsuario(idUsuario);
    }

    public String validarUsuarioAvanzado(String nombre, String idUsuario, String telefono) {
        return gimnasio.validarUsuarioAvanzado(nombre, idUsuario, telefono);
    }

    public List<ReporteRecepcionista> obtenerReportesRecepcionista() {
        return gimnasio.getListaReportesRecepcionista();
    }

    public ReporteRecepcionista generarReporteRecepcionista(String descripcion, String tipoReporteTexto) {
        return gimnasio.generarReporteRecepcionista(TipoReporte.valueOf(tipoReporteTexto), descripcion);
    }

    public ReporteRecepcionista actualizarReporteRecepcionista(String idReporte, String descripcion, String tipoReporteTexto) {
        return gimnasio.actualizarReporteRecepcionista(idReporte, TipoReporte.valueOf(tipoReporteTexto), descripcion);
    }

    public ReporteRecepcionista eliminarReporteRecepcionista(String idReporte) {
        return gimnasio.eliminarReporteRecepcionista(idReporte);
    }

    public String buscarReporteRecepcionista(String idReporte) {
        return gimnasio.buscarReporteRecepcionista(idReporte);
    }

    public List<ReporteAdministrador> obtenerReportesAdministrador() {
        return gimnasio.getListaReportesAdministrador();
    }

    public ReporteAdministrador generarReporteAdministrador(String descripcion, String tipoReporteTexto) {
        return gimnasio.generarReporteAdministrador(TipoReporteAvanzado.valueOf(tipoReporteTexto), descripcion);
    }

    public ReporteAdministrador actualizarReporteAdministrador(String idReporte, String descripcion, String tipoReporteTexto) {
        return gimnasio.actualizarReporteAdministrador(idReporte, TipoReporteAvanzado.valueOf(tipoReporteTexto), descripcion);
    }

    public ReporteAdministrador eliminarReporteAdministrador(String idReporte) {
        return gimnasio.eliminarReporteAdministrador(idReporte);
    }

    public String buscarReporteAdministrador(String idReporte) {
        return gimnasio.buscarReporteAdministrador(idReporte);
    }

    public List<Membresia> obtenerMembresias() {
        return gimnasio.getListaMembresias();
    }

    public Membresia crearMembresia(String idMembresia, String tipoMembresiaTexto, String duracionTexto, String tipoUsuarioTexto) {
        return gimnasio.crearMembresia(idMembresia, TipoMembresia.valueOf(tipoMembresiaTexto), Duracion.valueOf(duracionTexto), TipoUsuario.valueOf(tipoUsuarioTexto));
    }

    public Membresia actualizarMembresia(String idMembresia, String tipoMembresiaTexto, String duracionTexto, String tipoUsuarioTexto) {
        return gimnasio.actualizarMembresia(idMembresia, TipoMembresia.valueOf(tipoMembresiaTexto), Duracion.valueOf(duracionTexto), TipoUsuario.valueOf(tipoUsuarioTexto));
    }

    public Membresia eliminarMembresia(String idMembresia) {
        return gimnasio.eliminarMembresia(idMembresia);
    }


}