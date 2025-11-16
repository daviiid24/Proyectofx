package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.ReporteAvanzadoController;
import co.edu.uniquindio.proyecto.model.*;
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

public class CrudReporteAvanzadoViewController implements Initializable {

    ReporteAvanzadoController reporteAvanzadoController;
    ObservableList<ReporteAdministrador> listaReportes= FXCollections.observableArrayList();
    ReporteAdministrador reporteSeleccionado;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private ChoiceBox<TipoReporteAvanzado> chTipoReporte;

    @FXML
    private TableView<ReporteAdministrador> tableReporte;

    @FXML
    private TableColumn<ReporteAdministrador, String> tcDescripcion;

    @FXML
    private TableColumn<ReporteAdministrador, String> tcFecha;

    @FXML
    private TableColumn<ReporteAdministrador, String> tcIdReporte;

    @FXML
    private TableColumn<ReporteAdministrador, String> tcTipo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    void onActionActualizar(ActionEvent event) {
        actualizarReporteAdministrador();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarReporteAdministrador();
    }

    @FXML
    void onActionGenerarReporte(ActionEvent event) {
        generarReporteAdministrador();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TipoReporteAvanzado> tipos = FXCollections.observableArrayList(TipoReporteAvanzado.values());
        chTipoReporte.setItems(tipos);
        chTipoReporte.setValue(null);

        reporteAvanzadoController = new ReporteAvanzadoController();
        initView();
    }

    private void generarReporteAdministrador() {
        String descripcion=txtDescripcion.getText();

        TipoReporteAvanzado tipoSeleccionado = chTipoReporte.getValue();
        String tipoReporteAvanzadoTexto = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(descripcion, tipoReporteAvanzadoTexto);

        if (datosValidos){
            ReporteAdministrador reporteAdministrador= reporteAvanzadoController.generarReporteAdministrador(descripcion, tipoReporteAvanzadoTexto);
            if(reporteAdministrador!=null){
                mostrarMensaje("Notificación", "Creación reporte avanzado", "Reporte avanzado creado", Alert.AlertType.CONFIRMATION);
                listaReportes.add(reporteAdministrador);
                limpiarCampos();
            } else {
                mostrarMensaje("Notificación", "Creación reporte avanzado", "Reporte avanzado no creado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación reporte avanzado", "Campos vacios",Alert.AlertType.INFORMATION);
        }
    }

    private void actualizarReporteAdministrador(){
        String descripcion=txtDescripcion.getText();
        String idReporte=reporteSeleccionado.getIdReporte();

        TipoReporteAvanzado tipoSeleccionado = chTipoReporte.getValue();
        String tipoReporteAvanzadoTexto = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(descripcion,tipoReporteAvanzadoTexto);

        if (datosValidos){
            ReporteAdministrador reporteAdministrador= reporteAvanzadoController.actualizarReporteAdministrador(idReporte, descripcion,tipoReporteAvanzadoTexto);
            if(reporteAdministrador!=null){
                mostrarMensaje("Notificación", "Actualización reporte avanzado", "Reporte avanzado actualizado",Alert.AlertType.CONFIRMATION);
                tableReporte.refresh();
            } else {
                mostrarMensaje("Notificación", "Actualización reporte avanzado", "Reporte avanzado no actualizado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Actualización reporte avanzado", "Campos vacios",Alert.AlertType.INFORMATION);
        }

    }

    private void eliminarReporteAdministrador() {
        String idReporte=reporteSeleccionado.getIdReporte();
        boolean datosValidos = validarCamposEliminar(idReporte);
        if (!datosValidos) {
            mostrarMensaje("Notificación", "Eliminación reporte avanzado", "Campos vacíos", Alert.AlertType.INFORMATION);
            return;
        }
        ReporteAdministrador reporteEliminado = reporteAvanzadoController.eliminarReporteAdministrador(idReporte);
        if (reporteEliminado != null) {
            listaReportes.removeIf(reporteAdministrador -> reporteAdministrador.getIdReporte().equalsIgnoreCase(idReporte));
            tableReporte.refresh();

            mostrarMensaje("Notificación", "Eliminación reporte avanzado", "Reporte avanzado eliminado", Alert.AlertType.CONFIRMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación", "Eliminación reporte avanzado", "Reporte avanzado no encontrado", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCamposEliminar(String identificacion) {
        return !identificacion.isEmpty();
    }

    private boolean validarCampos(String descripcion, String tipoReporteTexto) {
        return !descripcion.isEmpty() && !tipoReporteTexto.isEmpty();
    }

    private void initView() {
        initDataBinding();
        obtenerReportesAdministrador();
        tableReporte.getItems().clear();
        tableReporte.setItems(listaReportes);
        listenerSelection();
    }

    private void obtenerReportesAdministrador() {
        listaReportes.addAll(reporteAvanzadoController.obtenerReportesAdministrador());
    }

    private void initDataBinding() {
        tcIdReporte.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdReporte()));
        tcTipo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipoAdministrador())));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaGeneracion())));
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
    }

    private void listenerSelection() {
        tableReporte.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reporteSeleccionado = newSelection;
            mostrarInformacion(reporteSeleccionado);
        });
    }

    private void mostrarInformacion(ReporteAdministrador reporteSeleccionado) {
        if(reporteSeleccionado != null){
            txtDescripcion.setText(reporteSeleccionado.getDescripcion());
            chTipoReporte.setValue(reporteSeleccionado.getTipoAdministrador());
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
        tableReporte.getSelectionModel().clearSelection();
    }



}
