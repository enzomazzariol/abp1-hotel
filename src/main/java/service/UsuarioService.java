package service;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class UsuarioService {
    private UsuariosDAO usuariosDAO;
    private CifradoService cifradoService;
    private AdminService adminService;

    public UsuarioService() {
        this.usuariosDAO = new UsuariosDAO();
        this.cifradoService = new CifradoService();
        this.adminService = new AdminService();
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
            req.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    // Método para cargar el perfil del usuario logueado (desde la sesión)
    public void forwardUsuarioSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/perfil.jsp");
            dispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            req.setAttribute("error", e.getMessage());
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
                actualizarUsuario(req, null);  // Método para actualizar con parámetros HTTP
            } else if ("eliminar".equals(action)) {
                eliminarUsuario(req, resp);
            }

            manejarPerfil(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
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
            // Obtener o generar la clave DES para cifrar
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------UPDATES------------------------------------------------------

    // ACTUALIZAR USUARIO
    public void actualizarUsuario(HttpServletRequest req, String jsonInput) throws Exception {
        if (jsonInput == null) {
            // Si no se pasa JSON, actualizar con parámetros de la solicitud HTTP
            actualizarUsuarioConParametros(req);
        } else {
            // Si se pasa un JSON, actualizar con los datos del JSON
            actualizarUsuarioDesdeJson(jsonInput);
        }
    }

    // Método para actualizar el usuario con parámetros de la solicitud HTTP
    private void actualizarUsuarioConParametros(HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));

        // Obtener el usuario existente desde la base de datos
        Usuario usuarioExistente = usuariosDAO.usuarioById(id);

        if (usuarioExistente == null) {
            throw new Exception("Usuario con ID " + id + " no encontrado.");
        }

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
        String passwordFinal = (nuevaPassword != null && !nuevaPassword.isEmpty()) ? nuevaPassword : usuarioExistente.getPassword();
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

    // Método para actualizar el usuario usando un JSON
    private void actualizarUsuarioDesdeJson(String jsonInput) throws Exception {
        try {
            // Crear una instancia de Gson para deserializar el JSON
            Gson gson = new Gson();

            // Deserializar el JSON en un objeto UsuarioActualizacionDTO
            Usuario usuario = gson.fromJson(jsonInput, Usuario.class);

            // Validar que el ID sea válido
            if (usuario.getId() == 0) {
                throw new Exception("El ID del usuario es obligatorio.");
            }

            // Obtener el usuario existente desde la base de datos
            Usuario usuarioExistente = usuariosDAO.usuarioById(usuario.getId());

            if (usuarioExistente == null) {
                throw new Exception("Usuario con ID " + usuario.getId() + " no encontrado.");
            }

            // Actualizar los valores solo si se proporcionan en el JSON
            String nombreFinal = (usuario.getNombre() != null && !usuario.getNombre().isEmpty())
                    ? usuario.getNombre() : usuarioExistente.getNombre();

            String emailFinal = (usuario.getEmail() != null && !usuario.getEmail().isEmpty())
                    ? usuario.getEmail() : usuarioExistente.getEmail();

            String passwordFinal = (usuario.getPassword() != null && !usuario.getPassword().isEmpty())
                    ? usuario.getPassword() : usuarioExistente.getPassword();

            String imagenFinal = (usuario.getImagen() != null && !usuario.getImagen().isEmpty())
                    ? usuario.getImagen() : usuarioExistente.getImagen();

            Rol rolFinal = (usuario.getRol() != null)
                    ? usuario.getRol()
                    : usuarioExistente.getRol();




            // Cifrar la contraseña si se ha proporcionado una nueva
            String passwordCifrada = passwordFinal;
            if (!passwordFinal.equals(usuarioExistente.getPassword())) {
                SecretKey clave = cifradoService.obtenerClaveDesdeBD();
                passwordCifrada = cifradoService.cifrarDES(passwordFinal, clave);
            }

            // Crear un nuevo objeto Usuario con los valores actualizados
            Usuario usuarioActualizado = new Usuario(
                    usuario.getId(),
                    nombreFinal,
                    emailFinal,
                    passwordCifrada,
                    rolFinal,
                    usuarioExistente.getFechaRegistro(),
                    usuarioExistente.isEliminado(),
                    imagenFinal
            );

            // Llamar al DAO para realizar la actualización en la base de datos
            usuariosDAO.actualizarAllUsuario(usuarioActualizado);

            System.out.println("Usuario actualizado correctamente: " + usuarioActualizado);
        } catch (JsonSyntaxException e) {
            throw new Exception("El JSON proporcionado no tiene el formato correcto.", e);
        } catch (IllegalArgumentException e) {
            throw new Exception("El rol proporcionado no es válido.", e);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }

    // -----------------------------------------DELETES------------------------------------------------------

    // ELIMINAR USUARIO
    public void eliminarUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            usuariosDAO.eliminarUsuario(id);
            resp.sendRedirect(req.getContextPath() + "/admin/usuarios");
        } catch (Exception e) {
            throw new Exception("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    // -----------------------------------------APP------------------------------------------------------

    public void updateUsuarioApp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    
    try {
        // Leer el cuerpo JSON
        BufferedReader reader = req.getReader();
        Usuario usuarioActualizado = new Gson().fromJson(reader, Usuario.class);

        if (usuarioActualizado == null || usuarioActualizado.getId() == 0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos inválidos o ID no proporcionado");
            return;
        }

        // Validar y actualizar usuario en la base de datos
        actualizarUsuarioDesdeJson(new Gson().toJson(usuarioActualizado));
        resp.getWriter().write(new Gson().toJson(usuarioActualizado));
    } catch (Exception e) {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar usuario: " + e.getMessage());
    }
}
    public void getUsuarioApp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       resp.setContentType("application/json");
       resp.setCharacterEncoding("UTF-8");
    
    try {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del usuario es obligatorio");
            return;
        }
        
        int id = Integer.parseInt(idParam);
        Usuario usuario = usuariosDAO.usuarioById(id);

        if (usuario == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
            return;
        }

        // Convertir el usuario a JSON y enviarlo en la respuesta
        String usuarioJson = new Gson().toJson(usuario);
        resp.getWriter().write(usuarioJson);
    } catch (Exception e) {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener usuario: " + e.getMessage());
    }
}


}
