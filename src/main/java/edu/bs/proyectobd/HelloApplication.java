package edu.bs.proyectobd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar la vista de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreenLogin.fxml"));
            Pane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Tienda De fotos");
            primaryStage.setScene(scene);
            primaryStage.sizeToScene(); // Ajustar al tamaño del contenido
            primaryStage.show();

            // Crear un icono personalizado para la aplicación
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/edu/bs/proyectobd/Logo/LogoTienda.png")));
            primaryStage.getIcons().add(icon);

            // Mostrar la ventana principal
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}