package edu.bs.proyectobd.dominio;

public class Picture {
    private String Description;
    private int Price;
    private String ImageRute;


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImageRute() {
        return ImageRute;
    }

    public void setImageRute(String imageRute) {
        ImageRute = imageRute;
    }

}
