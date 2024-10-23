package servlets;

import excepciones.ActividadesException;
import excepciones.ConexionException;
import excepciones.HabitacionException;
import service.HomeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    HomeService homeService ;

    public HomeServlet() {
        homeService = new HomeService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            homeService.forwardHome(req, resp);
        } catch (ConexionException | SQLException | HabitacionException | ActividadesException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
