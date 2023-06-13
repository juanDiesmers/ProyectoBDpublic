package edu.bs.proyectobd.negocio;

import edu.bs.proyectobd.dominio.User;
import edu.bs.proyectobd.integrador.userRepository;

import java.time.LocalDate;
import java.util.List;

public class NegocioUsuario {

    public static boolean verifyUser(String username, String password) {
        List<User> users = userRepository.Userlogin();
        for (User user : users) {
            if (user.getUser().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public static void almacenarUsuarios(String username){
        LocalDate fechaIngreso = LocalDate.now();
        String fechaIngresoStr = fechaIngreso.toString();

        userRepository.addUser(username, fechaIngresoStr);
    }


}
