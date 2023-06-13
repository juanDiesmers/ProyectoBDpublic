package edu.bs.proyectobd.controladodor;

import edu.bs.proyectobd.dominio.UsersLoggedin;
import edu.bs.proyectobd.negocio.NegocioAdmin;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController  implements Initializable {
    private  NegocioAdmin negocioAdmin;
    @FXML
    private TableView<UsersLoggedin> usuariosTable;
    @FXML
    private TableColumn<UsersLoggedin, String> nombreColumn;
    @FXML
    private TableColumn<UsersLoggedin, LocalDateTime> fechaColumn;
    @FXML
    private TableColumn<UsersLoggedin, Integer> cantidadFotosColumn;
    @FXML
    private TableColumn<UsersLoggedin, Float> precioColumn;
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        negocioAdmin = new NegocioAdmin();
        configurarColumnas();
        cargarUsuariosIngresados();

    }
    private void configurarColumnas() {
        //Configuramos la propiedades de valor de la colubna "Nombre" para que mueste el nombde de usuario
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        //Configuramos la propiedad de valor de la colubna "fecha" para que muestre la fecha de ingreso del usuario
        fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        // Configuramos la propiedad de valor de la columna "Cantidad Fotos" para que muestre la cantidad de fotos compradas
        cantidadFotosColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getCantidadFotos()));
        // Configuramos la propiedad de valor de la columna "Precio" para que muestre el precio de la compra
        precioColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(()->cellData.getValue().getPrecio()));
    }
    private void cargarUsuariosIngresados() {
        List<UsersLoggedin> usuariosIngresados = negocioAdmin.obtenerUsuariosIngresados();// Obtener los usuarios ingresados
        usuariosTable.getItems().addAll(usuariosIngresados);//Agregamos los usuarios a la tabla
    }

    @FXML
    public void onRegresarClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bs/proyectobd/ScreenLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Error to charge screen" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
