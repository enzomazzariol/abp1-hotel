package servlets;

import service.HabitacionService;
import excepciones.ConexionException;
import excepciones.HabitacionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/habitacion")
public class HabitacionServlet extends HttpServlet {

    HabitacionService hs;

    public HabitacionServlet() {
        this.hs = new HabitacionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            hs.forwardHabitacion(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            hs.menuPostHabitacion(req, resp);
    }

}

