package Servlets;

import Model.Actividad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/actividades")
public class ActividadesServlet extends HttpServlet {

    private List<Actividad> actividades = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Pasa la lista de actividades al JSP
        req.setAttribute("actividades", actividades);
        getServletContext().getRequestDispatcher("/jsp/actividades.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");  // Verifica si la acción es "actualizar" o "eliminar"
        int id = Integer.parseInt(req.getParameter("id"));  // ID de la actividad en la lista

        if ("actualizar".equals(action)) {
            // Actualiza los datos de la actividad
            String nombre = req.getParameter("nombre_actividad");
            String descripcion = req.getParameter("descripcion");
            String imagenes = req.getParameter("imagenes");
            double precio = Double.parseDouble(req.getParameter("precio"));
            int cupo = Integer.parseInt(req.getParameter("cupo"));
            String fecha = req.getParameter("fecha_actividad");

            // Busca y actualiza la actividad por ID
            Actividad actividad = actividades.get(id);
            actividad.setNombre_actividad(nombre);
            actividad.setDescripcion(descripcion);
            actividad.setImagenes(imagenes);
            actividad.setPrecio(precio);
            actividad.setCupo(cupo);
            actividad.setFecha_actividad(fecha);

        } else if ("eliminar".equals(action)) {
            // Marca la actividad como eliminada
            Actividad actividad = actividades.get(id);
            actividad.setEliminado(true);
        }

        // Redirige nuevamente al método GET para mostrar la lista actualizada
        doGet(req, resp);
    }
}
