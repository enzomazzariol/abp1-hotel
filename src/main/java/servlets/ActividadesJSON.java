package servlets;

import com.google.gson.Gson;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Permitir CORS para cualquier origen
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        resp.setStatus(HttpServletResponse.SC_OK);

        ActividadesService actividadesService = new ActividadesService();

        // Creamos un mapa con los datos que queremos devolver
        ArrayList actividades;
        try {
            actividades = actividadesService.listarActividades();
        } catch (ConexionException | ActividadesException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(actividades);
        // Convertimos el mapa a JSON usando Gson
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(actividades);

        // Configuramos el tipo de contenido para la respuesta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Enviamos el JSON como respuesta
        resp.getWriter().write(jsonResponse);
    }
}
