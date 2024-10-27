package service;

import dao.ActividadesDAO;
import dao.HabitacionesDAO;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import excepciones.HabitacionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HomeService {
    HabitacionesDAO habitacionesDAO;
    ActividadesDAO actividadesDAO;

    public HomeService() {
        habitacionesDAO = new HabitacionesDAO();
        actividadesDAO = new ActividadesDAO();
    }

    public void forwardHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("habitaciones", habitacionesDAO.listarHabitaciones());
            req.setAttribute("actividades", actividadesDAO.listarActividades());
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } catch(ConexionException | SQLException| HabitacionException | ServletException | IOException | ActividadesException | ClassNotFoundException e){
            req.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
