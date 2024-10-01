package Service;

import DAO.Conexion;
import Utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    // POST
    public void comprobarLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");

        // Logica para comprobar el nombre y la contraseña.
        Connection conexion = new Conexion().conectar();
        try (PreparedStatement ps = conexion.prepareStatement(Constants.SELECT_USUARIOS_NOMBRE_CONTRASENA)) {
            ps.setString(1, nombre);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.getSession().setAttribute("nombre", nombre);
                enviarLogin(req, resp);
            } else {
                System.out.println("Error en el usuario o en la contraseña.");
            }

        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El usuario es " + nombre + " y la contraseña es " + password);

    }

    // GET
    public void enviarLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hola este es el get.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }


}
