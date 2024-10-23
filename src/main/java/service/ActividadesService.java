package service;

import dao.ActividadesDAO;
import model.Actividad;
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

    public void menuPostActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ConexionException, ActividadesException, ServletException, IOException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarActividad(req, resp);
        } else if ("actualizar".equals(action)) {
            actualizarActividad(req, resp);
        } else if ("eliminar".equals(action)) {
            eliminarActividad(req, resp);
        }
    }

    public void agregarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ConexionException, ActividadesException, IOException, ServletException {
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");
        String imagen = req.getParameter("imagen");

        Actividad nuevaActividad = new Actividad(nombre, descripcion, imagen, precio, cupo, fecha);
        actividadesDAO.insertarActividad(nuevaActividad);
        resp.sendRedirect("admin?success=true");
        System.out.println("Actividad insertada correctamente en la base de datos");
    }

    public void actualizarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ConexionException, ActividadesException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");
        String imagen = req.getParameter("imagenes");

        Actividad actividad = new Actividad(id, nombre, descripcion, imagen, precio, cupo, fecha);
        actividadesDAO.actualizarActividad(actividad);
        resp.sendRedirect("admin?success=true");
        System.out.println("Actividad actualizada correctamente en la base de datos");
    }

    public void eliminarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ConexionException, ActividadesException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        actividadesDAO.eliminarActividad(id);
        resp.sendRedirect("admin?success=true");
        System.out.println("Se ha eliminado la actividad con el id: " + id);
    }
}
