package Servlets;

import Model.ReservaActividad;
import Utils.Estado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/reservaActividad")
public class ReservaActividadServlet extends HttpServlet {

    private List<ReservaActividad> reservasActividades = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reservasActividades", reservasActividades);
        getServletContext().getRequestDispatcher("/jsp/reservaActividad.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        Estado estado = Estado.valueOf(req.getParameter("estado"));
        String fechaReserva = req.getParameter("fechaReserva");
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));

        if ("actualizar".equals(action)) {
            int index = Integer.parseInt(req.getParameter("index"));
            ReservaActividad reservaActividad = reservasActividades.get(index);
            reservaActividad.setIdActividad(idActividad);
            reservaActividad.setEstado(estado);
            reservaActividad.setFechaReserva(fechaReserva);
        } else if ("eliminar".equals(action)) {
            int index = Integer.parseInt(req.getParameter("index"));
            reservasActividades.get(index).setEliminado(true); // Marcar como eliminado
        } else {
            // Crear nueva reserva
            ReservaActividad nuevaReserva = new ReservaActividad(idUsuario, idActividad, estado, fechaReserva);
            reservasActividades.add(nuevaReserva);
        }

        doGet(req, resp);
    }
}

