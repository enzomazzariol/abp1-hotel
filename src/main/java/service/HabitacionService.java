package service;

import dao.HabitacionesDAO;
import dao.ReservaHabitacionDAO;
import excepciones.ReservaHabitacionException;
import model.Habitacion;
import model.ReservaHabitacion;
import utils.Estado;
import utils.TipoHabitacion;
import excepciones.ConexionException;
import excepciones.HabitacionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitacionService {
    HabitacionesDAO habitacionesDAO;
    ReservaHabitacionDAO reservaHabitacionDAO;

    public HabitacionService() {
        this.habitacionesDAO = new HabitacionesDAO();
        this.reservaHabitacionDAO = new ReservaHabitacionDAO();
    }

    public void forwardHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("habitaciones", habitacionesDAO.listarHabitaciones());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/habitacion.jsp");
            dispatcher.forward(req, resp);
        } catch(ServletException | IOException | SQLException | HabitacionException | ConexionException e) {
            req.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public ArrayList<Habitacion> listarHabitaciones() throws ConexionException, SQLException, HabitacionException {
        return habitacionesDAO.listarHabitaciones();
    }

    public void menuPostHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("agregar".equals(action)) {
                agregarHabitacion(req);
            } else if ("actualizar".equals(action)) {
                actualizarHabitacion(req);
            } else if ("eliminar".equals(action)) {
                eliminarHabitacion(req, resp);
            } else if ("reservar".equals(action)) {
                agregarReservaHabitacion(req);
            }else {
                // Manejo en caso de acción no reconocida o inválida
                req.setAttribute("error", "Acción no reconocida: " + action);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
                dispatcher.forward(req, resp);
            }
            forwardHabitacion(req, resp);
        } catch(SQLException | ClassNotFoundException | HabitacionException| ConexionException| IOException | ServletException e) {
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        } catch (ReservaHabitacionException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarHabitacion(HttpServletRequest req) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud.
        String tipoHabitacionParam = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estadoParam = req.getParameter("estado");

        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabitacionParam.toUpperCase());
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crear una nueva instancia de Habitacion.
        Habitacion nuevaHabitacion = new Habitacion(tipoHabitacion, imagen, precio, estado);

        // Se agrega la habitacion a la base de datos.
        habitacionesDAO.insertarHabitacion(nuevaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva habitación insertada: " + nuevaHabitacion);
    }

    public void actualizarHabitacion(HttpServletRequest req) throws SQLException, HabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));
        String tipoHabitacionParam = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estadoParam = req.getParameter("estado");

        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabitacionParam.toUpperCase());
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crear una nueva instancia de Habitacion con id.
        Habitacion nuevaHabitacion = new Habitacion(id, tipoHabitacion, imagen, precio, estado);

        // Se actualiza la habitacion a la base de datos.
        habitacionesDAO.actualizarHabitacion(nuevaHabitacion);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Habitación actualizada: " + nuevaHabitacion);
    }

    public void eliminarHabitacion(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException, IOException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina la habitacion con el id.
        habitacionesDAO.actualizarEliminadoHabitacion(id);
        resp.sendRedirect("admin?success=true");
        // Imprime por consola la Habitacion actualiza.
        System.out.println("Se ha eliminado la habitacion con id " + id);
    }

    public void agregarReservaHabitacion(HttpServletRequest req) throws SQLException, ReservaHabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud.
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String estadoParam = req.getParameter("estado");
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crea la nueva instancia de ReservaHabitacion
        ReservaHabitacion nuevaReservaHabitacion = new ReservaHabitacion(idUsuario, estado, idHabitacion, fechaEntrada, fechaSalida);

        // Se agrega la habitacion a la base de datos.
        reservaHabitacionDAO.agregarReservaHabitacion(nuevaReservaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva reserva de habitación: " + nuevaReservaHabitacion);
    }

}
