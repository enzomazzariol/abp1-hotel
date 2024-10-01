package DAO;

import Model.Actividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActividadesDAO extends Conexion {

    public static final String SELECT_ACTIVIDADES = "select id, nombre_actividad, descripcion, imagen, precio, cupo, " +
            "fecha_actividad from actividades";

    public static final String INSERT_ACTIVIDADES = "";

    public static final String UPDATE_ACTIVIDADES = "";

    public static final String DELETE_ACTIVIDADES = "";

    public ArrayList<Actividad> obtenerActividades() throws SQLException, ClassNotFoundException {
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        Conexion conexionBD = new Conexion();

        PreparedStatement ps = conn.prepareStatement(SELECT_ACTIVIDADES);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre_actividad");
            String descripcion = rs.getString("descripcion");
            String imagenes = rs.getString("imagen");
            double precio = rs.getDouble("precio");
            int cupo = rs.getInt("cupo");
            String fecha_actividad = rs.getString("fecha_actividad");

            Actividad nuevaActividad = new Actividad(id, nombre, descripcion, imagenes, precio, cupo, fecha_actividad);
            System.out.println(nuevaActividad);
            listaActividades.add(nuevaActividad);
        }

        System.out.println("Se obtuvieron " + listaActividades.size() + " actividades de la base de datos.");

        conexionBD.desconectar();
        return listaActividades;
    }
}

