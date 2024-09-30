package Service;

import Model.Habitacion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class HabitacionService {
    private ArrayList<Habitacion> habitaciones;

    public void mostrarHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        habitaciones = new ArrayList<>();
        habitaciones.add(new Model.Habitacion(1,"a", "6px", 200, "ocupada"));
        req.setAttribute("habitaciones", habitaciones);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/habitacion.jsp");
        dispatcher.forward(req, resp);
    }

    public void crearHabitacion(HttpServletRequest req, HttpServletResponse resp){
        habitaciones = new ArrayList<>();

        int id = Integer.parseInt(req.getParameter("id"));
        String tipoHabitacion = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estado = req.getParameter("estado");

        Model.Habitacion habitacion = new Model.Habitacion(id, tipoHabitacion, imagen, precio, estado);

        habitaciones.add(habitacion);
        System.out.println(habitaciones);
    }
}
