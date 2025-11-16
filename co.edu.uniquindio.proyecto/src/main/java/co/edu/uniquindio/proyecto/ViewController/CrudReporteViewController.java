package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.ReporteController;
import co.edu.uniquindio.proyecto.model.Reporte;
import co.edu.uniquindio.proyecto.model.TipoReporte;
import co.edu.uniquindio.proyecto.model.TipoUsuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrudReporteViewController implements Initializable {

    ReporteController reporteController;
    ObservableList<Reporte> listaReportes= FXCollections.observableArrayList();
    Reporte reporteSeleccionado;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private ChoiceBox<TipoReporte> chTipoReporte;

    @FXML
    private TableView<Reporte> tableReportes;

    @FXML
    private TableColumn<Reporte, String> tcDescripcion;

    @FXML
    private TableColumn<Reporte, String> tcFecha;

    @FXML
    private TableColumn<Reporte, String> tcIdReporte;

    @FXML
    private TableColumn<Reporte, String> tcTipo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    void onActionActualizar(ActionEvent event) {

    }

    @FXML
    void onActionEliminar(ActionEvent event) {

    }

    @FXML
    void onActionGenerarReporte(ActionEvent event) {
        generarReporte();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TipoReporte> tipos = FXCollections.observableArrayList(TipoReporte.values());
        chTipoReporte.setItems(tipos);
        chTipoReporte.setValue(null);

        reporteController = new ReporteController();
        initView();
    }

    private void generarReporte() {
        String descripcion=txtDescripcion.getText();

        TipoReporte tipoSeleccionado = chTipoReporte.getValue();
        String tipoReporteTexto = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(descripcion,tipoReporteTexto);

        if (datosValidos){
            Reporte reporte=reporteController.generarReporte(descripcion,tipoReporteTexto);
            if(reporte!=null){
                mostrarMensaje("Notificación", "Creación reporte", "Reporte creado", Alert.AlertType.CONFIRMATION);
                listaReportes.add(reporte);
                limpiarCampos();
            } else {
                mostrarMensaje("Notificación", "Creación reporte", "Reporte no creado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación reporte", "Campos vacios",Alert.AlertType.INFORMATION);
        }
    }

    private boolean validarCamposEliminar(String identificacion) {
        return !identificacion.isEmpty();
    }

    private boolean validarCampos(String descripcion, String tipoReporteTexto) {
        return !descripcion.isEmpty() && !tipoReporteTexto.isEmpty();
    }

    private boolean validarCamposActualizar(String nombre, String identificacion, String edad, String telefono, TipoUsuario tipoSeleccionado) {
        return !nombre.isEmpty() && !identificacion.isEmpty() && !edad.isEmpty() && !telefono.isEmpty();
    }

    private void initView() {
        initDataBinding();
        obtenerReportes();
        tableReportes.getItems().clear();
        tableReportes.setItems(listaReportes);
        listenerSelection();
    }

    private void obtenerReportes() {
        listaReportes.addAll(reporteController.obtenerReportes());
    }

    private void initDataBinding() {
        tcIdReporte.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdReporte()));
        tcTipo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipo())));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaGeneracion())));
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
    }

    private void listenerSelection() {
        tableReportes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reporteSeleccionado = newSelection;
            mostrarInformacion(reporteSeleccionado);
        });
    }

    private void mostrarInformacion(Reporte reporteSeleccionado) {
        if(reporteSeleccionado != null){
            txtDescripcion.setText(reporteSeleccionado.getDescripcion());
            chTipoReporte.setValue(reporteSeleccionado.getTipo());
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }
    private void limpiarCampos() {
        txtDescripcion.clear();
        chTipoReporte.setValue(null);
        reporteSeleccionado = null;
        tableReportes.getSelectionModel().clearSelection();
    }



}
