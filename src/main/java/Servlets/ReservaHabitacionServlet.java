package Servlets;

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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       reservaHabitaciones = new ArrayList<>();

       int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
       int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
       String estado = req.getParameter("estado");
       String fechaReserva = req.getParameter("fechaReserva");
       String fechaEntrada = req.getParameter("fechaEntrada");
       String fechaSalida = req.getParameter("fechaSalida");

       ReservaHabitacion reservaHabitacion = new ReservaHabitacion(idUsuario, idHabitacion, estado, fechaReserva, fechaEntrada, fechaSalida);
       reservaHabitaciones.add(reservaHabitacion);
    }
}
