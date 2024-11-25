package servlets;

import com.google.gson.Gson;
import service.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/usuarioJSON")
public class UsuariosJSON extends HttpServlet {

   UsuarioService usuariosService;

    public UsuariosJSON() {
        usuariosService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Maneja la solicitud GET para obtener los datos del usuario
        usuariosService.getUsuarioApp(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Maneja la solicitud PUT para actualizar los datos del usuario
        usuariosService.updateUsuarioApp(req, resp);
    }
}
