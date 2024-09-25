package Servlets;

import Utils.Rol;
import Model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registro")
public class Registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Foward a la pagina registro
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registro.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // recuperar parametros del formulario registro
        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String rolParam = req.getParameter("rol");
        System.out.println(nombre + " " + password + " " + email + " " + rolParam);

        // Convertir el rol recibido del formulario a enum
        Rol rol = Rol.valueOf(rolParam.toUpperCase());

        Usuario nuevoUsuario = new Usuario(nombre, password, email, rol);
        req.setAttribute("usuario", nuevoUsuario);

    }
}
