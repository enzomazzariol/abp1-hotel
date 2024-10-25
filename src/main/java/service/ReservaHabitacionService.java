package service;

import dao.ReservaHabitacionDAO;
import model.ReservaHabitacion;
import utils.Estado;
import excepciones.ConexionException;
import excepciones.ReservaHabitacionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class ReservaHabitacionService {
    ReservaHabitacionDAO reservaHabitacionDAO;

    public ReservaHabitacionService() {
        this.reservaHabitacionDAO = new ReservaHabitacionDAO();
    }

    public void forwardReservaHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("reservahabitaciones", reservaHabitacionDAO.listarResevaHabitaciones());
            RequestDispatcher dispatcher= req.getRequestDispatcher("/jsp/reservaHabitacion.jsp");
            dispatcher.forward(req, resp);
        } catch(ServletException | IOException| SQLException| ReservaHabitacionException| ConexionException e){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void menuPostReservaHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("agregar".equals(action)) {
                agregarReservaHabitacion(req);
            } else if ("actualizar".equals(action)) {
                actualizarEstadoReservaHabitacion(req);
            } else if ("eliminar".equals(action)) {
                eliminarReservaHabitacion(req);
            }   else {
                // Manejo en caso de acción no reconocida o inválida
                req.setAttribute("error", "Acción no reconocida: " + action);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
                dispatcher.forward(req, resp);
            }
        } catch(ServletException | IOException| SQLException| ClassNotFoundException| ReservaHabitacionException| ConexionException e){
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }
    public void agregarReservaHabitacion(HttpServletRequest req) throws SQLException, ReservaHabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud.
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String estadoParam = req.getParameter("estado");
        String fechaReserva = req.getParameter("fechaReserva");
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crea la nueva instancia de ReservaHabitacion
        ReservaHabitacion nuevaReservaHabitacion = new ReservaHabitacion(idUsuario, estado, fechaReserva, idHabitacion, fechaEntrada, fechaSalida);

        // Se agrega la habitacion a la base de datos.
        reservaHabitacionDAO.agregarReservaHabitacion(nuevaReservaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva reserva de habitación: " + nuevaReservaHabitacion);
    }

    public void actualizarReservaHabitacion(HttpServletRequest req) throws SQLException, ReservaHabitacionException, ConexionException {
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

    public void actualizarEstadoReservaHabitacion(HttpServletRequest req) throws SQLException, ReservaHabitacionException, ConexionException, ServletException, IOException {
        // Obtiene el índice y los datos de la reserva de habitación a actualizar
        int id = Integer.parseInt(req.getParameter("id"));
        /*String estadoParam = req.getParameter("estado"); Para el Postman*/
        String estadoParam = "completado";

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crear una nueva instancia de Habitacion con id.
        ReservaHabitacion nuevaReservaHabitacion = new ReservaHabitacion(id, estado);

        // Se actualiza la habitacion a la base de datos.
        reservaHabitacionDAO.actualizarEstadoReservaHabitacion(nuevaReservaHabitacion);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Reserva de habitación actualizada: " + nuevaReservaHabitacion);

    }

    public void eliminarReservaHabitacion(HttpServletRequest req) throws SQLException, ClassNotFoundException, ReservaHabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina la habitacion con el id.
        reservaHabitacionDAO.actualizarEliminadoReservaHabitacion(id);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Se ha eliminado la reserva de habitacion con id " + id);
    }

}
