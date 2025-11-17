package co.edu.uniquindio.proyecto.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionViewController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private Button btnLogin;

    private static final String USUARIOA_VALIDO = "admin";
    private static final String PASSWORDA_VALIDA = "admin123";

    private static final String USUARIOR_VALIDO = "repc";
    private static final String PASSWORDR_VALIDA = "repc123";

    @FXML
    public void onLoginClick(ActionEvent event) {
            validarLogin();
        }
    private void validarLogin() {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario.isEmpty() || password.isEmpty()) {
            mostrarError("Por favor ingrese usuario y contraseña");
            return;
    }

        if (usuario.equals(USUARIOA_VALIDO) && password.equals(PASSWORDA_VALIDA)) {
            try {
                cargarVentanaAdministrador();
            } catch (IOException e) {
                mostrarError("Error al cargar la aplicación");
                e.printStackTrace();
            }
        } else {
            mostrarError("Usuario o contraseña incorrectos");
        }

        if (usuario.equals(USUARIOR_VALIDO) && password.equals(PASSWORDR_VALIDA)) {
            try {
                cargarVentanaRecepcionista();
            } catch (IOException e) {
                mostrarError("Error al cargar la aplicación");
                e.printStackTrace();
            }
        } else {
            mostrarError("Usuario o contraseña incorrectos");
        }
    }

    private void cargarVentanaAdministrador() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto/PantallaAdministrador.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setTitle("Sistema de Gimnasio - Gestión");
        stage.setScene(scene);
        stage.show();
    }

    private void cargarVentanaRecepcionista() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto/PantallaRecepcionista.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setTitle("Sistema de Gimnasio - Gestión");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setText(mensaje);
        lblMensaje.setVisible(true);
    }
}