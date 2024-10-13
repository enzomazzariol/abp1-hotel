package Service;

import DAO.UsuariosDAO;
import Model.Usuario;
import Utils.Rol;
import excepciones.UsuariosException;
import excepciones.ConexionException;


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

    public void forwardUsuarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, UsuariosException, ConexionException, ServletException {
        req.setAttribute("usuarios", usuariosDAO.listarUsuarios());
        //foward a la pagina de actividades
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/usuarios.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostUsuario(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarUsuario(req);
        } else if ("actualizar".equals(action)) {
            actualizarUsuario(req);
        } else if ("eliminar".equals(action)) {
            eliminarUsuario(req);
        }
    }

    public void agregarUsuario(HttpServletRequest req) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password"); // Obtener la contraseña
        String rolParam = req.getParameter("rol");

        Rol rol = Rol.valueOf(rolParam.toUpperCase());

        // Crear nuevo usuario con ID (puedes generarlo en la base de datos)
        Usuario nuevoUsuario = new Usuario(nombre, email, password, rol);
        usuariosDAO.insertarUsuario(nuevoUsuario);
        System.out.println("Nuevo usuario insertado: " + nuevoUsuario);
    }

    public void actualizarUsuario(HttpServletRequest req) throws SQLException, UsuariosException, ConexionException {
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

    public void eliminarUsuario(HttpServletRequest req) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException{
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina el usuario con el id.
        usuariosDAO.eliminarUsuario(id);

        // Imprime por consola la eliminación del usuario.
        System.out.println("Se ha eliminado el usuario con id " + id);
    }
}
