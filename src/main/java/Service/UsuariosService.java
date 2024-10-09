package Service;

import Model.Usuario;
import Utils.Rol;
import DAO.UsuariosDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosService {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private UsuariosDAO usuariosDAO = new UsuariosDAO();

    // Método para listar usuarios y enviar al JSP correspondiente
    public void listarUsuarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            usuarios = usuariosDAO.listarUsuarios();  // Obtener lista desde la base de datos
            req.setAttribute("listaUsuarios", usuarios);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al listar los usuarios", e);
        }

        // Redirigir a la página JSP de usuarios
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/usuarios.jsp");
        dispatcher.forward(req, resp);
    }


    // Método para manejar acciones POST de agregar, actualizar o eliminar usuarios
    public void menuPostUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarUsuario(req, resp);
        } else if ("actualizar".equals(action)) {
            actualizarUsuario(req);
        } else if ("eliminar".equals(action)) {
            eliminarUsuario(req);
        }
    }

    // Método para agregar un nuevo usuario
    public void agregarUsuario(HttpServletRequest req, HttpServletResponse resp) {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Rol rol = Rol.valueOf(req.getParameter("rol")); // Convertir el rol a partir de la request

        Usuario nuevoUsuario = new Usuario(0, nombre, email, password, rol);

        try {
            usuariosDAO.insertarUsuario(nuevoUsuario);  // Insertar el nuevo usuario en la base de datos
            usuarios.add(nuevoUsuario);  // Agregarlo a la lista local
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }

        System.out.println("Se ha creado: " + nuevoUsuario);
    }

    // Método para actualizar un usuario existente
    private void actualizarUsuario(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Rol rol = Rol.valueOf(req.getParameter("rol"));

        Usuario usuarioActualizado = new Usuario(id, nombre, email, password, rol);

        try {
            usuariosDAO.actualizarUsuario(usuarioActualizado);  // Actualizar el usuario en la base de datos

            // Actualizar en la lista local
            for (Usuario usuario : usuarios) {
                if (usuario.getId() == id) {
                    usuario.setNombre(nombre);
                    usuario.setEmail(email);
                    usuario.setPassword(password);
                    usuario.setRol(rol);
                    break;
                }
            }

            System.out.println("Usuario actualizado: " + usuarioActualizado);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    // Método para eliminar (marcar como eliminado) un usuario
    private void eliminarUsuario(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            usuariosDAO.eliminarUsuario(id);  // Marcar como eliminado en la base de datos

            // Marcar como eliminado en la lista local
            for (Usuario usuario : usuarios) {
                if (usuario.getId() == id) {
                    System.out.println("Usuario marcado como eliminado: " + usuario);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el usuario", e);
        }
    }
}
