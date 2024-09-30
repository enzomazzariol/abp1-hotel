package Service;

import Model.Actividad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ActividadesService {

    private ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();

    public void listarActividades(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Consultar actividades de la base de datos

        //foward a la pagna de actividades
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/actividades.jsp");
        dispatcher.forward(req, resp);
    }

    public void agregarActividad(HttpServletRequest req, HttpServletResponse res){
        int id = Integer.parseInt(req.getParameter("id"));
        String nombreActividad = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        String imagenes = req.getParameter("imagenes");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fechaActividad = req.getParameter("fecha_actividad");

        Actividad nuevaActividad = new Actividad(id, nombreActividad, descripcion, imagenes, precio, cupo, fechaActividad);
        listaActividades.add(nuevaActividad);
        System.out.println(nuevaActividad);
    }
}
