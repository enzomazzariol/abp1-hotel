package Service;

import Model.Actividad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ActividadesService {

    private ArrayList<Actividad> actividades = new ArrayList<>();

    public void listarActividades(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Consultar actividades de la base de datos

        //foward a la pagna de actividades
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/actividades.jsp");
        dispatcher.forward(req, resp);
    }

    public void menuPostActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("agregar".equals(action)) {
            agregarActividad(req);
        } else if ("actualizar".equals(action)) {
            actualizarActividad(req);
        } else if ("eliminar".equals(action)) {
            eliminarActividad(req);
        }
    }

    private void agregarActividad(HttpServletRequest req) {
        // Obtiene los datos de la nueva actividad
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        String imagenes = req.getParameter("imagenes");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");

        // Crea la nueva instancia de Actividad
        Actividad nuevaActividad = new Actividad(id, nombre, descripcion, imagenes, precio, cupo, fecha);

        // Agrega la nueva actividad a la lista
        actividades.add(nuevaActividad);

        System.out.println("Se ha creado: " +actividades);
    }

    private void actualizarActividad(HttpServletRequest req) {
        // Obtiene el ID y los datos de la actividad a actualizar
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre_actividad");
        String descripcion = req.getParameter("descripcion");
        String imagenes = req.getParameter("imagenes");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int cupo = Integer.parseInt(req.getParameter("cupo"));
        String fecha = req.getParameter("fecha_actividad");

        // Busca y actualiza la actividad por ID
        for (Actividad actividad: actividades) {
            if (actividad.getId() == id) {
                actividad.setNombre_actividad(nombre);
                actividad.setDescripcion(descripcion);
                actividad.setImagenes(imagenes);
                actividad.setPrecio(precio);
                actividad.setCupo(cupo);
                actividad.setFecha_actividad(fecha);
                System.out.println("Actividad actualizada: " + actividad);
                break;
            }
        }

    }

    private void eliminarActividad(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));

        for (Actividad actividad : actividades) {
            if (actividad.getId() == id) {
                actividad.setEliminado(true);  // Marcamos como eliminada
                System.out.println("Actividad marcada como eliminada: " + actividad);
                break;
            }
        }
    }

}
