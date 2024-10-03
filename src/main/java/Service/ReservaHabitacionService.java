package Service;

import DAO.HabitacionesDAO;
import DAO.ReservaHabitacionDAO;
import Model.ReservaHabitacion;
import Utils.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaHabitacionService {
    ReservaHabitacionDAO reservaHabitacionDAO;

    public ReservaHabitacionService() {
        this.reservaHabitacionDAO = new ReservaHabitacionDAO();
    }

    public void mostrarReservaHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reservahabitaciones", reservaHabitacionDAO.listarResevaHabitaciones());
        System.out.println(reservaHabitacionDAO.listarResevaHabitaciones());
        RequestDispatcher dispatcher= req.getRequestDispatcher("/jsp/reservaHabitacion.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostReservaHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
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
    public void agregarReservaHabitacion(HttpServletRequest req) {
        // Obtener los parámetros de la solicitud.
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String estadoParam = req.getParameter("estado");
        String fechaReserva = req.getParameter("fechaReserva");
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");
        boolean eliminado = Boolean.parseBoolean(req.getParameter("eliminado"));

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crea la nueva instancia de ReservaHabitacion
        ReservaHabitacion nuevaReservaHabitacion = new ReservaHabitacion(idUsuario, estado, fechaReserva, idHabitacion, fechaEntrada, fechaSalida);

        // Se agrega la habitacion a la base de datos.
        reservaHabitacionDAO.agregarReservaHabitacion(nuevaReservaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva reserva de habitación: " + nuevaReservaHabitacion);
    }

    public void actualizarReservaHabitacion(HttpServletRequest req) {
        // Obtiene el índice y los datos de la reserva de habitación a actualizar
        int id = Integer.parseInt(req.getParameter("id"));
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String estadoParam = req.getParameter("estado");
        String fechaReserva = req.getParameter("fechaReserva");
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");
        boolean eliminado = Boolean.parseBoolean(req.getParameter("eliminado"));

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crear una nueva instancia de Habitacion con id.
        ReservaHabitacion nuevaReservaHabitacion = new ReservaHabitacion(id, idUsuario, estado, fechaReserva, idHabitacion, fechaEntrada, fechaSalida);

        // Se actualiza la habitacion a la base de datos.
        reservaHabitacionDAO.actualizarReserva(nuevaReservaHabitacion);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Reserva de habitación actualizada: " + nuevaReservaHabitacion);
    }

    public void eliminarReservaHabitacion(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina la habitacion con el id.
        reservaHabitacionDAO.actualizarEliminadoReservaHabitacion(id);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Se ha eliminado la reserva de habitacion con id " + id);
    }

}
