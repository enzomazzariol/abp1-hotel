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
        reservaHabitaciones.add(new ReservaHabitacion(1, 1, Estado.RESERVADO, "01-02-2024", false, 1, "01-02-2024", "03-02-2024"));
        req.setAttribute("reservaHabitacion", reservaHabitaciones);

        RequestDispatcher dispatcher= req.getRequestDispatcher("/jsp/reservaHabitacion.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostReservaHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarReservaHabitacion(req);
        } else if ("actualizar".equals(action)) {
            actualizarReservaHabitacion(req);
        } else if ("eliminar".equals(action)) {
            eliminarReservaHabitacion(req);
        }

        mostrarReservaHabitacion(req, resp); // Redirigir para mostrar la lista actualizada
    }
    private void agregarReservaHabitacion(HttpServletRequest req) {
        // Obtiene los datos de la nueva reserva de habitación
        int id = Integer.parseInt(req.getParameter("id"));
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        Estado estado = Estado.valueOf(req.getParameter("estado"));
        String fechaReserva = req.getParameter("fechaReserva");
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");
        boolean eliminado = Boolean.parseBoolean(req.getParameter("eliminado"));

        // Crea la nueva instancia de ReservaHabitacion
        ReservaHabitacion nuevaReserva = new ReservaHabitacion(id, idUsuario, estado, fechaReserva, eliminado, idHabitacion, fechaEntrada, fechaSalida);

        // Agrega la nueva reserva a la lista
        reservaHabitaciones.add(nuevaReserva);
        System.out.println("Se ha creado la reserva de habitación: " + nuevaReserva);
    }

    private void actualizarReservaHabitacion(HttpServletRequest req) {
        // Obtiene el índice y los datos de la reserva de habitación a actualizar
        int index = Integer.parseInt(req.getParameter("index"));
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        Estado estado = Estado.valueOf(req.getParameter("estado"));
        String fechaReserva = req.getParameter("fechaReserva");
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");

        // Actualiza la reserva por índice
        ReservaHabitacion reservaHabitacion = reservaHabitaciones.get(index);
        reservaHabitacion.setIdHabitacion(idHabitacion);
        reservaHabitacion.setEstado(estado);
        reservaHabitacion.setFechaReserva(fechaReserva);
        reservaHabitacion.setFechaEntrada(fechaEntrada);
        reservaHabitacion.setFechaSalida(fechaSalida);

        System.out.println("Reserva de habitación actualizada: " + reservaHabitacion);
    }

    private void eliminarReservaHabitacion(HttpServletRequest req) {
        // Obtiene el índice de la reserva a eliminar
        int index = Integer.parseInt(req.getParameter("index"));

        // Marca como eliminada
        reservaHabitaciones.get(index).setEliminado(true);
        System.out.println("Reserva de habitación marcada como eliminada: " + reservaHabitaciones.get(index));
    }
}
