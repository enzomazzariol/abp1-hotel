package Service;

import Model.Usuario;
import Utils.Rol;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RegistroService {

    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public void fowardRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Foward a la pagina registro
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registro.jsp");
        dispatcher.forward(req, resp);
    }

    public void registroUsuario(HttpServletRequest req, HttpServletResponse resp){
        // recuperar parametros del formulario registro

        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String rolParam = req.getParameter("rol");

        // Convertir el rol recibido del formulario a enum
        Rol rol = Rol.valueOf(rolParam.toUpperCase());

        Usuario nuevoUsuario = new Usuario(id, nombre, password, email, rol);
        listaUsuarios.add(nuevoUsuario);
        System.out.println(nuevoUsuario);
    }
}
