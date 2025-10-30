module co.edu.uniquindio.proyecto.proyectoapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyecto.proyectoapp to javafx.fxml;
    exports co.edu.uniquindio.proyecto.proyectoapp;
}