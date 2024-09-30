package Service;

import Model.Habitacion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class HabitacionService {
    private ArrayList<Habitacion> habitaciones = new ArrayList<>();

    public void mostrarHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        habitaciones = new ArrayList<>();
        habitaciones.add(new Model.Habitacion(1,"a", "6px", 200, "ocupada", false));
        req.setAttribute("habitaciones", habitaciones);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/habitacion.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void agregarHabitacion(HttpServletRequest req) {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));
        String tipoHabitacion = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estado = req.getParameter("estado");
        boolean eliminado = Boolean.parseBoolean(req.getParameter("eliminado"));

        // Crear una nueva instancia de Habitacion
        Habitacion nuevaHabitacion = new Habitacion(id, tipoHabitacion, imagen, precio, estado, eliminado);

        // Agregar la nueva habitación a la lista de habitaciones
        habitaciones.add(nuevaHabitacion);

        System.out.println("Nueva habitación insertada: " + nuevaHabitacion);
    }

    private void actualizarHabitacion(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String tipoHabitacion = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estado = req.getParameter("estado");

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                habitacion.setTipoHabitacion(tipoHabitacion);
                habitacion.setImagen(imagen);
                habitacion.setPrecio(precio);
                habitacion.setEstado(estado);
                System.out.println("Habitación actualizada: " + habitacion);
                break;
            }
        }
    }

    private void eliminarHabitacion(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                habitacion.setEliminado(true);  // Marcamos como eliminada
                System.out.println("Habitación marcada como eliminada: " + habitacion);
                break;
            }
        }
    }
}
