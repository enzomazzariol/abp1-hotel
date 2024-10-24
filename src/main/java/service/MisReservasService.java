package service;

import dao.MisReservasDAO;
import excepciones.ConexionException;
import excepciones.ReservaActividadesException;
import excepciones.ReservaHabitacionException;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class MisReservasService {
    public MisReservasDAO misReservasDAO;
    public ReservaActividadService reservaActividadService;
    public ReservaHabitacionService reservaHabitacionService;


    public MisReservasService() {
        misReservasDAO = new MisReservasDAO();
        reservaActividadService = new ReservaActividadService();
        reservaHabitacionService = new ReservaHabitacionService();

    }

    public void fowardReservaActividad(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false); // false: No crea una nueva sesión si no existe
            if (session != null) {
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                if (usuario != null) {
                    int idUsuario = usuario.getId();

                    // Obtener las reservas del usuario
                    req.setAttribute("reservaActividadById", misReservasDAO.reservaActividadesById(idUsuario));
                    req.setAttribute("reservaHabitacionById", misReservasDAO.reservaHabitacionesById(idUsuario));

                    // Redirigir a la JSP
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/misReservas.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    resp.sendRedirect("jsp/error.jsp");
                }
            }

        /*
        // Obtener la sesión actual
        HttpSession session = req.getSession(false);

        if (session != null) {
            // Obtener el ID del usuario desde la sesión

            Integer idUsuario = (Integer) session.getAttribute("idUsuario");


            if (idUsuario != null) {
                req.setAttribute("reservaActividadById",  misReservasDAO.reservaActividadesById(idUsuario));
                req.setAttribute("reservasHabitacionById", misReservasDAO.reservaHabitacionesById(idUsuario));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/misReservas.jsp");
                dispatcher.forward(req, resp);
            } else {
                // Redirigir al login si no hay ID de usuario en la sesión
                resp.sendRedirect("/jsp/login.jsp");
            }
        } else {
            // Redirigir al login si no hay sesión activa
            resp.sendRedirect("/jsp/login.jsp");
        }
        */
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void menuPostMisReservas(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");

            if ("actualizarActividades".equals(action)) {
                reservaActividadService.actualizarReservaActividad(req);
            } else if ("eliminarActividades".equals(action)) {
                reservaActividadService.eliminarReservaActividad(req);
            } else if ("actualizarHabitaciones".equals(action)) {
                reservaHabitacionService.actualizarEstadoReservaHabitacion(req);
            } else if ("eliminarHabitaciones".equals(action)) {
                reservaHabitacionService.eliminarReservaHabitacion(req);
            }
            fowardReservaActividad(req, resp);
        } catch (SQLException | IOException | ConexionException | ReservaActividadesException | ClassNotFoundException |
                 ReservaHabitacionException | ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
