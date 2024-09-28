package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/habitacion")
public class HabitacionServlet extends HttpServlet {

    private ArrayList<Model.Habitacion> habitaciones;

    @Override
    public void init() throws ServletException {
        habitaciones = new ArrayList<>();
        // Inicialización de datos para pruebas
        habitaciones.add(new Model.Habitacion(1, "a", "6px", 200, "ocupada", false));  // La clase Model.Habitacion ahora tiene un campo 'eliminado'
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("habitaciones", habitaciones);
        getServletContext().getRequestDispatcher("/jsp/habitacion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("actualizar".equalsIgnoreCase(action)) {
            actualizarHabitacion(req);
        } else if ("eliminar".equalsIgnoreCase(action)) {
            eliminarHabitacion(req);
        }

        // Redirigir al GET para mostrar la lista actualizada
        doGet(req, resp);
    }

    private void actualizarHabitacion(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String tipoHabitacion = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estado = req.getParameter("estado");

        for (Model.Habitacion habitacion : habitaciones) {
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

        for (Model.Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                habitacion.setEliminado(true);  // Marcamos como eliminada
                System.out.println("Habitación marcada como eliminada: " + habitacion);
                break;
            }
        }
    }
}

