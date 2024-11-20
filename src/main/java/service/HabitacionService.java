package service;

import com.google.gson.Gson;
import dao.HabitacionesDAO;
import dao.ReservaHabitacionDAO;
import excepciones.ReservaHabitacionException;
import model.Habitacion;
import model.ReservaHabitacion;
import utils.Estado;
import utils.TipoHabitacion;
import excepciones.ConexionException;
import excepciones.HabitacionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HabitacionService {
    HabitacionesDAO habitacionesDAO;
    ReservaHabitacionDAO reservaHabitacionDAO;

    public HabitacionService() {
        this.habitacionesDAO = new HabitacionesDAO();
        this.reservaHabitacionDAO = new ReservaHabitacionDAO();
    }

    public void forwardHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("habitaciones", habitacionesDAO.listarHabitaciones());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/habitacion.jsp");
            dispatcher.forward(req, resp);
        } catch(ServletException | IOException | SQLException | HabitacionException | ConexionException e) {
            req.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public ArrayList<Habitacion> listarHabitaciones() throws ConexionException, SQLException, HabitacionException {
        return habitacionesDAO.listarHabitaciones();
    }

    public void menuPostHabitacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("agregar".equals(action)) {
                agregarHabitacion(req);
            } else if ("actualizar".equals(action)) {
                actualizarHabitacion(req);
            } else if ("eliminar".equals(action)) {
                eliminarHabitacion(req, resp);
            } else if ("reservar".equals(action)) {
                agregarReservaHabitacion(req);
            }else {
                // Manejo en caso de acción no reconocida o inválida
                req.setAttribute("error", "Acción no reconocida: " + action);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
                dispatcher.forward(req, resp);
            }
            forwardHabitacion(req, resp);
        } catch(SQLException | ClassNotFoundException | HabitacionException| ConexionException| IOException | ServletException e) {
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        } catch (ReservaHabitacionException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarHabitacion(HttpServletRequest req) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud.
        String tipoHabitacionParam = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estadoParam = req.getParameter("estado");

        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabitacionParam.toUpperCase());
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crear una nueva instancia de Habitacion.
        Habitacion nuevaHabitacion = new Habitacion(tipoHabitacion, imagen, precio, estado);

        // Se agrega la habitacion a la base de datos.
        habitacionesDAO.insertarHabitacion(nuevaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva habitación insertada: " + nuevaHabitacion);
    }

    public void actualizarHabitacion(HttpServletRequest req) throws SQLException, HabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));
        String tipoHabitacionParam = req.getParameter("tipoHabitacion");
        String imagen = req.getParameter("imagen");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String estadoParam = req.getParameter("estado");

        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabitacionParam.toUpperCase());
        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crear una nueva instancia de Habitacion con id.
        Habitacion nuevaHabitacion = new Habitacion(id, tipoHabitacion, imagen, precio, estado);

        // Se actualiza la habitacion a la base de datos.
        habitacionesDAO.actualizarHabitacion(nuevaHabitacion);

        // Imprime por consola la Habitacion actualiza.
        System.out.println("Habitación actualizada: " + nuevaHabitacion);
    }

    public void eliminarHabitacion(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException, IOException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina la habitacion con el id.
        habitacionesDAO.actualizarEliminadoHabitacion(id);
        resp.sendRedirect("admin?success=true");
        // Imprime por consola la Habitacion actualiza.
        System.out.println("Se ha eliminado la habitacion con id " + id);
    }

    public void agregarReservaHabitacion(HttpServletRequest req) throws SQLException, ReservaHabitacionException, ConexionException {
        // Obtener los parámetros de la solicitud.
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String estadoParam = req.getParameter("estado");
        int idHabitacion = Integer.parseInt(req.getParameter("idHabitacion"));
        String fechaEntrada = req.getParameter("fechaEntrada");
        String fechaSalida = req.getParameter("fechaSalida");

        Estado estado = Estado.valueOf(estadoParam.toUpperCase());

        // Crea la nueva instancia de ReservaHabitacion
        ReservaHabitacion nuevaReservaHabitacion = new ReservaHabitacion(idUsuario, estado, idHabitacion, fechaEntrada, fechaSalida);

        // Se agrega la habitacion a la base de datos.
        reservaHabitacionDAO.agregarReservaHabitacion(nuevaReservaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva reserva de habitación: " + nuevaReservaHabitacion);
    }

    // ---------------------------------- App methods -----------------------------------------//

    public void getHabitacionesApp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    // Recibir datos de la reserva del front para enviarlos al DAO
    public void insertReservaHabitacionApp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String getAction = req.getReader().readLine();

        // Eliminar cualquier envoltura de comillas (por si las hubiera)
        getAction = getAction.replaceAll("^\"|\"$", "");

        String[] action = getAction.split("/");

        System.out.println("Habitacion recibida: " + Arrays.toString(action));

        if (action.length != 0) {
            // Procesar la acción recibida
            System.out.println("Acción recibida correctamente");

            //hacer insert a la base de datos (insertar reserva de habitacion)
            try {
                agregarReservaHabitacionApp(action);
            } catch (ReservaHabitacionException | ConexionException | SQLException e) {
                throw new RuntimeException(e);
            }

            // Responder con un mensaje
            Map<String, String> responseData = new HashMap<>();
            responseData.put("message", "Acción procesada: " + Arrays.toString(action));

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(responseData);

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

    public void agregarReservaHabitacionApp(String[] action) throws ReservaHabitacionException, ConexionException, SQLException {
        int idUsuario = Integer.parseInt(action[0]);
        int idHabitacion = Integer.parseInt(action[1]);
        String fechaEntrada = action[2];
        String fechaSalida = action[3];
        Estado estado = Estado.RESERVADO;

        // Crea la nueva instancia de ReservaHabitacion
        ReservaHabitacion nuevaReservaHabitacion = new ReservaHabitacion(idUsuario, estado, idHabitacion, fechaEntrada, fechaSalida);

        // Se agrega la habitacion a la base de datos.
        reservaHabitacionDAO.agregarReservaHabitacion(nuevaReservaHabitacion);

        // Imprime por consola la Habitacion agregada.
        System.out.println("Nueva reserva de habitación: " + nuevaReservaHabitacion);
    }

}
