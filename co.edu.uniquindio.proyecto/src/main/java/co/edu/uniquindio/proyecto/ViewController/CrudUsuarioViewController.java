package co.edu.uniquindio.proyecto.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyecto.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CrudUsuarioViewController {

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
    private TableView<Usuario> tableEstudiante;

    @FXML
    private TableColumn<Usuario, String> tcAopellido;

    @FXML
    private TableColumn<Usuario, String> tcEdad;

    @FXML
    private TableColumn<Usuario, String> tcIdentificacion;

    @FXML
    private TableColumn<Usuario, String> tcNombre;

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
    }

}