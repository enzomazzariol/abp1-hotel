package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginService {

    // POST
    public void comprobarLogin(HttpServletRequest req, HttpServletResponse resp) {
        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");

        // Logica para comprobar el nombre y la contraseña.

        System.out.println("El usuario es " + nombre + " y la contraseña es " + password);

    }

    // GET
    public void enviarLogin(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Hola este es el get.");
    }


}
