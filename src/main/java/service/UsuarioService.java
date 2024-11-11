package service;

import dao.AdminDAO;
import dao.UsuariosDAO;
import model.Usuario;
import utils.Rol;
import excepciones.UsuariosException;
import excepciones.ConexionException;


import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

public class UsuarioService {
    UsuariosDAO usuariosDAO;
    AdminService adminService;
    CifradoService cifradoService;

    public UsuarioService() {
        this.usuariosDAO = new UsuariosDAO();
        adminService = new AdminService();
        cifradoService = new CifradoService();
    }

    // Método principal para manejar el acceso al perfil
    public void manejarPerfil(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtenemos el parámetro "id" de la solicitud
            String userIdParam = req.getParameter("id");

            // Si el parámetro "id" es nulo, accedemos al perfil del usuario logueado (desde la sesión)
            if (userIdParam == null) {
                forwardUsuarioSesion(req, resp);
            } else {
                // Si se pasa un id de usuario, cargamos el perfil de ese usuario
                int userId = Integer.parseInt(userIdParam);
                forwardUsuarioById(req, resp, userId);
            }
        } catch (IOException | ServletException | SQLException | ConexionException e) {
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    // Método para cargar el perfil del usuario logueado (desde la sesión)
    public void forwardUsuarioSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /*int id = 1;
            Usuario usuario = usuariosDAO.usuarioById(id);
            req.setAttribute("usuario", usuario);*/
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/perfil.jsp");
            dispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }

    }

    // Método para cargar el perfil de otro usuario (desde el admin)
    public void forwardUsuarioById(HttpServletRequest req, HttpServletResponse resp, int userId) throws ServletException, IOException, SQLException, ConexionException {
        Usuario usuario = usuariosDAO.usuarioById(userId);
        if (usuario == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
            return;
        }
        req.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/perfil.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("agregar".equals(action)) {
                agregarUsuario(req);
            } else if ("actualizar".equals(action)) {
                actualizarUsuario(req);
            } else if ("eliminar".equals(action)) {
                eliminarUsuario(req, resp);
            }

            manejarPerfil(req, resp);
        } catch (Exception e) {
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }

    }

    // -----------------------------------------INSERTS------------------------------------------------------

    // AGREGAR USUARIO
    public void agregarUsuario(HttpServletRequest req) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            //Obtener o generar la clave DES para cifrar
            SecretKey clave = cifradoService.obtenerClaveDesdeBD();
            if (clave == null) {
                clave = cifradoService.generarClaveDES();
                System.out.println("Clave generada y almacenada en BD: " + Base64.getEncoder().encodeToString(clave.getEncoded()));
            }

            // Cifrar la contraseña
            String passwordCifrada = cifradoService.cifrarDES(password, clave);

            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario(nombre, email, passwordCifrada);
            usuariosDAO.insertarUsuario(nuevoUsuario);
            System.out.println("Nuevo usuario insertado: " + nuevoUsuario);
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    // -----------------------------------------UPDATES------------------------------------------------------

    // ACTUALIZAR USUARIO
    public void actualizarUsuario(HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));

        // Obtener el usuario existente desde la base de datos
        Usuario usuarioExistente = usuariosDAO.usuarioById(id);

        // Obtener parámetros desde el request
        String nuevoNombre = req.getParameter("nombre");
        String nuevoEmail = req.getParameter("email");
        String nuevaPassword = req.getParameter("password");
        String nuevaImagen = req.getParameter("imagen");
        String rolParam = req.getParameter("rol");

        // Convertir el rol si se proporciona
        Rol rol = (rolParam != null && !rolParam.isEmpty()) ? Rol.valueOf(rolParam.toUpperCase()) : usuarioExistente.getRol();

        // Actualizar campos solo si llegan nuevos valores
        String nombreFinal = (nuevoNombre != null && !nuevoNombre.isEmpty()) ? nuevoNombre : usuarioExistente.getNombre();
        String passwordFinal = (nuevaPassword != null && !nuevaPassword.isEmpty() ? nuevaPassword : usuarioExistente.getPassword());
        String emailFinal = (nuevoEmail != null && !nuevoEmail.isEmpty()) ? nuevoEmail : usuarioExistente.getEmail();
        String imagenFinal = (nuevaImagen != null && !nuevaImagen.isEmpty()) ? nuevaImagen : usuarioExistente.getImagen();

        // Obtener la clave DES para cifrar
        SecretKey clave = cifradoService.obtenerClaveDesdeBD();
        // Cifrar la contraseña
        String passwordCifrada = cifradoService.cifrarDES(passwordFinal, clave);

        // Crear un nuevo objeto Usuario con los valores actualizados, incluyendo la contraseña cifrada
        Usuario usuarioActualizado = new Usuario(
                id,
                nombreFinal,
                emailFinal,
                passwordCifrada,  // Contraseña cifrada
                rol,
                usuarioExistente.getFechaRegistro(),
                usuarioExistente.isEliminado(),
                imagenFinal
        );

        // Llamar al DAO para realizar la actualización en la base de datos
        usuariosDAO.actualizarAllUsuario(usuarioActualizado);

        System.out.println("Usuario actualizado: " + usuarioActualizado);
    }

    // -----------------------------------------DELETES------------------------------------------------------

    // ELIMINAR USUARIO
    public void eliminarUsuario(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException, ServletException, IOException {
        // Obtener los parámetros de la solicitud
        int id = Integer.parseInt(req.getParameter("id"));

        // Se elimina el usuario con el id.
        usuariosDAO.eliminarUsuario(id);
        adminService.fowardAdmin(req, resp);
        // Imprime por consola la eliminación del usuario.
        System.out.println("Se ha eliminado el usuario con id " + id);
    }
}
