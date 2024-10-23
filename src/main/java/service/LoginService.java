package service;

import dao.LoginDAO;
import model.Usuario;
import excepciones.ConexionException;
import excepciones.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginService {

    LoginDAO loginDAO;

    public LoginService() {
        this.loginDAO = new LoginDAO();
    }

    // POST
    public void comprobarLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException, LoginException, ConexionException {
        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");

        // Lista para acumular errores en el front
        ArrayList<String> errores = new ArrayList<>();

        // Validar campos vacios
        if (nombre == null || nombre.trim().isEmpty()) {
            errores.add("El campo 'nombre' es obligatorio.");
        }
        if (password == null || password.trim().isEmpty()) {
            errores.add("El campo 'contraseña' es obligatorio.");
        }

        // Si hay errores, reenviarlos al formulario de login
        if (!errores.isEmpty()) {
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        // Logica para comprobar el nombre y la contraseña.
        Usuario usuarioActual = this.loginDAO.checklogin(nombre, password);
        if (usuarioActual.getId() == 0) {
            errores.add("Usuario o contraseña incorrecto");
        } else if (usuarioActual.isEliminado()) {
            errores.add("El usuario ha sido eliminado");
        }

        if (!errores.isEmpty()) {
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {
            // Si no hay errores, el usuario es válido
            req.setAttribute("usuario", usuarioActual);
            HttpSession session = req.getSession();
            session.setAttribute("user", usuarioActual);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    // GET
    public void forwardLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }


}
