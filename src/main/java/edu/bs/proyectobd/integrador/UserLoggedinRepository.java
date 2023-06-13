package edu.bs.proyectobd.integrador;

import edu.bs.proyectobd.dominio.User;
import edu.bs.proyectobd.dominio.UsersLoggedin;
import edu.bs.proyectobd.negocio.constants;

import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserLoggedinRepository {
    public List<UsersLoggedin> getUsuariosIngresados() {
        List<UsersLoggedin> usuariosIngresados = new ArrayList<>();

        String SQL = "SELECT U.USUARIO, U.FECHA, C.CANTIDADFOTO, C.PRECIO " +
                "FROM USUARIOS_INGRESA U " +
                "LEFT JOIN COMPRA C ON U.ID = C.USUARIOS_INGRESAID " +
                "UNION " +
                "SELECT U.USUARIO, U.FECHA, NULL AS CANTIDADFOTO, NULL AS PRECIO " +
                "FROM USUARIOS_INGRESA U " +
                "WHERE U.ID NOT IN (SELECT USUARIOS_INGRESAID FROM COMPRA)";

        try (Connection conex = DriverManager.getConnection(constants.THINCONN,
                constants.USERNAME,
                constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("USUARIO");
                LocalDateTime fecha = rs.getTimestamp("FECHA").toLocalDateTime();
                Integer cantidadFotos = rs.getInt("CANTIDADFOTO");
                Float precio = rs.getFloat("PRECIO");

                UsersLoggedin user = new UsersLoggedin(nombre, fecha, cantidadFotos, precio);
                usuariosIngresados.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios ingresados: " + e.getMessage());
        }

        return usuariosIngresados;
    }

    public static int obtenerUsuariosIngresaID(String nombreUsuario){
        int usuariosIngresaID = 0;

        String SQL = "SELECT ID FROM USUARIOS_INGRESA WHERE USUARIO = ?";

        try (Connection conex = DriverManager.getConnection(constants.THINCONN,
                constants.USERNAME,
                constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL);) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuariosIngresaID = rs.getInt("ID");
            }
            System.out.println(usuariosIngresaID);

        } catch (SQLException e) {
            System.out.println("Error al obtener usuariosIngresaID: " + e.getMessage());
        }

        return usuariosIngresaID;
    }

}

