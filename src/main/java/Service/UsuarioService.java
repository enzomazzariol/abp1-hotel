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

    public void forwardUsuarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.setAttribute("usuarios", usuariosDAO.listarUsuarios());
        System.out.println(usuariosDAO.listarUsuarios());
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

    public void agregarUsuario(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        // Obtener los parámetros de la solicitud.
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String rolParam = req.getParameter("rol");

        Rol rol = Rol.valueOf(rolParam.toUpperCase());

        // Crear una nueva instancia de Usuario.
        Usuario nuevoUsuario = new Usuario(nombre, email, rol);

        // Se agrega el usuario a la base de datos.
        usuariosDAO.insertarUsuario(nuevoUsuario);

        // Imprime por consola el Usuario agregado.
        System.out.println("Nuevo usuario insertado: " + nuevoUsuario);
    }

    public void actualizarUsuario(HttpServletRequest req) throws SQLException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String rolParam = req.getParameter("rol");

        Rol rol = Rol.valueOf(rolParam.toUpperCase());

        // Crear una nueva instancia de Usuario con id.
        Usuario nuevoUsuario = new Usuario(id, nombre, email, rol);

        // Se actualiza el usuario en la base de datos.
        usuariosDAO.actualizarUsuario(nuevoUsuario);

        // Imprime por consola el Usuario actualizado.
        System.out.println("Usuario actualizado: " + nuevoUsuario);
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