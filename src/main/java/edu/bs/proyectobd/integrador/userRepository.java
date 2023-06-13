package edu.bs.proyectobd.integrador;

import edu.bs.proyectobd.dominio.User;
import edu.bs.proyectobd.negocio.constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class userRepository {

    public static List<User> Userlogin() {
        String SQL = "SELECT NOMBREUSUARIO, CONTRASEÑA FROM usuario";
        List<User> users = new ArrayList<>();

        try (Connection conex = DriverManager.getConnection(constants.THINCONN,
                constants.USERNAME,
                constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(
                     SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUser(rs.getString("NOMBREUSUARIO"));
                user.setPassword(rs.getString("CONTRASEÑA"));
                users.add(user);
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failure: " + ex.toString());
            ex.printStackTrace();
        }

        return users;
    }
    public static void addUser(String username, String fechaIngreso) {
        Long primaryKey = null;
        String SQL = "INSERT INTO USUARIOS_INGRESA (USUARIO, FECHA) VALUES (?, CURRENT_TIMESTAMP)";

        try (Connection conex = DriverManager.getConnection(
                constants.THINCONN,
                constants.USERNAME,
                constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(
                     SQL,
                     new String[]{"id"}
             );) {

            ps.setString(1, username);
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
