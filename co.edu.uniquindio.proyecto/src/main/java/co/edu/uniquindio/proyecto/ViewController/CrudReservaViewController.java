package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.ReservaController;
import co.edu.uniquindio.proyecto.model.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CrudReservaViewController implements Initializable {

    ReservaController reservaController;
    ObservableList<Reserva> listaReservas= FXCollections.observableArrayList();
    Reserva reservaSelecionada;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Reserva> tableReservas;

    @FXML
    private TableColumn<Reserva, String> tcClase;

    @FXML
    private TableColumn<Reserva, String> tcEstado;

    @FXML
    private TableColumn<Reserva, String> tcFecha;

    @FXML
    private TableColumn<Reserva, String> tcIdReserva;

    @FXML
    private TableColumn<Reserva, String> tcUsuario;

    @FXML
    private TextField txtIdReserva;

    @FXML
    private TextField txtIdUsuario;

    @FXML
    private TextField txtNombreClase;

    @FXML
    void onActionActualizar(ActionEvent event) {
        actualizarReserva();
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearReserva();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarReserva();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        reservaController = new ReservaController();
        initView();
    }

    private void crearReserva() {
        String idReserva=txtIdReserva.getText();
        String idUsuario=txtIdUsuario.getText();
        String nombreClase=txtNombreClase.getText();

        boolean datosValidos = validarCampos(idReserva, idUsuario, nombreClase);

        if (datosValidos){
            Reserva reserva=reservaController.crearReserva(idReserva, idUsuario, nombreClase);
            if(reserva!=null){
                mostrarMensaje("Notificación", "Creación reserva", "Reserva creada", Alert.AlertType.CONFIRMATION);
                listaReservas.add(reserva);
            } else {
                mostrarMensaje("Notificación", "Creación reserva", "Reserva no creada",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación reserva", "Campos vacios",Alert.AlertType.INFORMATION);
        }
    }

    private void actualizarReserva() {
        String idReserva=txtIdReserva.getText();
        String idUsuario=txtIdUsuario.getText();
        String nombreClase=txtNombreClase.getText();

        boolean datosValidos = validarCampos(idReserva, idUsuario, nombreClase);
        if (datosValidos){
            Reserva reserva=reservaController.actualizarReserva(idReserva, idUsuario, nombreClase);
            if(reserva!=null){
                mostrarMensaje("Notificación", "Actualización reserva", "Reserva actualizada",Alert.AlertType.CONFIRMATION);
                tableReservas.refresh();
            } else {
                mostrarMensaje("Notificación", "Actualización reserva", "Reserva no actualizada",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Actualización reserva", "Campos vacios",Alert.AlertType.INFORMATION);
        }

    }

    private void eliminarReserva(){
        String idReserva=txtIdReserva.getText();
        boolean datosValidos = validarCamposEliminar(idReserva);
        if (!datosValidos) {
            mostrarMensaje("Notificación", "Eliminación reserva", "Campos vacíos", Alert.AlertType.INFORMATION);
            return;
        }
        Reserva reservaEliminada = reservaController.eliminarReserva(idReserva);
        if (reservaEliminada != null) {
            listaReservas.removeIf(reserva -> reserva.getIdReserva().equalsIgnoreCase(idReserva));
            tableReservas.refresh();

            mostrarMensaje("Notificación", "Eliminación reserva", "Reserva eliminada", Alert.AlertType.CONFIRMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación", "Eliminación reserva", "Reserva no encontrada", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCamposEliminar(String idReserva) {
        return !idReserva.isEmpty();
    }

    private boolean validarCampos(String idReserva, String idUsuario, String nombreClase) {
        return !idReserva.isEmpty() && !idUsuario.isEmpty() && !nombreClase.isEmpty();
    }

    private void initView() {
        initDataBinding();
        obtenerReservas();
        tableReservas.getItems().clear();
        tableReservas.setItems(listaReservas);
        listenerSelection();
    }

    private void obtenerReservas() {
        listaReservas.addAll(reservaController.obtenerReservas());
    }

    private void initDataBinding() {
        tcIdReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdReserva()));
        tcUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUsuario().getNombre())));
        tcClase.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getClase().getNombre())));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaReserva())));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEstado())));
    }

    private void listenerSelection() {
        tableReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSelecionada = newSelection;
            mostrarInformacion(reservaSelecionada);
        });
    }

    private void mostrarInformacion(Reserva reservaSelecionada) {
        if(reservaSelecionada != null){
            txtIdReserva.setText(reservaSelecionada.getIdReserva());
            txtIdUsuario.setText(String.valueOf(reservaSelecionada.getUsuario().getIdentificacion()));
            txtNombreClase.setText(String.valueOf(reservaSelecionada.getClase().getNombre()));
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private void limpiarCampos() {
        txtIdReserva.clear();
        txtIdUsuario.clear();
        txtNombreClase.clear();
        reservaSelecionada = null;
        tableReservas.getSelectionModel().clearSelection();
    }

}
