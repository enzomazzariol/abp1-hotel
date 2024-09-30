package Servlets;

import Model.ReservaHabitacion;
import Utils.Estado;
import Service.ReservaHabitacionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/reservaHabitacion")
public class ReservaHabitacionServlet extends HttpServlet {

    private List<ReservaHabitacion> reservasHabitaciones = new ArrayList<>();
    ReservaHabitacionService rhs = new ReservaHabitacionService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rhs.mostrarReservaHabitacion(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        Estado estado = Estado.valueOf(req.getParameter("estado"));
        String fechaReserva = req.getParameter("fechaReserva");
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");

        if ("actualizar".equals(action)) {
            int index = Integer.parseInt(req.getParameter("index"));
            ReservaHabitacion reservaHabitacion = reservasHabitaciones.get(index);
            reservaHabitacion.setIdHabitacion(idHabitacion);
            reservaHabitacion.setEstado(estado);
            reservaHabitacion.setFechaReserva(fechaReserva);
            reservaHabitacion.setFechaEntrada(fechaEntrada);
            reservaHabitacion.setFechaSalida(fechaSalida);
        } else if ("eliminar".equals(action)) {
            int index = Integer.parseInt(req.getParameter("index"));
            reservasHabitaciones.get(index).setEliminado(true); // Marcar como eliminado
        } else {
            // Crear nueva reserva
            ReservaHabitacion nuevaReserva = new ReservaHabitacion(idUsuario, idHabitacion, estado, fechaReserva, fechaEntrada, fechaSalida);
            reservasHabitaciones.add(nuevaReserva);
        }

        doGet(req, resp);
    }
}
