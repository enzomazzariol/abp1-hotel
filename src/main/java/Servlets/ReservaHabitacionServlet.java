package Servlets;

import Service.ReservaHabitacionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/reservaHabitacion")
public class ReservaHabitacionServlet extends HttpServlet {

    ReservaHabitacionService rhs = new ReservaHabitacionService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rhs.mostrarReservaHabitacion(req, resp);
        rhs.listarReservaHabitacion();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            rhs.menuPostReservaHabitacion(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
