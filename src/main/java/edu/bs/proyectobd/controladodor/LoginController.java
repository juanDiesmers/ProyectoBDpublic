package edu.bs.proyectobd.controladodor;


import edu.bs.proyectobd.dominio.User;
import edu.bs.proyectobd.negocio.CatalogoCarrito;
import edu.bs.proyectobd.negocio.NegocioUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class LoginController {

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userName;
    @FXML
    private Button startSesion;

    @FXML
    public void onInicioSesionClik(ActionEvent actionEvent)  {
        String username = userName.getText();
        String password = passwordField.getText();

        if (NegocioUsuario.verifyUser(username, password)) {
            if(username.equals("Admin") && password.equals("Admin1")){
                try {
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader((getClass().getResource("/edu/bs/proyectobd/ScreenAdmin.fxml")));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }catch (IOException ex){
                    System.out.println("Error to charge screen" + ex.getMessage());
                    ex.printStackTrace();
                }
            }else {
                try {
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bs/proyectobd/ScreenShoping.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    CatalogoCarrito.getInstance().vaciarCarrito();
                    NegocioUsuario.almacenarUsuarios(username);

                } catch (IOException ex) {
                    System.out.println("Error to charge screen" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Credenciales invalidos");
            alert.setContentText("El usuario o la contraseña estan mal");
            alert.showAndWait();
        }
    }
}
