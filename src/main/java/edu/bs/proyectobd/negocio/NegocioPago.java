package edu.bs.proyectobd.negocio;

import edu.bs.proyectobd.controladodor.PaymentController;

public class NegocioPago {
    private static PaymentController paymentController;
    private  static  NegocioPago instancia;


    public static PaymentController getPaymentController(){
        return paymentController;
    }

    public static void setPaymentController(PaymentController controller) {
        paymentController = controller;
    }
    public static NegocioPago getInstance(){
        if(instancia == null){
            instancia = new NegocioPago();
        }
        return instancia;
    }

    public static float calcularSaldoRestante(float totalAPagar, int moneda, int cantidad) {
        //Realizar los calculos nesesarios
        float montoPago = moneda * cantidad;
        float saldoRestante = totalAPagar - montoPago;

        return saldoRestante;
    }
    public float calcularMontoPago(float totalAPagar, int moneda, int cantidad) {
        return (moneda * cantidad)-totalAPagar;
    }


    public static float calcularTotal(){
        float total = CatalogoCarrito.calcularTotal();
        return total;
    }

}

