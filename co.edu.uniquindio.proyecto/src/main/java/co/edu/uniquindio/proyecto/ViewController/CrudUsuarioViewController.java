package co.edu.uniquindio.proyecto.ViewController;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<TipoUsuario> tipos = FXCollections.observableArrayList(TipoUsuario.values());
        chTipoUsuario.setItems(tipos);


        chTipoUsuario.setValue(null);
    }
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
    private TextField txtApellido;

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

        System.out.println("Evento del boton capturado");

    }

    @FXML
    void onActionNuevo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        usuarioController = new UsuarioController();
        initView();
    }
    private void initView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(listaUsuarios);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tcTipoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoUsuario().toString()));
    }

    private void listenerSelection() {
        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSelecionado = newSelection;
            mostrarInformacion(usuarioSelecionado);
        });
    }

}