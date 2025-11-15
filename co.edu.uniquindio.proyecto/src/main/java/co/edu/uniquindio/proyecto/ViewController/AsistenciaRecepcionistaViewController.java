package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.AsistenciaRecepcionistaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AsistenciaRecepcionistaViewController {

        AsistenciaRecepcionistaController asistenciaRecepcionistaController;

        @FXML
        private Button btnValidar;

        @FXML
        private TextField txtIdUsuario;

        @FXML
        private TextArea txtResultado;

        @FXML
        void onActionValidar(ActionEvent event) {
            validarUsuario();
        }

        @FXML
        void initialize() {
            asistenciaRecepcionistaController = new AsistenciaRecepcionistaController();
        }

        private void validarUsuario() {
            String idUsuario = txtIdUsuario.getText();

            if(!idUsuario.isEmpty()) {
                String resultado=asistenciaRecepcionistaController.validarUsuario(idUsuario);
                txtResultado.setText(resultado);
            }
    }
}
