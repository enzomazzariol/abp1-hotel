package Servlets;

import Model.Actividad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/actividades")
public class ActividadesServlet extends HttpServlet {

    // Lista que almacena las actividades
    private ArrayList<Actividad> listaActividades = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listaActividades = new ArrayList<>();
        listaActividades.add(new Actividad(1, "Boxeo", "Myke Tyson", "50px", 50, 15,
                        "2024-03-03"));

            // Foward a la pagina actividades
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/actividades.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre_actividad = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        String imagenes = req.getParameter("imagenes");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha_actividad = req.getParameter("fecha_actividad");

        Actividad nuevaActividad = new Actividad(id, nombre_actividad, descripcion, imagenes, precio, cupo, fecha_actividad);

        listaActividades.add(nuevaActividad);
        System.out.println(nuevaActividad);
        System.out.println("Actividad agregada con exito");

        doGet(req, resp);
    }
}
