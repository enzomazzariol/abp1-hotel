package Service;

import DAO.HabitacionesDAO;
import Model.Habitacion;
import Utils.Estado;
import Utils.TipoHabitacion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitacionService {
    private ArrayList<Habitacion> habitaciones = new ArrayList<>();

    public void mostrarHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        habitaciones = new ArrayList<>();
        habitaciones.add(new Model.Habitacion(1,TipoHabitacion.DOBLE, "6px", 200, Estado.DISPONIBLE));
        req.setAttribute("habitaciones", habitaciones);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/habitacion.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarHabitacion(req);
        } else if ("actualizar".equals(action)) {
            actualizarHabitacion(req);
        } else if ("eliminar".equals(action)) {
            eliminarHabitacion(req);
        }

        // Redirigir al GET para mostrar la lista actualizada
        mostrarHabitacion(req, resp);
    }

    private void agregarHabitacion(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        // Obtener los parámetros de la solicitud.
        String tipoHabitacionParam = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estadoParam = req.getParameter("estado");
        boolean eliminado = Boolean.parseBoolean(req.getParameter("eliminado"));

        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabitacionParam.toUpperCase());
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crear una nueva instancia de Habitacion.
        Habitacion nuevaHabitacion = new Habitacion(tipoHabitacion, imagen, precio, estado);

        // Se agrega la habitacion a la base de datos.
        HabitacionesDAO.agregarHabitacion(nuevaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva habitación insertada: " + nuevaHabitacion);
    }

    private void actualizarHabitacion(HttpServletRequest req) {
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
        HabitacionesDAO.actualizarHabitacion(nuevaHabitacion);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Habitación actualizada: " + nuevaHabitacion);
    }

    private void eliminarHabitacion(HttpServletRequest req) {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina la habitacion con el id.
        HabitacionesDAO.eliminarHabitacion(id);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Se ha eliminado la habitacion con id " + id);
    }
}
