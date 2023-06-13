package edu.bs.proyectobd.integrador;




import edu.bs.proyectobd.dominio.Picture;
import edu.bs.proyectobd.negocio.constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class pictureRepository {
    public static List<Picture> obtenerImagenes() {
        String SQL = "SELECT RUTA, DESCRIPCION, PRECIOFOTO FROM FOTOS";
        List<Picture> picture = new ArrayList<>();

        try (Connection conex = DriverManager.getConnection(constants.THINCONN, constants.USERNAME, constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Picture imagen = new Picture();
                imagen.setImageRute(rs.getString("RUTA"));
                imagen.setDescription(rs.getString("DESCRIPCION"));
                imagen.setPrice((int) rs.getDouble("PRECIOFOTO"));
                picture.add(imagen);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex.toString());
            ex.printStackTrace();
        }

        return picture;
    }
}
