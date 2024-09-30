package Servlets;

import DAO.ActividadesDAO;
import Service.ActividadesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/actividades")
public class ActividadesServlet extends HttpServlet {

    private ActividadesService actividadesService = new ActividadesService();
    private ActividadesDAO actividadesDAO = new ActividadesDAO();
    
    ActividadesService as = new ActividadesService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       as.listarActividades(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         actividadesService.agregarActividad(req, resp);

        try {
            actividadesDAO.obtenerActividades();
            as.menuPostActividad(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
