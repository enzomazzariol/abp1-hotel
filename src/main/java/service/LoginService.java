package service;

import com.google.gson.Gson;
import dao.LoginDAO;
import dao.UsuariosDAO;
import model.Usuario;
import excepciones.ConexionException;
import excepciones.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginService {

    LoginDAO loginDAO;
    UsuariosDAO usuariosDAO;
    UsuarioService usuarioService;

    public LoginService() {
        this.loginDAO = new LoginDAO();
        this.usuariosDAO = new UsuariosDAO();
        this.usuarioService = new UsuarioService();
    }

    // GET
    public void forwardLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    // POST
    public void comprobarLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre");
            String password = req.getParameter("password");

            // Lista para acumular errores en el front
            ArrayList<String> errores = new ArrayList<>();

            // Validar campos vacios
            if (nombre == null || nombre.trim().isEmpty()) {
                errores.add("El campo 'nombre' es obligatorio.");
            }
            if (password == null || password.trim().isEmpty()) {
                errores.add("El campo 'contraseña' es obligatorio.");
            }

            // Si hay errores, reenviarlos al formulario de login
            if (!errores.isEmpty()) {
                req.setAttribute("errores", errores);
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                return;
            }

            // Logica para comprobar el nombre y la contraseña.
            Usuario usuarioActual = this.loginDAO.checklogin(nombre, password);
            if (usuarioActual == null) {
                errores.add("Usuario o contraseña incorrecto");
            } else if (usuarioActual.isEliminado()) {
                errores.add("El usuario ha sido eliminado");
            }

            if (!errores.isEmpty()) {
                req.setAttribute("errores", errores);
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            } else {
                // Si no hay errores, el usuario es válido
                Usuario usuario = usuariosDAO.usuarioById(usuarioActual.getId());
                // req.setAttribute("usuario", usuario);
                HttpSession session = req.getSession();
                session.setAttribute("usuario", usuario);
                usuarioService.forwardUsuarioSesion(req, resp);

            }
        } catch(SQLException | ServletException | IOException | LoginException | ConexionException e) {
            // Enviar a la página de error en caso de excepción
            req.setAttribute("error",  e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void loginAPP(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Leer el cuerpo de la solicitud
            String getAction = req.getReader().readLine();

            // Eliminar cualquier envoltura de comillas (por si las hubiera)
            getAction = getAction.replaceAll("^\"|\"$", "");

            // Separar el contenido en un array
            String[] action = getAction.split("-");
            System.out.println("Usuario recibido: " + Arrays.toString(action));

            String nombre = action[0];
            String password = action[1];
            System.out.println(nombre + " " + password);

            // Logica para comprobar el nombre y la contraseña
            Usuario usuarioActual = this.loginDAO.checklogin(nombre, password);

            if (usuarioActual != null) {
                // Si las credenciales son correctas
                // Crear la respuesta con el mensaje
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("message", "Acción procesada: " + Arrays.toString(action));

                // Convertir la respuesta en JSON
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(responseData);

                // Configurar las cabeceras de la respuesta
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonResponse);

                // Crear la sesión del usuario si es válido
                Usuario usuario = usuariosDAO.usuarioById(usuarioActual.getId());
                HttpSession session = req.getSession();
                session.setAttribute("usuario", usuario);

            } else {
                // Si no se reciben las credenciales correctamente
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Código 401 para error de autenticación
                Map<String, String> responseData = new HashMap<>();
                responseData.put("error", "Usuario o contraseña incorrectos");

                Gson gson = new Gson();
                String jsonResponse = gson.toJson(responseData);
                resp.getWriter().write(jsonResponse);
            }

        } catch (SQLException | IOException | LoginException | ConexionException e) {
            // Si ocurre un error, devolver un error 500
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código 500 para error del servidor
            Map<String, String> responseData = new HashMap<>();
            responseData.put("error", "Error en el servidor: " + e.getMessage());

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(responseData);
            resp.getWriter().write(jsonResponse);
        }
    }

    public void obtenerUsuarioSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);  // Obtiene la sesión si existe, o null si no

        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");  // Obtiene el usuario de la sesión
            if (usuario != null) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                Gson gson = new Gson();
                String jsonResponse = gson.toJson(usuario);
                resp.getWriter().write(jsonResponse);
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 Unauthorized
                resp.getWriter().write("{\"error\": \"Usuario no autenticado\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 Unauthorized
            resp.getWriter().write("{\"error\": \"No hay sesión activa\"}");
        }
    }

    public int obtenerUsuarioSesionById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);  // Obtiene la sesión si existe, o null si no
        int id = 0;
        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");  // Obtiene el usuario de la sesión
            if (usuario != null) {
                // Devolver id de usuario
                id = usuario.getId();
                return id;
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 Unauthorized
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 Unauthorized
        }
        return id;
    }
}
