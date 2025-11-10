package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.ClasesController;
import co.edu.uniquindio.proyecto.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CrudClasesViewController implements Initializable {

    ClasesController clasesController;
    ObservableList<Clase> listaClases=FXCollections.observableArrayList();
    Clase claseSelecionada;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ChoiceBox<TipoClase> chTipoClase;

    @FXML
    private Label lblUsuariosRegistrados;

    @FXML
    private TableView<Clase> tableClases;

    @FXML
    private TableColumn<Clase, String> tcNombre;

    @FXML
    private TableColumn<Clase, String> tcHorario;

    @FXML
    private TableColumn<Clase, String> tcTipoClase;

    @FXML
    private TableColumn<Clase, String> tcEntrenador;

    @FXML
    private TableColumn<Clase, String> tcCupoMaximo;

    @FXML
    private TableColumn<Clase, String> tcUsuariosRegistrados;

    @FXML
    private TextField txtCupoMaximo;

    @FXML
    private TextField txtHorario;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtIdEntrenador;

    @FXML
    void onActionActualizar(ActionEvent event) {
        actualizarClase();
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearClase();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarClase();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TipoClase> tipos = FXCollections.observableArrayList(TipoClase.values());
        chTipoClase.setItems(tipos);
        chTipoClase.setValue(null);

        clasesController = new ClasesController();
        initView();
    }



    private void crearClase() {
        String nombre = txtNombre.getText();
        String horario = txtHorario.getText();
        String cupoMaximo = txtCupoMaximo.getText();
        String idEntrenador = txtIdEntrenador.getText();

        TipoClase tipoSeleccionado = chTipoClase.getValue();
        String TipoClaseTexto = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(nombre, horario, cupoMaximo, tipoSeleccionado, idEntrenador);

        if (datosValidos) {
            Clase clase = clasesController.crearClase(nombre, horario, cupoMaximo, TipoClaseTexto, idEntrenador);
            if (clase != null) {
                mostrarMensaje("Notificación", "Creación clase", "Clase creada", Alert.AlertType.CONFIRMATION);
                listaClases.add(clase);
            } else {
                mostrarMensaje("Notificación", "Creación clase", "Clase no creada", Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación clase", "Campos vacios", Alert.AlertType.INFORMATION);
        }
    }
    private void actualizarClase() {
        String nombre = txtNombre.getText();
        String horario = txtHorario.getText();
        String cupoMaximo = txtCupoMaximo.getText();
        String idEntrenador = txtIdEntrenador.getText();

        TipoClase tipoSeleccionado = chTipoClase.getValue();
        String tipoUsuarioStr = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(nombre, horario, cupoMaximo, tipoSeleccionado, idEntrenador);

        if (datosValidos) {
            Clase clase = clasesController.actualizarClase(nombre, horario, cupoMaximo, tipoUsuarioStr, idEntrenador);
            if (clase != null) {
                mostrarMensaje("Notificación", "Actualización clase", "Clase actualizada", Alert.AlertType.CONFIRMATION);
                tableClases.refresh();
            } else {
                mostrarMensaje("Notificación", "Actualización clase", "Clase no actualizada", Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Actualización clase", "Campos vacios", Alert.AlertType.INFORMATION);
        }
    }
    private void eliminarClase(){
        String nombre=txtNombre.getText();
        boolean datosValidos = validarCamposEliminar(nombre);
        if (!datosValidos) {
            mostrarMensaje("Notificación", "Eliminación clase", "Campos vacíos", Alert.AlertType.INFORMATION);
            return;
        }
        Clase claseEliminada = clasesController.eliminarClase(nombre);
        if (claseEliminada != null) {
            listaClases.removeIf(clase -> clase.getNombre().equalsIgnoreCase(nombre));
            tableClases.refresh();

            mostrarMensaje("Notificación", "Eliminación clase", "Clase eliminada", Alert.AlertType.CONFIRMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación", "Eliminación clase", "Clase no encontrada", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCamposEliminar(String nombre) {
        return !nombre.isEmpty();
    }

    private boolean validarCampos(String nombre, String horario, String cupoMaximo, TipoClase tipo, String idEntrenador) {
        return !nombre.isEmpty() && !horario.isEmpty() && !cupoMaximo.isEmpty() && tipo != null && idEntrenador != null;
    }

    private void initView() {
        initDataBinding();
        obtenerClases();
        tableClases.getItems().clear();
        tableClases.setItems(listaClases);
        listenerSelection();
    }

    private void obtenerClases() {
        listaClases.addAll(clasesController.obtenerClases());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcHorario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHorario()));
        tcCupoMaximo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCupoMaximo())));
        tcTipoClase.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipoClase())));
        tcEntrenador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEntrenador().getNombre()));
        tcUsuariosRegistrados.setCellValueFactory(cellData -> new SimpleStringProperty(
                String.valueOf(cellData.getValue().getListaUsuariosRegistrados() != null ? cellData.getValue().getListaUsuariosRegistrados().size() : 0))
        );
    }

    private void listenerSelection() {
        tableClases.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            claseSelecionada = newSelection;
            mostrarInformacion(claseSelecionada);

            if (claseSelecionada != null) {
                int cantidad = claseSelecionada.getListaUsuariosRegistrados().size();
                lblUsuariosRegistrados.setText(String.valueOf(cantidad));
            } else {
                lblUsuariosRegistrados.setText("Usuarios registrados: 0");
            }
        });
    }
    private void mostrarInformacion(Clase claseSeleccionada) {
        if(claseSeleccionada != null) {
            txtNombre.setText(claseSeleccionada.getNombre());
            txtHorario.setText(claseSeleccionada.getHorario());
            txtCupoMaximo.setText(String.valueOf(claseSeleccionada.getCupoMaximo()));
            txtIdEntrenador.setText(claseSeleccionada.getEntrenador().getNombre());
            chTipoClase.setValue(claseSeleccionada.getTipoClase());
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    private void limpiarCampos() {
        txtNombre.clear();
        txtHorario.clear();
        txtCupoMaximo.clear();
        txtIdEntrenador.clear();
        chTipoClase.setValue(null);
        claseSelecionada = null;
        tableClases.getSelectionModel().clearSelection();
    }
}