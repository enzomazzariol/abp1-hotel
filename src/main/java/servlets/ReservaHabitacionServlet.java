package servlets;

import service.ReservaHabitacionService;
import excepciones.ConexionException;
import excepciones.ReservaHabitacionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/reservaHabitacion")
public class ReservaHabitacionServlet extends HttpServlet {

    ReservaHabitacionService rhs;

    public ReservaHabitacionServlet() {
        this.rhs = new ReservaHabitacionService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rhs.forwardReservaHabitacion(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rhs.menuPostReservaHabitacion(req, resp);
    }
}
