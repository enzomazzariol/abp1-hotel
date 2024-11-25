package servlets;

import com.google.gson.Gson;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import excepciones.ReservaActividadesException;
import service.ActividadesService;

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

@WebServlet("/actividadesJSON")
public class ActividadesJSON extends HttpServlet {

    ActividadesService actividadesService;

    public ActividadesJSON() {
        actividadesService = new ActividadesService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actividadesService.getActividadesApp(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actividadesService.insertReservaActividadApp(req, resp);
    }
}
