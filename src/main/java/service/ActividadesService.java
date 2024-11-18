package service;

import dao.ActividadesDAO;
import dao.ReservaActividadesDAO;
import excepciones.ReservaActividadesException;
import model.Actividad;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import model.ReservaActividad;
import utils.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActividadesService {

    ActividadesDAO actividadesDAO;
    ReservaActividadesDAO reservaActividadesDAO;

    public ActividadesService(){
        this.actividadesDAO = new ActividadesDAO();
        this.reservaActividadesDAO = new ReservaActividadesDAO();
    }

    public void fowardActividades(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("actividades", actividadesDAO.listarActividades());
            //foward a la pagina de actividades
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/actividades.jsp");
            dispatcher.forward(req, resp);
        } catch(SQLException | ClassNotFoundException | ConexionException | ActividadesException | ServletException | IOException e){
            req.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public ArrayList<Actividad> listarActividades() throws ConexionException, SQLException, ActividadesException, ClassNotFoundException {
        return actividadesDAO.listarActividades();
    }

    public void menuPostActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("agregar".equals(action)) {
                agregarActividad(req, resp);
            } else if ("actualizar".equals(action)) {
                actualizarActividad(req, resp);
            } else if ("eliminar".equals(action)) {
                eliminarActividad(req, resp);
            } else if ("reservar".equals(action)) {
                agregarReservaActividad(req);
            } else {
                // Manejo en caso de acción no reconocida o inválida
                req.setAttribute("error", "Acción no reconocida: " + action);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
                dispatcher.forward(req, resp);
            }

            fowardActividades(req, resp);
        } catch(SQLException |ClassNotFoundException | ConexionException| ActividadesException| ServletException | IOException e){
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        } catch (ReservaActividadesException e) {
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
            throw new RuntimeException(e);
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

    public void agregarReservaActividad(HttpServletRequest req) throws ServletException, IOException, SQLException, ConexionException, ReservaActividadesException, ActividadesException {
        // Obtiene los datos de la nueva reserva de actividad
        int idUsuario = Integer.parseInt(req.getParameter("id_usuario"));
        int idActividad = Integer.parseInt(req.getParameter("id_actividad"));
        String estadoParam = req.getParameter("estado");

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crea la nueva instancia de Reserva Actividad
        ReservaActividad reservaActividad = new ReservaActividad(idUsuario, idActividad, estado);

        // insertamos la actividad en la BD
        reservaActividadesDAO.insertarActividad(reservaActividad);
        System.out.println("Se ha creado la reserva de la actividad: " + reservaActividad.getIdActividad());

        // Reducir el cupo de la actividad
        Actividad actividad = actividadesDAO.obtenerActividadPorId(idActividad); // Implementa este método para obtener la actividad
        actividad.reducirCupo(); // Reducimos el cupo en la instancia
        actividadesDAO.actualizarCupo(idActividad, actividad.getCupo()); // Actualizamos el cupo en la base de datos
    }
}
