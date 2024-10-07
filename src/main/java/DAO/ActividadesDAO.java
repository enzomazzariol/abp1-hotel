package DAO;

import Model.Actividad;

import java.sql.*;
import java.util.ArrayList;

public class ActividadesDAO extends Conexion {

    // Constantes SQL para el CRUD
    public static final String SELECT_ACTIVIDADES = "select id, nombre_actividad, descripcion, imagen, precio, cupo, " +
            "fecha_actividad from actividades";

    public static final String INSERT_ACTIVIDADES = "insert into actividades (nombre_actividad, descripcion, imagen," +
            "precio, cupo, fecha_actividad) values (?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_ACTIVIDADES = "update actividades set nombre_actividad = ?, descripcion = ?, imagen = ?, precio = ?, cupo = ?, fecha_actividad = ? where id = ?";

    public static final String DELETE_ACTIVIDADES = "delete from actividades where id = ?";

    public static final String UPDATE_ELIMINADO = "update actividades set eliminado = 1 where id = ?";


    // Metodo para lista todas las actividades de la BBDD
    public ArrayList<Actividad> listarActividades() throws SQLException, ClassNotFoundException {
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_ACTIVIDADES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_actividad");
                String descripcion = rs.getString("descripcion");
                String imagenes = rs.getString("imagen");
                double precio = rs.getDouble("precio");
                int cupo = rs.getInt("cupo");
                String fecha_actividad = rs.getString("fecha_actividad");

                Actividad nuevaActividad = new Actividad(id, nombre, descripcion, imagenes, precio, cupo, fecha_actividad);
                listaActividades.add(nuevaActividad);

                System.out.println(nuevaActividad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.desconectar();
        }
        return listaActividades;
    }

    public void insertarActividad(Actividad actividad) throws SQLException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(INSERT_ACTIVIDADES);

            // Asigna los valores a la consulta SQL
            ps.setString(1, actividad.getNombre_actividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setString(3, actividad.getImagenes());
            ps.setDouble(4, actividad.getPrecio());
            ps.setInt(5, actividad.getCupo());
            ps.setString(6, actividad.getFecha_actividad());

            // Ejecuta la consulta
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar la actividad", e);
        } finally {
            conn.desconectar();
        }
    }

    public void actualizarActividad(Actividad actividad) throws SQLException {
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(UPDATE_ACTIVIDADES);
            ps.setString(1, actividad.getNombre_actividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setString(3, actividad.getImagenes());
            ps.setDouble(4, actividad.getPrecio());
            ps.setInt(5, actividad.getCupo());
            ps.setString(6, actividad.getFecha_actividad());
            ps.setInt(7, actividad.getId());

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar la actividad", e);
        } finally {
            conn.desconectar();
        }
    }

    public void eliminarActividad(int id) throws SQLException, ClassNotFoundException {
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(UPDATE_ELIMINADO);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la actividad", e);
        } finally {
            conn.desconectar();
        }
    }
}

