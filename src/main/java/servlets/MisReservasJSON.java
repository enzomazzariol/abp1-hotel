package servlets;

import com.google.gson.Gson;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import service.ActividadesService;
import service.MisReservasService;

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

@WebServlet("/misReservasJSON")
public class MisReservasJSON extends HttpServlet {

    public MisReservasService misReservasService;

    public MisReservasJSON() {
        misReservasService = new MisReservasService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Permitir CORS para cualquier origen
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        resp.setStatus(HttpServletResponse.SC_OK);

        ArrayList detalleReservaActividad;
        ArrayList detalleReservaHabitacion;
        try {
            detalleReservaActividad = misReservasService.listarReservasActividad(req, resp);
            detalleReservaHabitacion = misReservasService.listarReservasHabitacion(req, resp);
            System.out.println(detalleReservaActividad);
        } catch (ConexionException | ActividadesException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Crear un objeto para almacenar ambas listas
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("reservasActividad", detalleReservaActividad);
        responseMap.put("reservasHabitacion", detalleReservaHabitacion);
        // Convertir el mapa a JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseMap);

        // Configurar el tipo de contenido para la respuesta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Enviar el JSON como respuesta
        resp.getWriter().flush();
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String getAction = req.getReader().readLine();

        // Eliminar cualquier envoltura de comillas (por si las hubiera)
        getAction = getAction.replaceAll("^\"|\"$", "");

        String[] action = getAction.split("-");
        System.out.println("Actividad recibida: " + Arrays.toString(action));

        if (action.length != 0) {

            System.out.println("Acción recibida: " + Arrays.toString(action));

            misReservasService.menuPostMisReservasJSON(action);

            // Responder con un mensaje
            Map<String, String> responseData = new HashMap<>();
            responseData.put("message", "Acción procesada: " + Arrays.toString(action));
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(responseData);

            // Configuramos la respuesta
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonResponse);

        } else {
            // Si no se recibe la acción, enviar error
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> responseData = new HashMap<>();
            responseData.put("error", "No se recibió la acción");

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(responseData);

            resp.getWriter().write(jsonResponse);
        }
    }


}
