package edu.bs.proyectobd.negocio;

import edu.bs.proyectobd.dominio.UsersLoggedin;
import edu.bs.proyectobd.integrador.UserLoggedinRepository;

import java.util.List;

public class NegocioAdmin {
    private UserLoggedinRepository userLoggedinRepository;

    public  NegocioAdmin(){
        userLoggedinRepository = new UserLoggedinRepository();
    }

    public List<UsersLoggedin> obtenerUsuariosIngresados(){
        return userLoggedinRepository.getUsuariosIngresados();
    }
}
