package service;

import dao.ReservaActividadesDAO;

import model.ReservaActividad;
import utils.Estado;
import excepciones.ConexionException;
import excepciones.ReservaActividadesException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;


public class ReservaActividadService {
    ReservaActividadesDAO reservaActividadesDAO;

    public ReservaActividadService(){
        this.reservaActividadesDAO = new ReservaActividadesDAO();
    }

    public void fowardReservaActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("reserva actividades",  reservaActividadesDAO.listarReservaActividades());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/reservaActividad.jsp");
            dispatcher.forward(req, resp);
        } catch(ServletException | IOException | SQLException | ClassNotFoundException| ConexionException| ReservaActividadesException e){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void menuPostReservaActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("agregar".equals(action)) {
                agregarReservaActividad(req);
            } else if ("actualizar".equals(action)) {
                actualizarReservaActividad(req);
            } else if ("eliminar".equals(action)) {
                eliminarReservaActividad(req);
            }   else {
                // Manejo en caso de acción no reconocida o inválida
                req.setAttribute("error", "Acción no reconocida: " + action);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
                dispatcher.forward(req, resp);
            }
        } catch(ServletException | IOException |SQLException| ClassNotFoundException| ConexionException | ReservaActividadesException e){
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void agregarReservaActividad(HttpServletRequest req) throws ServletException, IOException, SQLException, ConexionException, ReservaActividadesException {
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


    }

    public void actualizarReservaActividadJSON(int id) throws SQLException, ConexionException, ReservaActividadesException {
        /* String estadoParam = req.getParameter("estado"); Para el Postman*/
        String estadoParam = "completado";
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        ReservaActividad reservaActividad = new ReservaActividad(id, estado);

        reservaActividadesDAO.actualizarReservaActividad(reservaActividad);

        System.out.println("Reserva de la actividad " + id + " actualizada correctamente en la base de datos");
    }

    public void actualizarReservaActividad(HttpServletRequest req) throws SQLException, ConexionException, ReservaActividadesException {
        // Obtiene el índice y los datos de la reserva de actividad a actualizar
        int id = Integer.parseInt(req.getParameter("id"));
        /* String estadoParam = req.getParameter("estado"); Para el Postman*/
        String estadoParam = "completado";
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        ReservaActividad reservaActividad = new ReservaActividad(id, estado);

        reservaActividadesDAO.actualizarReservaActividad(reservaActividad);

        System.out.println("Reserva de la actividad " + id + " actualizada correctamente en la base de datos");
    }

    public void eliminarReservaActividad(HttpServletRequest req) throws SQLException, ClassNotFoundException, ConexionException, ReservaActividadesException {
        // Obtenemos el id a eliminar en la BD
        int id = Integer.parseInt(req.getParameter("id"));
        reservaActividadesDAO.eliminarReservaActividad(id);

        System.out.println("Se ha eliminado la actividad con el id: " + id);
    }

    public void eliminarReservaActividadJSON(int id) throws SQLException, ClassNotFoundException, ConexionException, ReservaActividadesException {
        reservaActividadesDAO.eliminarReservaActividad(id);

        System.out.println("Se ha eliminado la actividad con el id: " + id);
    }
}
