package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.factory.ModelFactory;

public class UsuarioController {
 ModelFactory modelFactory;
 public UsuarioController(){
     modelFactory = ModelFactory.getInstancia();
 }
}
