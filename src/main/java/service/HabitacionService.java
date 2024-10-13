package service;

import dao.HabitacionesDAO;
import model.Habitacion;
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

public class HabitacionService {
    HabitacionesDAO habitacionesDAO;

    public HabitacionService() {
        this.habitacionesDAO = new HabitacionesDAO();
    }

    public void forwardHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, HabitacionException, ConexionException {
        req.setAttribute("habitaciones", habitacionesDAO.listarHabitaciones());
        System.out.println(habitacionesDAO.listarHabitaciones());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/habitacion.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostHabitacion(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarHabitacion(req);
        } else if ("actualizar".equals(action)) {
            actualizarHabitacion(req);
        } else if ("eliminar".equals(action)) {
            eliminarHabitacion(req);
        }
    }

    public void agregarHabitacion(HttpServletRequest req) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud.
        String tipoHabitacionParam = req.getParameter("tipoHabitacion");
        byte[] imagen = req.getParameter("imagen").getBytes();
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
        byte[] imagen = req.getParameter("imagen").getBytes();
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

    public void eliminarHabitacion(HttpServletRequest req) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina la habitacion con el id.
        habitacionesDAO.actualizarEliminadoHabitacion(id);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Se ha eliminado la habitacion con id " + id);
    }

}
