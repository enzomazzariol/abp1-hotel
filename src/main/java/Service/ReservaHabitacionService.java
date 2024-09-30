package Service;

import Model.ReservaHabitacion;
import Utils.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ReservaHabitacionService {
    private ArrayList<ReservaHabitacion> reservaHabitaciones;

    public void mostrarReservaHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reservaHabitaciones = new ArrayList<>();
        reservaHabitaciones.add(new ReservaHabitacion(1, 1, Estado.RESERVADO, "01-02-2024", 1, "01-02-2024", "03-02-2024"));
        req.setAttribute("reservaHabitacion", reservaHabitaciones);

        RequestDispatcher dispatcher= req.getRequestDispatcher("/jsp/reservaHabitacion.jsp");
        dispatcher.forward(req, resp);
    }

    public void crearReservaHabitacion(HttpServletRequest req, HttpServletResponse resp){
        reservaHabitaciones = new ArrayList<>();

        int id = Integer.parseInt(req.getParameter("id"));
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String estadoParam = req.getParameter("estado");
        String fechaReserva = req.getParameter("fechaReserva");
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());
        ReservaHabitacion reservaHabitacion = new ReservaHabitacion(id, idUsuario, estado, fechaReserva, idHabitacion, fechaEntrada, fechaSalida);
        reservaHabitaciones.add(reservaHabitacion);

        System.out.println(reservaHabitaciones);
    }
}
