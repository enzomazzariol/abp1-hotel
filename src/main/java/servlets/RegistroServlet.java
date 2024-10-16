package servlets;

import excepciones.ConexionException;
import excepciones.UsuariosException;
import service.RegistroService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    RegistroService registroService = new RegistroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registroService.fowardRegistro(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            registroService.registroUsuario(req, resp);
        } catch (ConexionException | UsuariosException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
