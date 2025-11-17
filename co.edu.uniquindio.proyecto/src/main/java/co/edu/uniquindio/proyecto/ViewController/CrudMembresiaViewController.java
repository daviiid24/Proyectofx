package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.MembresiaController;
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

public class CrudMembresiaViewController implements Initializable {

    MembresiaController membresiaController;
    ObservableList<Membresia> listaMembresias= FXCollections.observableArrayList();
    Membresia membresiaSelecionada;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ChoiceBox<Duracion> chDuracion;

    @FXML
    private ChoiceBox<TipoMembresia> chTipoMembresia;

    @FXML
    private ChoiceBox<TipoUsuario> chTipoUsuario;

    @FXML
    private TableView<Membresia> tableMembresia;

    @FXML
    private TableColumn<Membresia, String> tcCosto;

    @FXML
    private TableColumn<Membresia, String> tcDuracion;

    @FXML
    private TableColumn<Membresia, String> tcEstado;

    @FXML
    private TableColumn<Membresia, String> tcFechaInicio;

    @FXML
    private TableColumn<Membresia, String> tcFechaVencimiento;

    @FXML
    private TableColumn<Membresia, String> tcIdMembresia;

    @FXML
    private TableColumn<Membresia, String> tcTipoMembresia;

    @FXML
    private TableColumn<Membresia, String> tcTipoUsuario;

    @FXML
    private TextField txtIdMembresia;

    @FXML
    void onActionActualizar(ActionEvent event) {
        actualizarMembresia();
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearMembresia();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarMembresia();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<TipoMembresia> tiposMembresia = FXCollections.observableArrayList(TipoMembresia.values());
        chTipoMembresia.setItems(tiposMembresia);
        chTipoMembresia.setValue(null);

        ObservableList<Duracion> duraciones = FXCollections.observableArrayList(Duracion.values());
        chDuracion.setItems(duraciones);
        chDuracion.setValue(null);

        ObservableList<TipoUsuario> tiposUsuario = FXCollections.observableArrayList(TipoUsuario.values());
        chTipoUsuario.setItems(tiposUsuario);
        chTipoUsuario.setValue(null);

        membresiaController = new MembresiaController();
        initView();
    }

    private void crearMembresia() {
        String idMembresia=txtIdMembresia.getText();

        TipoMembresia tipoMembresiaSeleccionada = chTipoMembresia.getValue();
        String tipoMembresiaTexto = tipoMembresiaSeleccionada != null ? tipoMembresiaSeleccionada.name() : null;

        Duracion duracionSeleccionada = chDuracion.getValue();
        String duracionTexto = duracionSeleccionada != null ? duracionSeleccionada.name() : null;

        TipoUsuario tipoUsuarioSeleccionado = chTipoUsuario.getValue();
        String tipoUsuarioTexto = tipoUsuarioSeleccionado != null ? tipoUsuarioSeleccionado.name() : null;

        boolean datosValidos = validarCampos(idMembresia, tipoMembresiaTexto, duracionTexto, tipoUsuarioTexto);

        if (datosValidos){
            Membresia membresia=membresiaController.crearMembresia(idMembresia, tipoMembresiaTexto, duracionTexto, tipoUsuarioTexto);
            if(membresia!=null){
                mostrarMensaje("Notificación", "Creación membresia", "Membresia creada", Alert.AlertType.CONFIRMATION);
                listaMembresias.add(membresia);
            } else {
                mostrarMensaje("Notificación", "Creación membresia", "Membresia no creada",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación membresia", "Campos vacios",Alert.AlertType.INFORMATION);
        }

    }

    private void actualizarMembresia() {
        String idMembresia=txtIdMembresia.getText();

        TipoMembresia tipoMembresiaSeleccionada = chTipoMembresia.getValue();
        String tipoMembresiaTexto = tipoMembresiaSeleccionada != null ? tipoMembresiaSeleccionada.name() : null;

        Duracion duracionSeleccionada = chDuracion.getValue();
        String duracionTexto = duracionSeleccionada != null ? duracionSeleccionada.name() : null;

        TipoUsuario tipoUsuarioSeleccionado = chTipoUsuario.getValue();
        String tipoUsuarioTexto = tipoUsuarioSeleccionado != null ? tipoUsuarioSeleccionado.name() : null;

        boolean datosValidos = validarCampos(idMembresia, tipoMembresiaTexto, duracionTexto, tipoUsuarioTexto);
        if (datosValidos){
            Membresia membresia=membresiaController.actualizarMembresia(idMembresia, tipoMembresiaTexto, duracionTexto, tipoUsuarioTexto);
            if(membresia!=null){
                mostrarMensaje("Notificación", "Actualización membresia", "Membresia actualizada",Alert.AlertType.CONFIRMATION);
                tableMembresia.refresh();
            } else {
                mostrarMensaje("Notificación", "Actualización membresia", "Membresia no actualizada",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Actualización membresia", "Campos vacios",Alert.AlertType.INFORMATION);
        }

    }

    private void eliminarMembresia(){
        String idMembresia=txtIdMembresia.getText();
        boolean datosValidos = validarCamposEliminar(idMembresia);
        if (!datosValidos) {
            mostrarMensaje("Notificación", "Eliminación membresia", "Campos vacíos", Alert.AlertType.INFORMATION);
            return;
        }
        Membresia membresiaEliminada = membresiaController.eliminarMembresia(idMembresia);
        if (membresiaEliminada != null) {
            listaMembresias.removeIf(membresia -> membresia.getIdMembresia().equalsIgnoreCase(idMembresia));
            tableMembresia.refresh();

            mostrarMensaje("Notificación", "Eliminación membresia", "Membresia eliminada", Alert.AlertType.CONFIRMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación", "Eliminación membresia", "Membresia no encontrada", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCamposEliminar(String identificacion) {
        return !identificacion.isEmpty();
    }

    private boolean validarCampos(String idMembresia, String tipoMembresiaTexto, String duracionTexto, String tipoUsuarioTexto) {
        return !idMembresia.isEmpty() && !tipoMembresiaTexto.isEmpty() && !duracionTexto.isEmpty() && !tipoUsuarioTexto.isEmpty();
    }

    private void initView() {
        initDataBinding();
        obtenerMembresias();
        tableMembresia.getItems().clear();
        tableMembresia.setItems(listaMembresias);
        listenerSelection();
    }

    private void obtenerMembresias() {
        listaMembresias.addAll(membresiaController.obtenerMembresias());
    }

    private void initDataBinding() {
        tcIdMembresia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdMembresia()));
        tcTipoMembresia.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipoMembresia())));
        tcDuracion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuracion())));
        tcTipoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipoUsuario())));
        tcCosto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCosto())));
        tcFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaInicio())));
        tcFechaVencimiento.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaVencimiento())));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEstado())));
    }

    private void listenerSelection() {
        tableMembresia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            membresiaSelecionada = newSelection;
            mostrarInformacion(membresiaSelecionada);
        });
    }

    private void mostrarInformacion(Membresia membresiaSelecionada) {
        if(membresiaSelecionada != null){
            txtIdMembresia.setText(membresiaSelecionada.getIdMembresia());
            chTipoMembresia.setValue(membresiaSelecionada.getTipoMembresia());
            chDuracion.setValue(membresiaSelecionada.getDuracion());
            chTipoUsuario.setValue(membresiaSelecionada.getTipoUsuario());
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
        txtIdMembresia.clear();
        chTipoMembresia.setValue(null);
        chDuracion.setValue(null);
        chTipoUsuario.setValue(null);
        membresiaSelecionada = null;
        tableMembresia.getSelectionModel().clearSelection();
    }

}
