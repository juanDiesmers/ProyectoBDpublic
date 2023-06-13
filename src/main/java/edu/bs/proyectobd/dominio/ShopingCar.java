package edu.bs.proyectobd.dominio;

import javafx.scene.image.Image;

public class ShopingCar {
    private Image foto;
    private int cantidad;
    private float precio;
    private String descripcion;


    public ShopingCar(Image foto, int cantidad, float precio, String descripcion) {
        this.foto = foto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}