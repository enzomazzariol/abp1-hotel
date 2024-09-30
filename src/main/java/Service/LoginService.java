package Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginService {

    // POST
    public void comprobarLogin(HttpServletRequest req, HttpServletResponse resp) {
        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");

        // Logica para comprobar el nombre y la contraseña.

        System.out.println("El usuario es " + nombre + " y la contraseña es " + password);

    }

    // GET
    public void enviarLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hola este es el get.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }


}
