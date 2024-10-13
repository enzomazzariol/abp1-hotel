package servlets;

import service.ActividadesService;
import excepciones.ActividadesException;
import excepciones.ConexionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/actividades")
public class ActividadesServlet extends HttpServlet {

    private ActividadesService actividadesService;

    public ActividadesServlet(){
        actividadesService = new ActividadesService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            actividadesService.fowardActividades(req, resp);
        } catch (SQLException | ClassNotFoundException | ConexionException | ActividadesException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            actividadesService.menuPostActividad(req, resp);
        } catch (SQLException | ClassNotFoundException | ConexionException | ActividadesException e) {
            throw new RuntimeException(e);
        }

    }
}
