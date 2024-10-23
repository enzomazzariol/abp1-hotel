package service;

import dao.AdminDAO;
import dao.UsuariosDAO;
import model.Usuario;
import utils.Rol;
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
    AdminService adminService;

    public UsuarioService() {
        this.usuariosDAO = new UsuariosDAO();
        adminService = new AdminService();
    }

    public void forwardUsuarios(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = 1;
            Usuario usuario = usuariosDAO.usuarioById(id);
            req.setAttribute("usuario", usuario);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/perfil.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException | IOException | ServletException | ConexionException e) {
            throw new RuntimeException(e);
        }
    }

    public void menuPostUsuario(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");

            if ("agregar".equals(action)) {
                agregarUsuario(req);
            } else if ("actualizar".equals(action)) {
                actualizarUsuario(req);
            } else if ("eliminar".equals(action)) {
                eliminarUsuario(req, resp);
            }

            forwardUsuarios(req, resp);
        } catch (SQLException | ClassNotFoundException | UsuariosException | ConexionException e) {
            throw new RuntimeException(e);
        }

    }

    // -----------------------------------------INSERTS------------------------------------------------------

    // AGREGAR USUARIO
    public void agregarUsuario(HttpServletRequest req) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password"); // Obtener la contraseña
        //String rolParam = req.getParameter("rol");

       // Rol rol = Rol.valueOf(rolParam.toUpperCase());

        // Crear nuevo usuario con ID (puedes generarlo en la base de datos)
        Usuario nuevoUsuario = new Usuario(nombre, email, password);
        usuariosDAO.insertarUsuario(nuevoUsuario);
        System.out.println("Nuevo usuario insertado: " + nuevoUsuario);
    }

    // -----------------------------------------UPDATES------------------------------------------------------

    // ACTUALIZAR USUARIO
    public void actualizarUsuario(HttpServletRequest req) throws SQLException, UsuariosException, ConexionException {
        int id = Integer.parseInt(req.getParameter("id"));

        // Obtener el usuario existente desde la base de datos
        Usuario usuarioExistente = usuariosDAO.usuarioById(id);

        // Obtener parámetros desde el request
        String nuevoNombre = req.getParameter("nombre");
        String nuevoEmail = req.getParameter("email");
        String nuevaPassword = req.getParameter("password");
        String nuevaImagen = req.getParameter("imagen");
        String rolParam = req.getParameter("rol");

        // Si el rol no es nulo, lo convertimos
        Rol rol = null;
        if (rolParam != null && !rolParam.isEmpty()) {
            rol = Rol.valueOf(rolParam.toUpperCase());
        } else {
            rol = usuarioExistente.getRol(); // Mantener rol existente si no se proporciona
        }

        // Actualizar campos solo si llegan nuevos valores
        String nombreFinal = (nuevoNombre != null && !nuevoNombre.isEmpty()) ? nuevoNombre : usuarioExistente.getNombre();
        String emailFinal = (nuevoEmail != null && !nuevoEmail.isEmpty()) ? nuevoEmail : usuarioExistente.getEmail();
        String passwordFinal = (nuevaPassword != null && !nuevaPassword.isEmpty()) ? nuevaPassword : usuarioExistente.getPassword();
        String imagenFinal = (nuevaImagen != null && !nuevaImagen.isEmpty()) ? nuevaImagen : usuarioExistente.getImagen();

        // Crear un nuevo objeto Usuario con los valores actualizados
        Usuario usuarioActualizado = new Usuario(id, nombreFinal, emailFinal, passwordFinal, rol, usuarioExistente.getFechaRegistro(), usuarioExistente.isEliminado(), imagenFinal);

        // Llamar al DAO para realizar la actualización en la base de datos
        usuariosDAO.actualizarAllUsuario(usuarioActualizado);

        System.out.println("Usuario actualizado: " + usuarioActualizado);
    }


    // -----------------------------------------DELETES------------------------------------------------------

    // ELIMINAR USUARIO
    public void eliminarUsuario(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException{
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina el usuario con el id.
        usuariosDAO.eliminarUsuario(id);
        adminService.fowardAdmin(req, resp);
        // Imprime por consola la eliminación del usuario.
        System.out.println("Se ha eliminado el usuario con id " + id);
    }
}
