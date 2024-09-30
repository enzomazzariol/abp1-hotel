package Service;

import Model.ReservaActividad;
import Utils.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ReservaActividadService {
    private ArrayList<ReservaActividad> reservaActividades;

    public void mostrarReservaActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reservaActividades = new ArrayList<>();
        reservaActividades.add(new ReservaActividad(1, 1, Estado.RESERVADO, "03-03-2004", 1));
        req.setAttribute("reservaActividad", reservaActividades);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/reservaActividad.jsp");
        dispatcher.forward(req, resp);
    }

    public void crearReservaActividad(HttpServletRequest req, HttpServletResponse resp) {
        reservaActividades = new ArrayList<>();

        int id = Integer.parseInt(req.getParameter("id"));
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        String estadoParam = req.getParameter("estado");
        String fechaReserva = req.getParameter("fechaReserva");

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        ReservaActividad reservaActividad = new ReservaActividad(id, idUsuario,  estado, fechaReserva , idActividad);
        reservaActividades.add(reservaActividad);
        System.out.println(reservaActividades);
    }
}
