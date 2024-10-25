package service;

import dao.UsuariosDAO;
import excepciones.ConexionException;
import excepciones.UsuariosException;
import model.Usuario;
import utils.Rol;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistroService {

    private UsuariosDAO usuariosDAO;

    public RegistroService(){
        usuariosDAO = new UsuariosDAO();
    }

    public void fowardRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Foward a la pagina registro
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registro.jsp");
        dispatcher.forward(req, resp);
    }

    public void registroUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           // recuperar parametros del formulario registro
           String nombre = req.getParameter("nombre");
           String email = req.getParameter("email");
           String password = req.getParameter("password");
           // String rolParam = req.getParameter("rol");
           // Convertir el rol recibido del formulario a enum
           //Rol rol = Rol.valueOf(rolParam.toUpperCase());

           // Lista para acumular errores en el front
           ArrayList<String> errores = new ArrayList<>();

           // Validar campos vacíos
           if (nombre == null || nombre.trim().isEmpty()) {
               errores.add("El campo 'nombre' es obligatorio.");
           }
           if (email == null || email.trim().isEmpty()) {
               errores.add("El campo 'email' es obligatorio.");
           }
           if (password == null || password.trim().isEmpty()) {
               errores.add("El campo 'contraseña' es obligatorio.");
           } else if (password.length() < 6) {
               errores.add("La contraseña debe tener al menos 6 caracteres.");
           }

           if (!errores.isEmpty()) {
               req.setAttribute("errores", errores);
               req.setAttribute("nombre", nombre);
               req.setAttribute("email", email);
               RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registro.jsp");
               dispatcher.forward(req, resp);
               return;
           }

           try {
               Usuario nuevoUsuario = new Usuario(nombre, email, password);
               usuariosDAO.insertarUsuario(nuevoUsuario);

               // Redirigir al home después de registrarse
               req.setAttribute("nombreUsuario", nuevoUsuario.getNombre());
               RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
               dispatcher.forward(req, resp);

           } catch (SQLException | ClassNotFoundException | UsuariosException | ConexionException e) {
               req.setAttribute("error",  e.getMessage());
               RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
               dispatcher.forward(req, resp);
           }
       } catch(ServletException | IOException e){
           // Enviar a la página de error en caso de excepción
           req.setAttribute("error",  e.getMessage());
           RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error.jsp");
           dispatcher.forward(req, resp);
       }
    }
}
