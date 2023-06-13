package edu.bs.proyectobd.dominio;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsersLoggedin {
    private SimpleStringProperty nombre;//Popiedad para el nombre del usuario
    private ObjectProperty<LocalDateTime> fecha;//Propiedad para la fecha de ingreso del usuario
    private IntegerProperty cantidadFotos;
    private FloatProperty precio;

    public UsersLoggedin(String nombre, LocalDateTime fecha, int cantidadFotos, float precio) {
        this.nombre = new SimpleStringProperty(nombre);//Inicializacion de la propuedad del nombre
        this.fecha = new SimpleObjectProperty<>(fecha);//Inicializacion de la propiedad de la fecha
        this.cantidadFotos = new SimpleIntegerProperty(cantidadFotos);
        this.precio = new SimpleFloatProperty(precio);


    }




    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public LocalDateTime getFecha() {
        return fecha.get();
    }

    public ObjectProperty<LocalDateTime> fechaProperty() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha.set(fecha);
    }

    public IntegerProperty cantidadFotosProperty() {
        return cantidadFotos;
    }

    public int getCantidadFotos() {
        return cantidadFotos.get();
    }

    public void setCantidadFotos(int cantidadFotos) {
        this.cantidadFotos.set(cantidadFotos);
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public float getPrecio() {
        return precio.get();
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }


}
