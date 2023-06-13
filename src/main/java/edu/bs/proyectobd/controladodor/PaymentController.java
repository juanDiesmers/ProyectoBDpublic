package edu.bs.proyectobd.controladodor;

import edu.bs.proyectobd.dominio.User;
import edu.bs.proyectobd.dominio.UsersLoggedin;
import edu.bs.proyectobd.integrador.PicturesShopingCar;
import edu.bs.proyectobd.integrador.UserLoggedinRepository;
import edu.bs.proyectobd.negocio.CatalogoCarrito;
import edu.bs.proyectobd.negocio.NegocioPago;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    private NegocioPago negocioPago;
    @FXML
    private Label totalLabel;
    @FXML
    private Label saldoInicioLabel;
    @FXML
    private Label dineroFaltanteLabel;
    @FXML
    private ChoiceBox<String> monedaChoiceBox;
    @FXML
    private Spinner<Integer> cantidadSpinner;
    @FXML
    private Button agregarButton;
    @FXML
    private Button terminarButton;
    @FXML
    private Button back;
    private float saldoRestante;
    private  float dineroInicial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        negocioPago = NegocioPago.getInstance();
        NegocioPago.setPaymentController(this);
        // Configurar listener para el choiceBox
        monedaChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarDineroFaltante();
        });
        // Actualizar el total al inicio
        actualizarTotal();
        // Obtener el dinero inicial
        actualizarDineroInicial();

        actualizarDineroFaltante();
    }

    @FXML
    public void onAgregarClick(ActionEvent actionEvent) {
        int moneda = obtenerMonedaSeleccionada();
        int cantidad = cantidadSpinner.getValue();

        float totalAPagar = CatalogoCarrito.calcularTotal();
        float nuevoSaldoRestante = negocioPago.calcularSaldoRestante(totalAPagar, moneda, cantidad);

        actualizarDineroFaltante();
        actualizarDineroInicial();
        cantidadSpinner.getValueFactory().setValue(0);
    }

    @FXML
    public void onRegresarClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bs/proyectobd/ScreenPurchase.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Error to charge screen" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void actualizarTotal() {
        float totalAPagar = negocioPago.calcularTotal();
        totalLabel.setText("$" + totalAPagar);
    }

    private void actualizarDineroFaltante() {
        float nuevoSaldoRestante = dineroInicial - obtenerMontoPago();
        saldoRestante = nuevoSaldoRestante;
        dineroFaltanteLabel.setText("$" + saldoRestante);
    }

    private  void actualizarDineroInicial(){
        float dineroInicial = obtenerDineroInicial();
        saldoInicioLabel.setText("$" + dineroInicial);
    }
    private int obtenerMonedaSeleccionada() {
        String opcion = monedaChoiceBox.getValue();
        return opcion != null ? Integer.parseInt(opcion) : 0;
    }

    private float obtenerMontoPago() {
        int moneda = obtenerMonedaSeleccionada();
        int cantidad = cantidadSpinner.getValue();
        float totalAPagar = CatalogoCarrito.calcularTotal();
        return negocioPago.calcularMontoPago(totalAPagar, moneda, cantidad);
    }

    private float obtenerDineroInicial() {
        int cantidad = cantidadSpinner.getValue();
        int moneda = obtenerMonedaSeleccionada();
        float dineroInicial = cantidad *moneda;
        return dineroInicial;
    }


    @FXML
    public void onTerminarClick(ActionEvent actionEvent) {
        // Obtener el precio pagado
        float totalAPagar = CatalogoCarrito.calcularTotal();
        // Cantidad de fotos
        int cantidadFotos = CatalogoCarrito.getInstance().getCantidadFotos();
        // Obtener fecha del sistema
        LocalDate fechaCompra = LocalDate.now();
        String fechaStr = fechaCompra.toString();

        //Aca la idea es llamar al nombre que ingresa en el sistema
        String nombreUsuario = "amers";

        // Obtener usuariosIngresaID correspondiente al nombreUsuario
        int usuariosIngresaID = UserLoggedinRepository.obtenerUsuariosIngresaID(nombreUsuario);

        // Crear una instancia de PicturesShopingCar y agregar la compra a la base de datos
        PicturesShopingCar picturesShopingCar = new PicturesShopingCar();
        picturesShopingCar.agregarCompra(nombreUsuario, fechaStr, cantidadFotos, totalAPagar, usuariosIngresaID);
    }

}
