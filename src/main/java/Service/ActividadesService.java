package Service;

import DAO.ActividadesDAO;
import Model.Actividad;
import excepciones.ActividadesException;
import excepciones.ConexionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ActividadesService {

    ActividadesDAO actividadesDAO;

    public ActividadesService(){
        this.actividadesDAO = new ActividadesDAO();
    }

    public void fowardActividades(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException, ConexionException, ActividadesException {
        req.setAttribute("actividades", actividadesDAO.listarActividades());
        //foward a la pagina de actividades
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/actividades.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ConexionException, ActividadesException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarActividad(req, resp);
        } else if ("actualizar".equals(action)) {
            actualizarActividad(req, resp);
        } else if ("eliminar".equals(action)) {
            eliminarActividad(req);
        }
    }

    public void agregarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ConexionException, ActividadesException {
        // Obtiene los datos de la nueva actividad
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        String imagenes = req.getParameter("imagenes");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");

        // Crea la nueva instancia de Actividad
        Actividad nuevaActividad = new Actividad(nombre, descripcion, imagenes, precio, cupo, fecha);

        // insertamos la actividad en la BD
        actividadesDAO.insertarActividad(nuevaActividad);
        System.out.println("Actividad insertada correctamente en la base de datos");

    }

    public void actualizarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ConexionException, ActividadesException {
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

        System.out.println("Actividad actualizada correctamente en la base de datos");
    }

    public void eliminarActividad(HttpServletRequest req) throws SQLException, ClassNotFoundException, ConexionException, ActividadesException {
        // Obtenemos el id a eliminar en la BD
        int id = Integer.parseInt(req.getParameter("id"));
        actividadesDAO.eliminarActividad(id);

        System.out.println("Se ha eliminado la actividad con el id: " + id);
    }

}
