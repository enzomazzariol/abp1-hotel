package Servlets;

import Service.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsuariosServlet extends HttpServlet {

    UsuarioService us = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        us.listarUsuarios(req, resp); // Llama al método de listar usuarios
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        us.menuPostUsuario(req, resp); // Llama al método de manejar acciones POST
    }
}
