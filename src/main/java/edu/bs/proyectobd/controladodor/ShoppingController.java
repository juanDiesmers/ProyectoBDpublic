package edu.bs.proyectobd.controladodor;

import edu.bs.proyectobd.negocio.CatalogoCarrito;
import edu.bs.proyectobd.negocio.CatalogoNegocio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingController implements Initializable {
    @FXML
    private VBox catalogContainer;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button back;
    @FXML
    private Button carritoButton;
    private CatalogoNegocio catalogoNegocio;


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        catalogoNegocio = new CatalogoNegocio();
        catalogoNegocio.mostrarCatalogo(catalogContainer);
    }

    public void onRegresarClick(ActionEvent actionEvent) {
        try{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bs/proyectobd/ScreenLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }catch (IOException ex){
            System.out.println("Error to charge screen"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void onAbrirClick(ActionEvent actionEvent) {
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
}

