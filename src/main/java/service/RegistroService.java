package service;

import dao.LoginDAO;
import dao.UsuariosDAO;
import excepciones.ConexionException;
import excepciones.LoginException;
import excepciones.UsuariosException;
import model.Usuario;
import utils.Rol;

import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class RegistroService {

    private UsuariosDAO usuariosDAO;
    private LoginDAO loginDAO;
    CifradoService cifradoService;

    public RegistroService(){
        usuariosDAO = new UsuariosDAO();
        loginDAO = new LoginDAO();
        cifradoService = new CifradoService();
    }

    public void fowardRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Foward a la pagina registro
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registro.jsp");
        dispatcher.forward(req, resp);
    }

    public void registroUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lista para acumular errores en el front
        ArrayList<String> errores = new ArrayList<>();

        try {
           // recuperar parametros del formulario registro
           String nombre = req.getParameter("nombre");
           String email = req.getParameter("email");
           String password = req.getParameter("password");

           // Obtener o generar la clave DES para cifrar
           SecretKey clave = cifradoService.obtenerClaveDesdeBD();
           if (clave == null) {
               clave = cifradoService.generarClaveDES();
               System.out.println("Clave generada y almacenada en BD: " + Base64.getEncoder().encodeToString(clave.getEncoded()));
           }

           // Cifrar la contraseña
           String passwordCifrada = cifradoService.cifrarDES(password, clave);

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
               Usuario nuevoUsuario = new Usuario(nombre, email, passwordCifrada);
               usuariosDAO.insertarUsuario(nuevoUsuario);
            // Corregir los objetos usuarios de abajo (dan un nullPointer) al recuperar el id, arreglarlo para que la pantalla registro funcione correctamente
               Usuario usuarioId = loginDAO.checklogin(nuevoUsuario.getNombre(), nuevoUsuario.getPassword());
               Usuario usuario = usuariosDAO.usuarioById(usuarioId.getId());
               HttpSession session = req.getSession();
               session.setAttribute("usuario", usuario);
               req.getRequestDispatcher("/jsp/perfil.jsp").forward(req, resp);

           } catch (SQLException | ClassNotFoundException e) {
               errores.add("Hubo un problema con el registro. Inténtalo más tarde.");
           } catch (UsuariosException e) {
               errores.add("El usuario ya existe o el correo ya está en uso.");
           }
       } catch (Exception e) {
           errores.add("Ocurrió un error inesperado: " + e.getMessage());
       }

        // Si hay errores, volver al formulario con los mensajes
        if (!errores.isEmpty()) {
            req.setAttribute("errores", errores);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registro.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
