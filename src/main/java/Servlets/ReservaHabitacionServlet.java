package Servlets;

import Utils.Estado;
import Model.ReservaHabitacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/reservaHabitacion")
public class ReservaHabitacionServlet extends HttpServlet {

    private ArrayList<ReservaHabitacion> reservaHabitaciones;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        reservaHabitaciones = new ArrayList<>();

        reservaHabitaciones.add(new ReservaHabitacion(1, 1, Estado.RESERVADO, "01-02-2024", "01-02-2024", "03-02-2024"));
        req.setAttribute("reservaHabitacion", reservaHabitaciones);
        getServletContext().getRequestDispatcher("/jsp/reservaHabitacion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       reservaHabitaciones = new ArrayList<>();

       int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
       int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
       String estadoParam = req.getParameter("estado");
       String fechaReserva = req.getParameter("fechaReserva");
       String fechaEntrada = req.getParameter("fechaEntrada");
       String fechaSalida = req.getParameter("fechaSalida");

       Estado estado = Estado.valueOf(estadoParam.toUpperCase());
       ReservaHabitacion reservaHabitacion = new ReservaHabitacion(idUsuario, idHabitacion, estado, fechaReserva, fechaEntrada, fechaSalida);
       reservaHabitaciones.add(reservaHabitacion);

       System.out.println(reservaHabitaciones);
    }
}
