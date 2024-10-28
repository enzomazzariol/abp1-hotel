package service;

import dao.ActividadesDAO;
import dao.AdminDAO;
import dao.HabitacionesDAO;
import dao.UsuariosDAO;
import excepciones.ActividadesException;
import excepciones.ConexionException;
import excepciones.HabitacionException;
import excepciones.UsuariosException;
import model.Actividad;
import model.Habitacion;
import model.ReservaGeneral;
import model.Usuario;
import utils.Estado;
import utils.TipoHabitacion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminService {

    private AdminDAO adminDAO;
    private UsuariosDAO usuariosDAO;
    private ActividadesDAO actividadesDAO;
    private HabitacionesDAO habitacionesDAO;

    public AdminService(){
        adminDAO = new AdminDAO();
        usuariosDAO = new UsuariosDAO();
        actividadesDAO = new ActividadesDAO();
        habitacionesDAO = new HabitacionesDAO();
    }

    public void menuPostAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("actualizarUsuarioRol".equals(action)) {
                actualizarUsuarioRol(req, resp);
            } else if ("insertarUsuarioAdmin".equals(action)) {
                insertarUsuario(req, resp);
            } else if ("insertarHabitacionAdmin".equals(action)) {
                insertarHabitacion(req, resp);
            } else if ("actualizarHabitacionAdmin".equals(action)) {
                actualizarHabitacion(req, resp);
            } else if ("eliminarHabitacionAdmin".equals(action)){
                eliminarHabitacion(req, resp);
            } else if ("insertarActividadAdmin".equals(action)){
                insertarActividad(req, resp);
            } else if ("actualizarActividadAdmin".equals(action)){
                actualizarActividad(req, resp);
            } else if ("eliminarActividadAdmin".equals(action)){
                eliminarActividad(req, resp);
            } else {
                // Manejo en caso de acción no reconocida o inválida
                req.setAttribute("error", "Acción no reconocida: " + action);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (ConexionException | SQLException | UsuariosException | ClassNotFoundException | IOException | HabitacionException | ActividadesException e){
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error", "Error al procesar la acción: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void fowardAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // mandar listas de usuarios, habitaciones, actividades y reservas
        try {
            req.setAttribute("usuarios", usuariosDAO.listarUsuarios());
            req.setAttribute("reservas", adminDAO.getAllReservas());
            req.setAttribute("actividades", actividadesDAO.listarActividades());
            req.setAttribute("habitaciones", habitacionesDAO.listarHabitaciones());
            //Foward a la pagina de admin
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/admin.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException | ConexionException | SQLException |UsuariosException | ActividadesException| ClassNotFoundException | HabitacionException e){
            req.setAttribute("error", "Error al procesar la acción: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
            throw new RuntimeException(e);
        }
    }

    public void insertarUsuario(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException, IOException {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Usuario nuevoUsuario = new Usuario(nombre, email, password);
        usuariosDAO.insertarUsuario(nuevoUsuario);
        resp.sendRedirect("admin?success=true");
    }

    public void actualizarUsuarioRol(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Recuperamos el id del usuario para hacer un update
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("id usuario: " + id);
            adminDAO.actualizarRolUsuario(id);
            resp.sendRedirect("admin?success=true");
        } catch (ConexionException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public void insertarHabitacion(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException, IOException, HabitacionException {
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
        resp.sendRedirect("admin?success=true");
    }

    public void actualizarHabitacion(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String estadoParam = req.getParameter("estado");
            Estado estado = Estado.valueOf(estadoParam.toUpperCase());
            int precio = Integer.parseInt(req.getParameter("precio"));
            adminDAO.actualizarEstadoHabitacion(estado, precio, id);
            resp.sendRedirect("admin?success=true");
        } catch (ConexionException | SQLException | IOException e) {
            e.printStackTrace();
        }
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

    public void insertarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException, IOException, HabitacionException, ActividadesException {
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");
        String imagen = req.getParameter("imagen");

        Actividad nuevaActividad = new Actividad(nombre, descripcion, imagen, precio, cupo, fecha);
        actividadesDAO.insertarActividad(nuevaActividad);
        resp.sendRedirect("admin?success=true");
    }

    public void actualizarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ConexionException, ActividadesException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");
        String imagen = req.getParameter("imagenes");

        Actividad actividad = new Actividad(id, nombre, descripcion, imagen, precio, cupo, fecha);
        actividadesDAO.actualizarActividad(actividad);
        resp.sendRedirect("admin?success=true");
        System.out.println("Actividad actualizada correctamente en la base de datos");
    }

    public void eliminarActividad(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ConexionException, ActividadesException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        actividadesDAO.eliminarActividad(id);
        resp.sendRedirect("admin?success=true");
        System.out.println("Se ha eliminado la actividad con el id: " + id);
    }
}
