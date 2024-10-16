package service;

import dao.UsuariosDAO;
import excepciones.ConexionException;
import excepciones.UsuariosException;
import model.Usuario;
import utils.Rol;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistroService {

    private UsuariosDAO usuariosDAO;

    public RegistroService(){
        usuariosDAO = new UsuariosDAO();
    }

    public void fowardRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Foward a la pagina registro
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registro.jsp");
        dispatcher.forward(req, resp);
    }

    public void registroUsuario(HttpServletRequest req, HttpServletResponse resp) throws ConexionException, SQLException, UsuariosException, ClassNotFoundException {
        // recuperar parametros del formulario registro

        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String rolParam = req.getParameter("rol");

        // Convertir el rol recibido del formulario a enum
        Rol rol = Rol.valueOf(rolParam.toUpperCase());

        Usuario nuevoUsuario = new Usuario(nombre, password, email, rol); // cambiar por CRUD
        usuariosDAO.insertarUsuario(nuevoUsuario);
        System.out.println(nuevoUsuario);
    }
}
