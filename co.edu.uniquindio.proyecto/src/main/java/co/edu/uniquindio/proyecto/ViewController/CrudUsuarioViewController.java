package co.edu.uniquindio.proyecto.ViewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyecto.controller.UsuarioController;
import co.edu.uniquindio.proyecto.model.TipoUsuario;
import co.edu.uniquindio.proyecto.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;

public class CrudUsuarioViewController implements Initializable {

    UsuarioController usuarioController;
    ObservableList<Usuario> listaUsuarios=FXCollections.observableArrayList();
    Usuario usuarioSelecionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnNuevo;

    @FXML
    private ChoiceBox<TipoUsuario> chTipoUsuario;

    @FXML
    private TableView<Usuario> tableUsuario;

    @FXML
    private TableColumn<Usuario, String> tcEdad;

    @FXML
    private TableColumn<Usuario, String> tcIdentificacion;

    @FXML
    private TableColumn<Usuario, String> tcNombre;

    @FXML
    private TableColumn<Usuario, String> tcTelefono;

    @FXML
    private TableColumn<Usuario, String> tcTipoUsuario;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    void onActionActualizar(ActionEvent event) {

    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearUsuario();
    }

    @FXML
    void onActionNuevo(ActionEvent event) {

    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TipoUsuario> tipos = FXCollections.observableArrayList(TipoUsuario.values());
        chTipoUsuario.setItems(tipos);
        chTipoUsuario.setValue(null);

        usuarioController = new UsuarioController();
        initView();
    }

    private void crearUsuario() {
        String nombre=txtNombre.getText();
        String identificacion=txtIdentificacion.getText();
        String edad=txtEdad.getText();
        String telefono=txtTelefono.getText();

        TipoUsuario tipoSeleccionado = chTipoUsuario.getValue();
        String tipoUsuarioStr = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCampos(nombre, identificacion, edad, telefono, tipoSeleccionado);

        if (datosValidos==true){
            Usuario usuario=usuarioController.crearUsuario(nombre, identificacion, edad, telefono, tipoUsuarioStr);
            if(usuario!=null){
                mostrarMensaje("Notificación", "Creación usuario", "Usuario creado",Alert.AlertType.CONFIRMATION);
                listaUsuarios.add(usuario);
            } else {
                mostrarMensaje("Notificación", "Creación usuario", "Usuario no creado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación usuario", "Campos vacios",Alert.AlertType.INFORMATION);
        }

    }

    private boolean validarCampos(String nombre, String identificacion, String edad, String telefono, TipoUsuario tipoSeleccionado) {
        if (nombre.isEmpty() || identificacion.isEmpty() || edad.isEmpty() || telefono.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void initView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(listaUsuarios);
        listenerSelection();
    }

    private void obtenerUsuarios() {
        listaUsuarios.addAll(usuarioController.obtenerUsuarios());
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

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

}