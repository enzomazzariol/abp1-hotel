package Service;

import DAO.ActividadesDAO;
import Model.Actividad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActividadesService {

    private ArrayList<Actividad> actividades = new ArrayList<>();

    public void fowardActividades(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //foward a la pagna de actividades
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/actividades.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostActividad(HttpServletRequest req, HttpServletResponse resp) throws  SQLException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarActividad(req, resp);
        } else if ("actualizar".equals(action)) {
            actualizarActividad(req, resp);
        } else if ("eliminar".equals(action)) {
            eliminarActividad(req);
        }
    }

    public void listarActividades(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        // Consultar actividades de la base de datos
        ActividadesDAO actividadesDAO = new ActividadesDAO();
        actividadesDAO.listarActividades();
    }


    public void agregarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        ActividadesDAO actividadesDAO = new ActividadesDAO();

        // Obtiene los datos de la nueva actividad
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        String imagenes = req.getParameter("imagenes");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");

        // Crea la nueva instancia de Actividad
        Actividad nuevaActividad = new Actividad(id, nombre, descripcion, imagenes, precio, cupo, fecha);

        // insertamos la actividad en la BD
        actividadesDAO.insertarActividad(nuevaActividad);
        System.out.println("Actividad: " + nuevaActividad + " insertada correctamente en la base de datos");

    }

    public void actualizarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        ActividadesDAO actividadesDAO = new ActividadesDAO();

        // Obtiene el ID y los datos de la actividad a actualizar
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        String imagenes = req.getParameter("imagenes");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");

        Actividad actividad = new Actividad(id, nombre, descripcion, imagenes, precio, cupo, fecha);

        // Actualizamos la actividad en la Base de datos
        actividadesDAO.actualizarActividad(actividad);

        System.out.println("Actividad: " + actividad + " actualizada correctamente en la base de datos SERVICE");
    }

    public void eliminarActividad(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));

        for (Actividad actividad : actividades) {
            if (actividad.getId() == id) {
                actividad.setEliminado(true);  // Marcamos como eliminada
                System.out.println("Actividad marcada como eliminada: " + actividad);
                break;
            }
        }
    }

}
