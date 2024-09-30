package Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminService {

    public void fowardAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Foward a la pagina de admin
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin.jsp");
        dispatcher.forward(req, resp);
    }

    public void actualizarUsuario(HttpServletRequest req, HttpServletResponse resp){
        // Recuperamos el id del usuario para hacer un update
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("id usuario: " + id);
    }
}
