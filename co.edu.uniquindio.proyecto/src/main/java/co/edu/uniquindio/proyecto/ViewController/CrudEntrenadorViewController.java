package co.edu.uniquindio.proyecto.ViewController;

import co.edu.uniquindio.proyecto.controller.EntrenadorController;
import co.edu.uniquindio.proyecto.model.Entrenador;
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

    }

    @FXML
    void onActionEliminar(ActionEvent event) {

    }

    public void initialize() {
        entrenadorController = new EntrenadorController();
        initView();
    }

    private void crearEntrenador() {
        String nombre=txtNombre.getText();
        String identificacion=txtIdentificacion.getText();
        String edad=txtEdad.getText();
        String telefono=txtTelefono.getText();

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
        listaEntrenadores.addAll(entrenadorController.obtenerEntrenador());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tcClasesAsignadas.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getClaseAsignadas()));
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
            tcClasesAsignadas.setText(entrenadorSelecionado.getClasesAsignadasTexto());
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