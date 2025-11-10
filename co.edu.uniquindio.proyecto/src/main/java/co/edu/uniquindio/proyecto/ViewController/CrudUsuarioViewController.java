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
    private Button btnEliminar;

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
        actualizarUsuario();
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearUsuario();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarUsuario();
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

        if (datosValidos){
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

    private void actualizarUsuario(){
        String nombre=txtNombre.getText();
        String identificacion=txtIdentificacion.getText();
        String edad=txtEdad.getText();
        String telefono=txtTelefono.getText();

        TipoUsuario tipoSeleccionado = chTipoUsuario.getValue();
        String tipoUsuarioStr = tipoSeleccionado != null ? tipoSeleccionado.name() : null;

        boolean datosValidos = validarCamposActualizar(nombre, identificacion, edad, telefono, tipoSeleccionado);
        if (datosValidos){
            Usuario usuario=usuarioController.actualizarUsuario(nombre, identificacion, edad, telefono, tipoUsuarioStr);
            if(usuario!=null){
                mostrarMensaje("Notificación", "Actualización usuario", "Usuario actualizado",Alert.AlertType.CONFIRMATION);
                tableUsuario.refresh();
            } else {
                mostrarMensaje("Notificación", "Actualización usuario", "Usuario no actualizado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Actualización usuario", "Campos vacios",Alert.AlertType.INFORMATION);
        }

    }
    private void eliminarUsuario(){
        String identificacion=txtIdentificacion.getText();
        boolean datosValidos = validarCamposEliminar(identificacion);
        if (!datosValidos) {
            mostrarMensaje("Notificación", "Eliminación usuario", "Campos vacíos", Alert.AlertType.INFORMATION);
            return;
        }
        Usuario usuarioEliminado = usuarioController.eliminarUsuario(identificacion);
        if (usuarioEliminado != null) {
            listaUsuarios.removeIf(usuario -> usuario.getIdentificacion().equalsIgnoreCase(identificacion));
            tableUsuario.refresh();

            mostrarMensaje("Notificación", "Eliminación usuario", "Usuario eliminado", Alert.AlertType.CONFIRMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación", "Eliminación usuario", "Usuario no encontrado", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCamposEliminar(String identificacion) {
        return !identificacion.isEmpty();
    }

    private boolean validarCampos(String nombre, String identificacion, String edad, String telefono, TipoUsuario tipoSeleccionado) {
        return !nombre.isEmpty() && !identificacion.isEmpty() && !edad.isEmpty() && !telefono.isEmpty();
    }

    private boolean validarCamposActualizar(String nombre, String identificacion, String edad, String telefono, TipoUsuario tipoSeleccionado) {
        return !nombre.isEmpty() && !identificacion.isEmpty() && !edad.isEmpty() && !telefono.isEmpty();
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
        return action.get() == ButtonType.OK;
    }
    private void limpiarCampos() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtEdad.clear();
        txtTelefono.clear();
        chTipoUsuario.setValue(null);
        usuarioSelecionado = null;
        tableUsuario.getSelectionModel().clearSelection();
    }
}