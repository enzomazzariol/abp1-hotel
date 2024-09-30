package Servlets;

import Service.ReservaActividadService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reservaActividad")
public class ReservaActividadServlet extends HttpServlet {
    ReservaActividadService ras = new ReservaActividadService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ras.mostrarReservaActividad(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ras.crearReservaActividad(req, resp);
    }
}
