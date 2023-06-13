package edu.bs.proyectobd.negocio;
import edu.bs.proyectobd.dominio.Picture;
import edu.bs.proyectobd.dominio.ShopingCar;
import edu.bs.proyectobd.integrador.pictureRepository;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

public class CatalogoNegocio {

    private CatalogoCarrito catalogoCarrito;


    public CatalogoNegocio(){
        catalogoCarrito = CatalogoCarrito.getInstance();
    }

    public void mostrarCatalogo(VBox catalogContainer) {
        List<Picture> imagenes = pictureRepository.obtenerImagenes();

        VBox container = new VBox(); // Contenedor para las fotos
        container.setSpacing(10);

        for (Picture imagen : imagenes) {
            String rutaImagen = imagen.getImageRute();

            // Crear el ImageView para mostrar la imagen
            ImageView imageView = new ImageView();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/edu/bs/proyectobd/Pictures/" + rutaImagen)));
            imageView.setImage(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            // Crear los elementos visuales para la descripción, precio y modificador de cantidad
            Label descripcionLabel = new Label(imagen.getDescription());
            Label precioLabel = new Label("$" + imagen.getPrice());
            Spinner<Integer> spinnerCantidad = new Spinner<>(0, 10, 0); // Valores mínimo, máximo e inicial

            // Crear un contenedor para agrupar los elementos de cada foto
            VBox fotoContainer = new VBox();
            fotoContainer.getChildren().addAll(imageView, descripcionLabel, precioLabel, spinnerCantidad);

            //Agregar el contenedro  al contenedor principal
            container.getChildren().add(fotoContainer);

            //agregar un controlador de eventos para el boton "Agregar al carrito"
            Button agregarCarritoButton = new Button("Agregar al Carrito");
            agregarCarritoButton.setOnAction(event -> {
                int cantidad = spinnerCantidad.getValue();
                if (cantidad > 0) {
                    float precio = imagen.getPrice();
                    String descripcion = imagen.getDescription();
                    Image foto = imageView.getImage();
                    //Crear el objeto ShopingCar y agregarlo al carrito
                    ShopingCar item = new ShopingCar(foto, cantidad, precio, descripcion);
                    catalogoCarrito.agregarAlCarrito(item);

                    spinnerCantidad.getValueFactory().setValue(0);
                    // Agregar la lógica adicional que desees realizar después de agregar al carrito
                } else {
                    // Mostrar mensaje de error o realizar alguna acción si la cantidad es 0
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al cargar la foto");
                    alert.setContentText("Se quiere agregar 0 fotos");
                    alert.showAndWait();
                }
                spinnerCantidad.getValueFactory().setValue(0);
            });

            //Agregar el boton al contenedor
            fotoContainer.getChildren().add(agregarCarritoButton);
        }

        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500); // Establecer la altura del ScrollPane

        catalogContainer.getChildren().add(scrollPane);
    }
}