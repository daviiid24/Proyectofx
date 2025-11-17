package co.edu.uniquindio.proyecto.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaRecepcionistaViewController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    void onActionCerrarSesion(ActionEvent event) throws IOException {
        cerrarSesion();
    }

    private void cerrarSesion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto/InicioSesion.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Inicio de Sesión");
        stage.show();
    }
}
