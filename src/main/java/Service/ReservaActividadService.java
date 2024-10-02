package Service;

import Model.Actividad;
import Model.ReservaActividad;
import Utils.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ReservaActividadService {
    private ArrayList<ReservaActividad> reservaActividades = new ArrayList<>();

    public void mostrarReservaActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reservaActividades = new ArrayList<>();
        reservaActividades.add(new ReservaActividad(1, 1, Estado.DISPONIBLE, "03-03-2004", 1));
        req.setAttribute("reservaActividad", reservaActividades);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/reservaActividad.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostReservaActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarReservaActividad(req);
        } else if ("actualizar".equals(action)) {
            actualizarReservaActividad(req);
        } else if ("eliminar".equals(action)) {
            eliminarReservaActividad(req);
        }
    }

    public void agregarReservaActividad(HttpServletRequest req) throws ServletException, IOException {
        // Obtiene los datos de la nueva reserva de actividad
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        Estado estado = Estado.valueOf(req.getParameter("estado"));
        String fechaReserva = req.getParameter("fechaReserva");
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        boolean eliminado = Boolean.parseBoolean(req.getParameter("eliminado"));

        // Crea la nueva instancia de ReservaActividad
        ReservaActividad nuevaReserva = new ReservaActividad(idUsuario, idActividad, estado, fechaReserva, idActividad);

        // Agrega la nueva reserva a la lista
        reservaActividades.add(nuevaReserva);

        System.out.println("Se ha creado la reserva de actividad: " + nuevaReserva);
    }
    private void actualizarReservaActividad(HttpServletRequest req) {
        // Obtiene el índice y los datos de la reserva de actividad a actualizar
        int id = Integer.parseInt(req.getParameter("id"));
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String estadoParam = req.getParameter("estado");
        String fechaReserva = req.getParameter("fechaReserva");
        boolean eliminado = Boolean.parseBoolean(req.getParameter("eliminado"));
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Busca y actualiza la reserva por índice
        for (ReservaActividad reservaActividad: reservaActividades) {
            if (reservaActividad.getId() == id){
                reservaActividad.setIdUsuario(idUsuario);
                reservaActividad.setEstado(estado);
                reservaActividad.setFechaReserva(fechaReserva);
                reservaActividad.setEliminado(eliminado);
                reservaActividad.setIdActividad(idActividad);
                System.out.println("Reserva de actividad actualizada: " + reservaActividad);
                break;
            }
        }

    }

    private void eliminarReservaActividad(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));

        for (ReservaActividad reservaActividad: reservaActividades) {
            if (reservaActividad.getId() == id) {
                reservaActividad.setEliminado(true);  // Marcamos como eliminada
                System.out.println("Actividad marcada como eliminada: " + reservaActividad);
                break;
            }
        }
    }
}
