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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        habitaciones = new ArrayList<>();
        habitaciones.add(new Model.Habitacion("a", "6px", 200, "ocupada"));
        req.setAttribute("habitaciones", habitaciones);
        getServletContext().getRequestDispatcher("/jsp/habitacion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        habitaciones = new ArrayList<>();

        String tipoHabitacion = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estado = req.getParameter("estado");

        Model.Habitacion habitacion = new Model.Habitacion(tipoHabitacion, imagen, precio, estado);

        habitaciones.add(habitacion);
        System.out.println(habitaciones);

    }
}
