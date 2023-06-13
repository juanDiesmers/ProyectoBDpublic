package edu.bs.proyectobd.negocio;

import edu.bs.proyectobd.controladodor.PayPurchaseController;
import edu.bs.proyectobd.dominio.ShopingCar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CatalogoCarrito {

    private static List<ShopingCar> carrito;
    private static CatalogoCarrito instancia;

    private static PayPurchaseController payPurchaseController;

    public static PayPurchaseController getPayPurchaseController() {
        return payPurchaseController;
    }

    public static void setPayPurchaseController(PayPurchaseController controller) {
        payPurchaseController = controller;
    }

    private CatalogoCarrito() {
        carrito = new ArrayList<>();
    }

    public static CatalogoCarrito getInstance() {
        if (instancia == null) {
            instancia = new CatalogoCarrito();
        }
        return instancia;
    }

    public void agregarAlCarrito(ShopingCar item) {
        // Verificar si la foto ya existe en el carrito
        String descripcion = item.getDescripcion();
        for (ShopingCar carItem : carrito) {
            if (carItem.getDescripcion().equals(descripcion)) {
                int cantidadExistente = carItem.getCantidad();
                carItem.setCantidad(cantidadExistente + item.getCantidad());
                return; // Salir del método, la foto ya fue procesada
            }
        }
        // Si no se encontró una coincidencia en la descripción, agregar la foto al carrito
        carrito.add(item);
        if (payPurchaseController != null) {
            payPurchaseController.actualizarTotal();
        }
    }
    public void cargarFotosCarrito(VBox catalogoCarrito) {
        catalogoCarrito.getChildren().clear();

        //crear un VBox para cada elemento del carrito
        VBox itemBox = new VBox();
        itemBox.setSpacing(10);

        for (ShopingCar item : carrito) {

            //Agregar la imagen al Vbox
            ImageView imageView = new ImageView(item.getFoto());
            imageView.setFitWidth(100);//Establecer ancho deseado
            imageView.setPreserveRatio(true);

            //Agregar la descripcion al VBox
            Label descripcionLabel = new Label(item.getDescripcion());

            //Agregar el precio al VBox
            Label precioLabel = new Label("Precio $" + item.getPrecio());

            //Crear un Spinner para la cantidad
            Spinner<Integer> spinner = new Spinner<>(0, 10, item.getCantidad());
            spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                item.setCantidad(newValue);
                if (payPurchaseController != null) {
                    payPurchaseController.actualizarTotal();
                }
            });

            VBox fotoContainer = new VBox();
            fotoContainer.getChildren().addAll(imageView, descripcionLabel, precioLabel, spinner);

            itemBox.getChildren().add(fotoContainer);

            //Agregar boton de eliminar a cada foto
            Button eliminarButton = new Button("Eliminar");
            eliminarButton.setOnAction(event -> {
                //Eliminar elemento del carrito
                CatalogoCarrito.eliminarDelCarrito(item);
                //Volver a cargar las fotos Actualizadas en el carrito
                cargarFotosCarrito(itemBox);
            });
            itemBox.getChildren().add(eliminarButton);

        }
        //Crear un srollPane
        ScrollPane scrollPane = new ScrollPane(itemBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);//Establece la alture del ScrollPane
        //Agregar el VBox del elemento al Vbox principal carrito
        catalogoCarrito.getChildren().add(scrollPane);
    }

    public static void vaciarCarrito(){
        carrito.clear();
        if (payPurchaseController != null) {
            payPurchaseController.actualizarTotal();
        }
    }

    public static void  eliminarDelCarrito(ShopingCar item){
        carrito.remove(item);
        if (payPurchaseController != null) {
            payPurchaseController.actualizarTotal();
        }
    }

    public static  float calcularTotal(){
        float total= 0;
        for (ShopingCar item : carrito){
            total += item.getPrecio() * item.getCantidad();
        }
        return total;
    }

    public int getCantidadFotos() {
        int totalFotos = 0;
        // Sumar la cantidad seleccionada en el spinner para cada elemento del carrito
        for (ShopingCar item : carrito) {
            totalFotos += item.getCantidad();
        }

        return totalFotos;
    }

}
