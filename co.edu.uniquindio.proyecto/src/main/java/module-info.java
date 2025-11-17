module co.edu.uniquindio.proyectofx.proyectofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;

    opens co.edu.uniquindio.proyecto to javafx.fxml;
    opens co.edu.uniquindio.proyecto.ViewController;


    exports co.edu.uniquindio.proyecto;
    exports co.edu.uniquindio.proyecto.model;
    exports co.edu.uniquindio.proyecto.factory;
    exports co.edu.uniquindio.proyecto.ViewController;
}