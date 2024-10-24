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

@WebServlet("/perfil")
public class UsuariosServlet extends HttpServlet {

    UsuarioService us;

    public UsuariosServlet(){
        us = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        us.manejarPerfil(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        us.menuPostUsuario(req, resp);
    }
}
