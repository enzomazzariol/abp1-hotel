package Service;

import DAO.UsuariosDAO;
import Model.Usuario;
import Utils.Rol;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UsuarioService {
    UsuariosDAO usuariosDAO;

    public UsuarioService() {
        this.usuariosDAO = new UsuariosDAO();
    }

    public void forwardUsuarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Intenta listar los usuarios y establecerlo como atributo de la solicitud
            req.setAttribute("usuarios", usuariosDAO.listarUsuarios());
            System.out.println(usuariosDAO.listarUsuarios());
        } catch (ClassNotFoundException e) {
            // Manejar ClassNotFoundException
            e.printStackTrace();
            req.setAttribute("errorMessage", "No se pudo encontrar la clase necesaria para acceder a la base de datos.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp"); // Cambia a tu página de error
            dispatcher.forward(req, resp);
            return;
        } catch (SQLException e) {
            // Manejar SQLException
            e.printStackTrace();
            req.setAttribute("errorMessage", "Se produjo un error al acceder a la base de datos.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp"); // Cambia a tu página de error
            dispatcher.forward(req, resp);
            return;
        }

        // Si no hay excepciones, continúa a la página de usuario
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/usuario.jsp");
        dispatcher.forward(req, resp);
    }


    public void menuPostUsuario(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException{
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarUsuario(req);
        } else if ("actualizar".equals(action)) {
            actualizarUsuario(req);
        } else if ("eliminar".equals(action)) {
            eliminarUsuario(req);
        }
    }

    public void agregarUsuario(HttpServletRequest req) throws SQLException {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password"); // Obtener la contraseña
        String rolParam = req.getParameter("rol");

        Rol rol = Rol.valueOf(rolParam.toUpperCase());

        // Crear nuevo usuario con ID (puedes generarlo en la base de datos)
        Usuario nuevoUsuario = new Usuario(0, nombre, email, password, rol); // Usar ID temporal, ajusta según tu lógica
        usuariosDAO.insertarUsuario(nuevoUsuario);
        System.out.println("Nuevo usuario insertado: " + nuevoUsuario);
    }

    public void actualizarUsuario(HttpServletRequest req) throws SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password"); // Obtener la nueva contraseña
        String rolParam = req.getParameter("rol");

        Rol rol = Rol.valueOf(rolParam.toUpperCase());
        Usuario usuarioActualizado = new Usuario(id, nombre, email, password, rol); // Usar la contraseña nueva
        usuariosDAO.actualizarUsuario(usuarioActualizado);
        System.out.println("Usuario actualizado: " + usuarioActualizado);
    }

    public void eliminarUsuario(HttpServletRequest req) throws SQLException, ClassNotFoundException  {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina el usuario con el id.
        usuariosDAO.eliminarUsuario(id);

        // Imprime por consola la eliminación del usuario.
        System.out.println("Se ha eliminado el usuario con id " + id);
    }
}