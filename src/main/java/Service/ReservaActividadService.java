package Service;

import DAO.ActividadesDAO;
import DAO.ReservaActividadesDAO;
import Model.Actividad;
import Model.ReservaActividad;
import Utils.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaActividadService {
    ReservaActividadesDAO reservaActividadesDAO;

    public ReservaActividadService(){
        this.reservaActividadesDAO = new ReservaActividadesDAO();
    }

    public void fowardReservaActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        req.setAttribute("reserva actividades",  reservaActividadesDAO.listarReservaActividades());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/reservaActividad.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostReservaActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarReservaActividad(req);
        } else if ("actualizar".equals(action)) {
            actualizarReservaActividad(req);
        } else if ("eliminar".equals(action)) {
            eliminarReservaActividad(req);
        }
    }

    public void agregarReservaActividad(HttpServletRequest req) throws ServletException, IOException, SQLException {
        // Obtiene los datos de la nueva reserva de actividad
        int idUsuario = Integer.parseInt(req.getParameter("id_usuario"));
        int idActividad = Integer.parseInt(req.getParameter("id_actividad"));
        String estadoParam = req.getParameter("estado");
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crea la nueva instancia de Reserva Actividad
        ReservaActividad reservaActividad = new ReservaActividad(idUsuario, idActividad, estado);

        // insertamos la actividad en la BD
        reservaActividadesDAO.insertarActividad(reservaActividad);
        System.out.println("Se ha creado la reserva de la actividad: " + reservaActividad.getId());
    }

    public void actualizarReservaActividad(HttpServletRequest req) throws SQLException {
        // Obtiene el índice y los datos de la reserva de actividad a actualizar
        int id = Integer.parseInt(req.getParameter("id"));
        String estadoParam = req.getParameter("estado");
        String fechaReserva = req.getParameter("fechaReserva");
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        ReservaActividad reservaActividad = new ReservaActividad(id, estado, fechaReserva);

        reservaActividadesDAO.actualizarReservaActividad(reservaActividad);

        System.out.println("Reserva de la actividad " + id + "actualizada correctamente en la base de datos");
    }

    private void eliminarReservaActividad(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        // Obtenemos el id a eliminar en la BD
        int id = Integer.parseInt(req.getParameter("id"));
        reservaActividadesDAO.eliminarReservaActividad(id);

        System.out.println("Se ha eliminado la actividad con el id: " + id);
    }
}
