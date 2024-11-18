package servlets;

import com.google.gson.Gson;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import excepciones.HabitacionException;
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

@WebServlet("/habitacionJSON")
public class HabitacionJSON extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-type, Authorization");
        HabitacionService habitacionService = new HabitacionService();

        // Creamos un mapa con los datos que queremos devolver
        ArrayList habitaciones;
        try {
            habitaciones = habitacionService.listarHabitaciones();
        } catch (ConexionException | SQLException |
                 HabitacionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(habitaciones);
        // Convertimos el mapa a JSON usando Gson
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(habitaciones);

        // Configuramos el tipo de contenido para la respuesta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Enviamos el JSON como respuesta
        resp.getWriter().write(jsonResponse);
    }
}
