package dao;

import model.Actividad;
import excepciones.ActividadesException;
import excepciones.ConexionException;

import java.sql.*;
import java.util.ArrayList;

public class ActividadesDAO extends Conexion {

    public static final String SELECT_ACTIVIDADES = "select id, nombre_actividad, descripcion, imagen, precio, cupo, fecha_actividad, eliminado from actividades where eliminado = 0";
    public static final String INSERT_ACTIVIDADES = "insert into actividades (nombre_actividad, descripcion, imagen, precio, cupo, fecha_actividad) values (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_ACTIVIDADES = "update actividades set nombre_actividad = ?, descripcion = ?, imagen = ?, precio = ?, cupo = ?, fecha_actividad = ? where id = ?";
    public static final String DELETE_ACTIVIDADES = "delete from actividades where id = ?";
    public static final String UPDATE_ELIMINADO = "update actividades set eliminado = 1 where id = ?";
    public static final String UPDATE_CUPO_ACTIVIDAD = "UPDATE actividades SET cupo = ? WHERE id = ?";


    public ArrayList<Actividad> listarActividades() throws SQLException, ClassNotFoundException, ActividadesException, ConexionException {
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_ACTIVIDADES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_actividad");
                String descripcion = rs.getString("descripcion");
                String imagen = rs.getString("imagen");
                double precio = rs.getDouble("precio");
                int cupo = rs.getInt("cupo");
                String fecha_actividad = rs.getString("fecha_actividad");
                boolean eliminado = rs.getBoolean("eliminado");

                Actividad nuevaActividad = new Actividad(id, nombre, descripcion, imagen, precio, cupo, fecha_actividad, eliminado);
                listaActividades.add(nuevaActividad);
                System.out.println(nuevaActividad);
            }
        } catch (SQLException e) {
            throw new ActividadesException(ActividadesException.ErrorListarActividades);
        } finally {
            conn.desconectar();
        }
        return listaActividades;
    }

    public void insertarActividad(Actividad actividad) throws SQLException, ActividadesException, ConexionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(INSERT_ACTIVIDADES);
            ps.setString(1, actividad.getNombre_actividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setString(3, actividad.getImagen());
            ps.setDouble(4, actividad.getPrecio());
            ps.setInt(5, actividad.getCupo());
            ps.setString(6, actividad.getFecha_actividad());

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ActividadesException(ActividadesException.ErrorInsertarActividad);
        } finally {
            conn.desconectar();
        }
    }

    public void actualizarActividad(Actividad actividad) throws SQLException, ActividadesException, ConexionException {
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(UPDATE_ACTIVIDADES);
            ps.setString(1, actividad.getNombre_actividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setString(3, actividad.getImagen());
            ps.setDouble(4, actividad.getPrecio());
            ps.setInt(5, actividad.getCupo());
            ps.setString(6, actividad.getFecha_actividad());
            ps.setInt(7, actividad.getId());

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ActividadesException(ActividadesException.ErrorActualizarActividad);
        } finally {
            conn.desconectar();
        }
    }

    public void eliminarActividad(int id) throws SQLException, ClassNotFoundException, ActividadesException, ConexionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(UPDATE_ELIMINADO);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ActividadesException(ActividadesException.ErrorEliminarActividad);
        } finally {
            conn.desconectar();
        }
    }

    public Actividad obtenerActividadPorId(int id) throws SQLException, ConexionException {
        Actividad actividad = null;
        Conexion conn = new Conexion();
        try {
            String sql = "SELECT * FROM actividades WHERE id = ?";
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                actividad = new Actividad(rs.getInt("id"), rs.getString("nombre_actividad"), rs.getString("descripcion"), rs.getString("imagen"), rs.getDouble("precio"), rs.getInt("cupo"), rs.getString("fecha_actividad"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            conn.desconectar();
        }
        return actividad;
    }

    public void actualizarCupo(int idActividad, int nuevoCupo) throws SQLException, ConexionException, ActividadesException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(UPDATE_CUPO_ACTIVIDAD);
            ps.setInt(1, nuevoCupo);
            ps.setInt(2, idActividad);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ActividadesException(ActividadesException.ErrorActualizarCupo);
        } finally {
            conn.desconectar();
        }
    }
}
