package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.ReporteController;
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

public class CrudReporteViewController implements Initializable {

    ReporteController reporteController;
    ObservableList<ReporteRecepcionista> listaReportes= FXCollections.observableArrayList();
    ReporteRecepcionista reporteSeleccionado;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private ChoiceBox<TipoReporte> chTipoReporte;

    @FXML
    private TableView<ReporteRecepcionista> tableReporte;

    @FXML
    private TableColumn<ReporteRecepcionista, String> tcDescripcion;

    @FXML
    private TableColumn<ReporteRecepcionista, String> tcFecha;

    @FXML
    private TableColumn<ReporteRecepcionista, String> tcIdReporte;

    @FXML
    private TableColumn<ReporteRecepcionista, String> tcTipo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    void onActionActualizar(ActionEvent event) {
        actualizarReporteRecepcionista();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarReporteRecepcionista();
    }

    @FXML
    void onActionGenerarReporte(ActionEvent event) {
        generarReporteRecepcionista();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TipoReporte> tipos = FXCollections.observableArrayList(TipoReporte.values());
        chTipoReporte.setItems(tipos);
        chTipoReporte.setValue(null);

        reporteController = new ReporteController();
        initView();
    }

    private void generarReporteRecepcionista() {
        String descripcion=txtDescripcion.getText();

        TipoReporte tipoSeleccionado = chTipoReporte.getValue();
        String tipoReporteTexto = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(descripcion,tipoReporteTexto);

        if (datosValidos){
            ReporteRecepcionista reporteRecepcionista=reporteController.generarReporteRecepcionista(descripcion,tipoReporteTexto);
            if(reporteRecepcionista!=null){
                mostrarMensaje("Notificación", "Creación reporte", "Reporte creado", Alert.AlertType.CONFIRMATION);
                listaReportes.add(reporteRecepcionista);
                limpiarCampos();
            } else {
                mostrarMensaje("Notificación", "Creación reporte", "Reporte no creado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación reporte", "Campos vacios",Alert.AlertType.INFORMATION);
        }
    }

    private void actualizarReporteRecepcionista(){
        String descripcion=txtDescripcion.getText();
        String idReporte=reporteSeleccionado.getIdReporte();

        TipoReporte tipoSeleccionado = chTipoReporte.getValue();
        String tipoReporteTexto = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(descripcion,tipoReporteTexto);

        if (datosValidos){
            ReporteRecepcionista reporteRecepcionista=reporteController.actualizarReporteRecepcionista(idReporte, descripcion,tipoReporteTexto);
            if(reporteRecepcionista!=null){
                mostrarMensaje("Notificación", "Actualización reporte", "Reporte actualizado",Alert.AlertType.CONFIRMATION);
                tableReporte.refresh();
            } else {
                mostrarMensaje("Notificación", "Actualización reporte", "Reporte no actualizado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Actualización reporte", "Campos vacios",Alert.AlertType.INFORMATION);
        }

    }

    private void eliminarReporteRecepcionista() {
        String idReporte=reporteSeleccionado.getIdReporte();
        boolean datosValidos = validarCamposEliminar(idReporte);
        if (!datosValidos) {
            mostrarMensaje("Notificación", "Eliminación reporte", "Campos vacíos", Alert.AlertType.INFORMATION);
            return;
        }
        ReporteRecepcionista reporteEliminado = reporteController.eliminarReporteRecepcionista(idReporte);
        if (reporteEliminado != null) {
            listaReportes.removeIf(reporteRecepcionista -> reporteRecepcionista.getIdReporte().equalsIgnoreCase(idReporte));
            tableReporte.refresh();

            mostrarMensaje("Notificación", "Eliminación reporte", "Reporte eliminado", Alert.AlertType.CONFIRMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación", "Eliminación reporte", "Reporte no encontrado", Alert.AlertType.WARNING);
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
        obtenerReportes();
        tableReporte.getItems().clear();
        tableReporte.setItems(listaReportes);
        listenerSelection();
    }

    private void obtenerReportes() {
        listaReportes.addAll(reporteController.obtenerReportesRecepcionista());
    }

    private void initDataBinding() {
        tcIdReporte.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdReporte()));
        tcTipo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipoRecepcionista())));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaGeneracion())));
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
    }

    private void listenerSelection() {
        tableReporte.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reporteSeleccionado = newSelection;
            mostrarInformacion(reporteSeleccionado);
        });
    }

    private void mostrarInformacion(ReporteRecepcionista reporteSeleccionado) {
        if(reporteSeleccionado != null){
            txtDescripcion.setText(reporteSeleccionado.getDescripcion());
            chTipoReporte.setValue(reporteSeleccionado.getTipoRecepcionista());
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
