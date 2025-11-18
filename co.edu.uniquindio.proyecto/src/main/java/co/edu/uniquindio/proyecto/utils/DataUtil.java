package co.edu.uniquindio.proyecto.utils;

import co.edu.uniquindio.proyecto.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataUtil {

    public static Gimnasio inicializarDatos() {

        Gimnasio gimnasio = new Gimnasio();

        /*
         * ===========================
         *      MEMBRESÍAS (3)
         * ===========================
         */

        Membresia membresia1 = new MembresiaBasica();
        membresia1.setIdMembresia("1001");
        membresia1.setTipoMembresia(TipoMembresia.BASICA);
        membresia1.setDuracion(Duracion.MENSUAL);
        membresia1.setTipoUsuario(TipoUsuario.ESTUDIANTE);
        membresia1.setCosto(60000);
        membresia1.setFechaInicio(LocalDate.of(2025, 1, 10));
        membresia1.setFechaVencimiento(LocalDate.of(2025, 2, 10));
        membresia1.setEstado(Estado.ACTIVA);

        Membresia membresia2 = new MembresiaPremium();
        membresia2.setIdMembresia("1002");
        membresia2.setTipoMembresia(TipoMembresia.PREMIUM);
        membresia2.setDuracion(Duracion.TRIMESTRAL);
        membresia2.setTipoUsuario(TipoUsuario.EXTERNO);
        membresia2.setCosto(240000);
        membresia2.setFechaInicio(LocalDate.of(2025, 2, 1));
        membresia2.setFechaVencimiento(LocalDate.of(2025, 5, 1));
        membresia2.setEstado(Estado.ACTIVA);

        Membresia membresia3 = new MembresiaVip();
        membresia3.setIdMembresia("1003");
        membresia3.setTipoMembresia(TipoMembresia.VIP);
        membresia3.setDuracion(Duracion.ANUAL);
        membresia3.setTipoUsuario(TipoUsuario.TRABAJADOR_UQ);
        membresia3.setCosto(900000);
        membresia3.setFechaInicio(LocalDate.of(2025, 3, 15));
        membresia3.setFechaVencimiento(LocalDate.of(2026, 3, 15));
        membresia3.setEstado(Estado.ACTIVA);

        gimnasio.getListaMembresias().add(membresia1);
        gimnasio.getListaMembresias().add(membresia2);
        gimnasio.getListaMembresias().add(membresia3);


        /*
         * ===========================
         *      USUARIOS (3)
         * ===========================
         */

        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Laura Martínez");
        usuario1.setEdad(24);
        usuario1.setIdentificacion("1001");
        usuario1.setTelefono("3105678901");
        usuario1.setTipoUsuario(TipoUsuario.ESTUDIANTE);
        usuario1.setMembresia(membresia1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Carlos Torres");
        usuario2.setEdad(31);
        usuario2.setIdentificacion("1002");
        usuario2.setTelefono("3112345678");
        usuario2.setTipoUsuario(TipoUsuario.EXTERNO);
        usuario2.setMembresia(membresia2);

        Usuario usuario3 = new Usuario();
        usuario3.setNombre("Valentina Ríos");
        usuario3.setEdad(29);
        usuario3.setIdentificacion("1003");
        usuario3.setTelefono("3009981122");
        usuario3.setTipoUsuario(TipoUsuario.TRABAJADOR_UQ);
        usuario3.setMembresia(membresia3);

        gimnasio.getListaUsuarios().add(usuario1);
        gimnasio.getListaUsuarios().add(usuario2);
        gimnasio.getListaUsuarios().add(usuario3);


        /*
         * ===========================
         *      ENTRENADORES (3)
         * ===========================
         */

        Entrenador entrenador1 = new Entrenador();
        entrenador1.setNombre("Andrés López");
        entrenador1.setEdad(36);
        entrenador1.setIdentificacion("2001");
        entrenador1.setTelefono("3209876543");
        entrenador1.setOwnedByGimnasio(gimnasio);

        Entrenador entrenador2 = new Entrenador();
        entrenador2.setNombre("María Fernández");
        entrenador2.setEdad(29);
        entrenador2.setIdentificacion("2002");
        entrenador2.setTelefono("3004567890");
        entrenador2.setOwnedByGimnasio(gimnasio);

        Entrenador entrenador3 = new Entrenador();
        entrenador3.setNombre("Julián Ortiz");
        entrenador3.setEdad(33);
        entrenador3.setIdentificacion("2003");
        entrenador3.setTelefono("3017789345");
        entrenador3.setOwnedByGimnasio(gimnasio);

        gimnasio.getListaEntrenadores().add(entrenador1);
        gimnasio.getListaEntrenadores().add(entrenador2);
        gimnasio.getListaEntrenadores().add(entrenador3);


        /*
         * ===========================
         *         CLASES (3)
         * ===========================
         */

        Clase clase1 = new Clase();
        clase1.setNombre("Entrenamiento Funcional");
        clase1.setTipoClase(TipoClase.CROSSFIT);
        clase1.setHorario("06:00 AM - 07:00 AM");
        clase1.setCupoMaximo(20);
        clase1.setEntrenador(entrenador1);
        clase1.setOwnedByGimnasio(gimnasio);
        clase1.getListaUsuariosRegistrados().add(usuario1);
        clase1.actualizarUsuariosRegistradosTexto();

        Clase clase2 = new Clase();
        clase2.setNombre("Yoga Relax");
        clase2.setTipoClase(TipoClase.YOGA);
        clase2.setHorario("07:30 AM - 08:30 AM");
        clase2.setCupoMaximo(15);
        clase2.setEntrenador(entrenador2);
        clase2.setOwnedByGimnasio(gimnasio);
        clase2.getListaUsuariosRegistrados().add(usuario2);
        clase2.actualizarUsuariosRegistradosTexto();

        Clase clase3 = new Clase();
        clase3.setNombre("Boxeo Intensivo");
        clase3.setTipoClase(TipoClase.SPINNING);
        clase3.setHorario("05:00 PM - 06:00 PM");
        clase3.setCupoMaximo(18);
        clase3.setEntrenador(entrenador3);
        clase3.setOwnedByGimnasio(gimnasio);
        clase3.getListaUsuariosRegistrados().add(usuario3);
        clase3.actualizarUsuariosRegistradosTexto();

        gimnasio.getListaClases().add(clase1);
        gimnasio.getListaClases().add(clase2);
        gimnasio.getListaClases().add(clase3);

        entrenador1.getClasesAsignadas().add(clase1);
        entrenador1.actualizarClasesAsignadasTexto();
        entrenador2.getClasesAsignadas().add(clase2);
        entrenador2.actualizarClasesAsignadasTexto();
        entrenador3.getClasesAsignadas().add(clase3);
        entrenador3.actualizarClasesAsignadasTexto();

        /*
         * ===========================
         *        RESERVAS (3)
         * ===========================
         */

        Reserva reserva1 = new Reserva();
        reserva1.setIdReserva("R001");
        reserva1.setUsuario(usuario1);
        reserva1.setClase(clase1);
        reserva1.setFechaReserva(LocalDate.of(2025, 1, 12));
        reserva1.setEstado(Estado.ACTIVA);

        Reserva reserva2 = new Reserva();
        reserva2.setIdReserva("R002");
        reserva2.setUsuario(usuario2);
        reserva2.setClase(clase2);
        reserva2.setFechaReserva(LocalDate.of(2025, 2, 5));
        reserva2.setEstado(Estado.ACTIVA);

        Reserva reserva3 = new Reserva();
        reserva3.setIdReserva("R003");
        reserva3.setUsuario(usuario3);
        reserva3.setClase(clase3);
        reserva3.setFechaReserva(LocalDate.of(2025, 3, 20));
        reserva3.setEstado(Estado.ACTIVA);

        gimnasio.getListaReservas().add(reserva1);
        gimnasio.getListaReservas().add(reserva2);
        gimnasio.getListaReservas().add(reserva3);

        usuario1.getReservas().add(reserva1);
        usuario2.getReservas().add(reserva2);
        usuario3.getReservas().add(reserva3);
        usuario1.actualizarReservasTexto();
        usuario2.actualizarReservasTexto();
        usuario3.actualizarReservasTexto();


        /*
         * ========================================
         *   REPORTE RECEPCIONISTA (ÚNICO)
         * ========================================
         */

        ReporteRecepcionista reporteRecepcionista = new ReporteRecepcionista();
        reporteRecepcionista.setIdReporte("REP_R_01");
        reporteRecepcionista.setTipoRecepcionista(TipoReporte.USUARIOS_ACTIVOS);
        reporteRecepcionista.setDescripcion("Usuarios activos del mes");
        reporteRecepcionista.setContenidoGenerado("Lista generada de usuarios activos y asistencia semanal.");

        gimnasio.getListaReportesRecepcionista().add(reporteRecepcionista);


        /*
         * ========================================
         *    REPORTE ADMINISTRADOR (ÚNICO)
         * ========================================
         */

        ReporteAdministrador reporteAdministrador = new ReporteAdministrador();
        reporteAdministrador.setIdReporte("REP_A_01");
        reporteAdministrador.setTipoAdministrador(TipoReporteAvanzado.ASISTENCIAS_USUARIO);
        reporteAdministrador.setDescripcion("Reporte avanzado de asistencias");
        reporteAdministrador.setContenidoGenerado("Estadísticas de asistencia por usuario durante los últimos 3 meses.");

        gimnasio.getListaReportesAdministrador().add(reporteAdministrador);

        return gimnasio;
    }
}
