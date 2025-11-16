package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.ConsultaReporteAvanzadoController;
import co.edu.uniquindio.proyecto.controller.ConsultaReporteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ConsultaReporteAvanzadoViewController {

    ConsultaReporteAvanzadoController consultaReporteAvanzadoController;

    @FXML
    private Button btnBuscarReporte;

    @FXML
    private TextField txtIdReporte;

    @FXML
    private TextArea txtResultado;

    @FXML
    void onActionBuscarReporte(ActionEvent event) {
        buscarReporte();
    }

    @FXML
    void initialize() {
        consultaReporteAvanzadoController = new ConsultaReporteAvanzadoController();
    }

    private void buscarReporte() {
        String idReporte = txtIdReporte.getText();

        if(!idReporte.isEmpty()) {
            String resultado=consultaReporteAvanzadoController.buscarReporte(idReporte);
            txtResultado.setText(resultado);
        }
    }
}
