package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.EntrenadorController;
import co.edu.uniquindio.proyecto.model.Entrenador;
import co.edu.uniquindio.proyecto.model.TipoUsuario;
import co.edu.uniquindio.proyecto.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        crearEntrenador();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarEntrenador();
    }

    public void initialize() {
        entrenadorController = new EntrenadorController();
        initView();
    }

    private void crearEntrenador() {
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String edad = txtEdad.getText();
        String telefono = txtTelefono.getText();

        boolean datosValidos = validarCampos(nombre, identificacion, edad, telefono);
        if (datosValidos==true){
            Entrenador entrenador=entrenadorController.crearEntrenador(nombre,
                    identificacion,
                    edad,
                    telefono);
            if(entrenador!=null){
                mostrarMensaje("Notificación", "Creación entrenador", "Entrenador creado",Alert.AlertType.CONFIRMATION);
                listaEntrenadores.add(entrenador);
            } else {
                mostrarMensaje("Notificación", "Creación entrenador", "Entrenador no creado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Creación entrenador", "Campos vacios",Alert.AlertType.INFORMATION);
        }
    }

    private void actualizarEntrenador() {
        String nombre=txtNombre.getText();
        String identificacion=txtIdentificacion.getText();
        String edad=txtEdad.getText();
        String telefono=txtTelefono.getText();


        boolean datosValidos = validarCamposActualizar(nombre, identificacion, edad, telefono);
        if (datosValidos==true){
            Entrenador entrenador =entrenadorController.actualizarEntrenador(nombre, identificacion, edad, telefono);
            if(entrenador !=null){
                mostrarMensaje("Notificación", "Actualización entrenador", "Entrenador actualizado",Alert.AlertType.CONFIRMATION);
                tableEntrenador.refresh();
            } else {
                mostrarMensaje("Notificación", "Actualización entrenador", "Entrenador no actualizado",Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Notificación", "Actualización entrenador", "Campos vacios",Alert.AlertType.INFORMATION);
        }
    }

    private void eliminarEntrenador() {
        String identificacion=txtIdentificacion.getText();
        boolean datosValidos = validarCamposEliminar(identificacion);
        if (!datosValidos) {
            mostrarMensaje("Notificación", "Eliminación entrenador", "Campos vacíos", Alert.AlertType.INFORMATION);
            return;
        }
        Entrenador entrenadorEliminado = entrenadorController.eliminarEntrenador(identificacion);
        if (entrenadorEliminado != null) {
            listaEntrenadores.removeIf(entrenador -> entrenador.getIdentificacion().equalsIgnoreCase(identificacion));
            tableEntrenador.refresh();

            mostrarMensaje("Notificación", "Eliminación entrenador", "Entrenador eliminado", Alert.AlertType.CONFIRMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación", "Eliminación entrenador", "Entrenador no encontrado", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCamposEliminar(String identificacion) {
        if(identificacion.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    private boolean validarCampos(String nombre, String identificacion, String edad, String telefono) {
        if (nombre.isEmpty() || identificacion.isEmpty() || edad.isEmpty() || telefono.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validarCamposActualizar(String nombre, String identificacion, String edad, String telefono) {
        if (nombre.isEmpty() || identificacion.isEmpty() || edad.isEmpty() || telefono.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void initView() {
        initDataBinding();
        obtenerEntrenadores();
        tableEntrenador.getItems().clear();
        tableEntrenador.setItems(listaEntrenadores);
        listenerSelection();
    }

    private void obtenerEntrenadores() {
        listaEntrenadores.addAll(entrenadorController.obtenerEntrenadores());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tcClasesAsignadas.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getClasesAsignadasTexto())));
    }


    private void listenerSelection() {
        tableEntrenador.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            entrenadorSelecionado = newSelection;
            mostrarInformacion(entrenadorSelecionado);
        });
    }

    private void mostrarInformacion(Entrenador entrenadorSelecionado) {
        if(entrenadorSelecionado != null){
            txtNombre.setText(entrenadorSelecionado.getNombre());
            txtIdentificacion.setText(entrenadorSelecionado.getIdentificacion());
            txtEdad.setText(String.valueOf(entrenadorSelecionado.getEdad()));
            txtTelefono.setText(entrenadorSelecionado.getTelefono());
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
        txtNombre.clear();
        txtIdentificacion.clear();
        txtEdad.clear();
        txtTelefono.clear();
        entrenadorSelecionado = null;
        tableEntrenador.getSelectionModel().clearSelection();
    }

}