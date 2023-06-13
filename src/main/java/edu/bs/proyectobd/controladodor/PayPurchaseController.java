package edu.bs.proyectobd.controladodor;

import edu.bs.proyectobd.dominio.ShopingCar;
import edu.bs.proyectobd.negocio.CatalogoCarrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class PayPurchaseController implements Initializable {

    @FXML
    private VBox carritoVBox;
    @FXML
    private Label totalLabel;
    @FXML
    private Button back;
    @FXML
    private Button eliminarCarrito;

    private  CatalogoCarrito catalogoCarrito;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button pago;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catalogoCarrito = CatalogoCarrito.getInstance();
        catalogoCarrito.cargarFotosCarrito(carritoVBox);
        // Establecer la referencia al controlador PayPurchaseController en CatalogoCarrito
        CatalogoCarrito.setPayPurchaseController(this);
        // Actualizar el total al inicio
        actualizarTotal();;
    }

        @FXML
    public void onRegresarClick(ActionEvent actionEvent) {
        try{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bs/proyectobd/ScreenShoping.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException ex){
            System.out.println("Error to charge screen"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void onElinarClick(ActionEvent actionEvent) {
        CatalogoCarrito.vaciarCarrito();
        catalogoCarrito.cargarFotosCarrito(carritoVBox);
    }

    public void actualizarTotal() {
        float total = CatalogoCarrito.calcularTotal();
        totalLabel.setText("Total: $" + total);
    }

    @FXML
    public void onPagarClick(ActionEvent actionEvent) {
        try{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bs/proyectobd/ScreenPayment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException ex){
            System.out.println("Error to charge screen"+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
