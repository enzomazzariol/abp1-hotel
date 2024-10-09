package Servlets;

import Service.UsuariosService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsuariosServlet extends HttpServlet {

    private UsuariosService usuariosService = new UsuariosService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usuariosService.listarUsuarios(req, resp); // Llama al método de listar usuarios
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usuariosService.menuPostUsuario(req, resp); // Llama al método de manejar acciones POST
    }
}
