package Service;

import DAO.LoginDAO;
import Model.Usuario;
import Servlets.LoginServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginService {

    LoginDAO loginDAO;

    public LoginService() {
        this.loginDAO = new LoginDAO();
    }

    // POST
    public void comprobarLogin(HttpServletRequest req, HttpServletResponse resp, LoginServlet loginServlet) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");

        // Logica para comprobar el nombre y la contraseña.
        Usuario usuarioActual = this.loginDAO.checklogin(nombre, password);
        if (usuarioActual.getId() == 0) {
            req.setAttribute("error", "Error en el usuario o contraseña.");
            System.out.println("error");
            req.getRequestDispatcher("/jsp/error.jsp"). forward(req, resp);
        } else {
            req.setAttribute("nombre", nombre);
            System.out.println("El usuario es " + nombre + " y la contraseña es " + password);
            req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
        }
    }

    // GET
    public void forwardLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hola este es el get.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }


}
