package servlets;

import com.google.gson.Gson;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import excepciones.HabitacionException;
import excepciones.ReservaHabitacionException;
import service.ActividadesService;
import service.HabitacionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/habitacionJSON")
public class HabitacionJSON extends HttpServlet {

    HabitacionService habitacionService;

    public HabitacionJSON() {
        habitacionService = new HabitacionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        habitacionService.getHabitacionesApp(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        habitacionService.insertReservaHabitacionApp(req, resp);
    }
}
