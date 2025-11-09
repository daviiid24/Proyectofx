package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;
import co.edu.uniquindio.proyecto.model.Entrenador;

import java.util.List;

public class EntrenadorController {
        ModelFactory modelFactory;
        public EntrenadorController(){
            modelFactory = ModelFactory.getInstancia();
        }

        public List<Entrenador> obtenerEntrenadores() {
            return modelFactory.obtenerEntrenadores();
        }

        public Entrenador crearEntrenador(String nombre,
                                    String identificacion,
                                    String edad,
                                    String telefono) {
            return modelFactory.crearEntrenador(nombre, identificacion, edad, telefono);
        }

        public Entrenador actualizarEntrenador(String nombre,
                                         String identificacion,
                                         String edad,
                                         String telefono) {
            return modelFactory.actualizarEntrenador(nombre, identificacion, edad, telefono);
        }

        public Entrenador eliminarEntrenador(String identificacion) {
            return modelFactory.eliminarEntrenador(identificacion);
        }
}
