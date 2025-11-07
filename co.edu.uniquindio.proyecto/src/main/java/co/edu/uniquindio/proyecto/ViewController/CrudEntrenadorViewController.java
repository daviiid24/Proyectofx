package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.EntrenadorController;
import co.edu.uniquindio.proyecto.controller.UsuarioController;
import co.edu.uniquindio.proyecto.model.Entrenador;
import co.edu.uniquindio.proyecto.model.TipoUsuario;
import co.edu.uniquindio.proyecto.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CrudEntrenadorViewController {

    EntrenadorController entrenadorController;
    ObservableList<Entrenador> listaEntrenadores=FXCollections.observableArrayList();
    Entrenador entrenadorSelecionado;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Entrenador> tableEntrenador;

    @FXML
    private TableColumn<Entrenador, String> tcClasesAsignadas;

    @FXML
    private TableColumn<Entrenador, String> tcEdad;

    @FXML
    private TableColumn<Entrenador, String> tcIdentificacion;

    @FXML
    private TableColumn<Entrenador, String> tcNombre;

    @FXML
    private TableColumn<Entrenador, String> tcTelefono;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void onActionActualizar(ActionEvent event) {
        actualizarEntrenador();
    }



    @FXML
    void onActionAgregar(ActionEvent event) {

    }

    @FXML
    void onActionEliminar(ActionEvent event) {

    }

    public void initialize() {
        entrenadorController = new EntrenadorController();
        initView();
    }

    private void actualizarEntrenador() {

    }

    private void initView() {
        initDataBinding();
        obtenerEntrenadores();
        tableEntrenador.getItems().clear();
        tableEntrenador.setItems(listaEntrenadores);
        listenerSelection();
    }

    private void obtenerEntrenadores() {
        listaEntrenadores.addAll(entrenadorController.obtenerUsuarios());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tcTipoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipoUsuario())));
    }


    private void listenerSelection() {
        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSelecionado = newSelection;
            mostrarInformacion(usuarioSelecionado);
        });
    }

    private void mostrarInformacion(Usuario usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            txtNombre.setText(usuarioSeleccionado.getNombre());
            txtIdentificacion.setText(usuarioSeleccionado.getIdentificacion());
            txtEdad.setText(String.valueOf(usuarioSeleccionado.getEdad()));
            txtTelefono.setText(usuarioSeleccionado.getTelefono());
            chTipoUsuario.setValue(usuarioSeleccionado.getTipoUsuario());
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

}