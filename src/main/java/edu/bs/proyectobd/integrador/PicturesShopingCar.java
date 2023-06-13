package edu.bs.proyectobd.integrador;

import edu.bs.proyectobd.negocio.constants;

import java.sql.*;
import java.time.LocalDate;

public class PicturesShopingCar {

    public void  agregarCompra(String comprador, String fecha, int cantidadFotos, float precio, int usuarioIngresaID ){
        Long primaryKey = null;
        String SQL = "INSERT INTO COMPRA (COMPRADOR, FECHA, CANTIDADFOTO, PRECIO, USUARIOS_INGRESAID) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?) ";

        try (Connection conex = DriverManager.getConnection(constants.THINCONN,
                constants.USERNAME,
                constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(
                     SQL,
                     new String[]{"id"}
             );) {

            ps.setString(1, comprador);
            ps.setInt(2, cantidadFotos);
            ps.setFloat(3, precio);
            ps.setInt(4, usuarioIngresaID);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(null != generatedKeys && generatedKeys.next()){
                primaryKey = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failure: " + ex.toString());
            ex.printStackTrace();
        }
    }
}
