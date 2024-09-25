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

@WebServlet("/reservaActividad")
public class ReservaActividadServlet extends HttpServlet {

    private ArrayList<ReservaActividad> reservaActividades;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        reservaActividades = new ArrayList<>();

        reservaActividades.add(new ReservaActividad(1, 1, Estado.RESERVADO, "03-03-2004", 1));
        req.setAttribute("reservaActividad", reservaActividades);
        getServletContext().getRequestDispatcher("/jsp/reservaActividad.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
