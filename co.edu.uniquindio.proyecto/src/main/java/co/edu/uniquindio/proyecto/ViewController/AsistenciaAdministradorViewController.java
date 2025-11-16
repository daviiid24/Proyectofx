package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.AsistenciaAdministradorController;
import co.edu.uniquindio.proyecto.controller.AsistenciaRecepcionistaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AsistenciaAdministradorViewController {

    AsistenciaAdministradorController asistenciaAdministradorController;

    @FXML
    private Button btnValidar;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea txtResultado;

    @FXML
    private TextField txtTelefono;

    @FXML
    void onActionValidar(ActionEvent event) {
        validarUsuarioAvanzado();
    }

    @FXML
    void initialize() {
        asistenciaAdministradorController = new AsistenciaAdministradorController();
    }

    private void validarUsuarioAvanzado() {
        String nombre = txtNombre.getText();
        String idUsuario = txtIdentificacion.getText();
        String telefono = txtTelefono.getText();


        if(!idUsuario.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()) {
            String resultado=asistenciaAdministradorController.validarUsuarioAvanzado(nombre, idUsuario, telefono);
            txtResultado.setText(resultado);
        }
    }

}
