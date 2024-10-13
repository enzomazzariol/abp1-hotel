package servlets;

import service.UsuarioService;
import excepciones.ConexionException;
import excepciones.UsuariosException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet {

    UsuarioService us;

    public UsuariosServlet(){
        us = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            us.forwardUsuarios(req, resp); // Llama al método de forward usuarios
        } catch (SQLException | UsuariosException | ConexionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            us.menuPostUsuario(req, resp); // Llama al método de manejar acciones POST
        } catch (SQLException | ClassNotFoundException | UsuariosException | ConexionException e) {
            throw new RuntimeException(e);
        }
    }
}
