package dao;

import model.Habitacion;
import utils.Estado;
import utils.TipoHabitacion;
import excepciones.ConexionException;
import excepciones.HabitacionException;

import java.sql.*;
import java.util.ArrayList;

public class HabitacionesDAO {
    // Constantes SQL para el CRUD:
    public static final String AGREGAR_HABITACION = "INSERT INTO habitaciones (tipo_habitacion, imagen, precio, estado) VALUES (?, ?, ?, ?)";
    public static final String ACTUALIZAR_HABITACION = "UPDATE habitaciones SET tipo_habitacion = ?, imagen = ?, precio = ?, estado = ? WHERE id = ?";
    public static final String ACTUALIZAR_ELIMINADO_HABITACION = "UPDATE habitaciones SET eliminado = 1 WHERE id = ?";
    public static final String ELIMINAR_HABITACION = "DELETE FROM habitaciones WHERE id = ?";
    public static final String SELECT_HABITACIONES = "SELECT * FROM habitaciones WHERE eliminado = 0";

    // INSERTAR:
    public void insertarHabitacion(Habitacion habitacion) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(AGREGAR_HABITACION);
            ps.setString(1, habitacion.getTipoHabitacion().name());
            ps.setBytes(2, habitacion.getImagen());
            ps.setDouble(3, habitacion.getPrecio());
            ps.setString(4, habitacion.getEstado().name());
            ps.executeUpdate();
            System.out.println("Se ha insertado la habitacion en la base de datos correctamente!");
        } catch (SQLException e) {
            throw new HabitacionException(HabitacionException.ErrorInsertarHabitacion);
        } finally {
            conn.desconectar();
        }
    }

    // ACTUALIZAR:
    public void actualizarHabitacion(Habitacion habitacion) throws SQLException, HabitacionException, ConexionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(ACTUALIZAR_HABITACION);
            ps.setString(1, habitacion.getTipoHabitacion().name());
            ps.setBytes(2, habitacion.getImagen());
            ps.setDouble(3, habitacion.getPrecio());
            ps.setString(4, habitacion.getEstado().name());
            ps.setInt(5, habitacion.getId());
            ps.executeUpdate();
            System.out.println("Se ha actualizado la habitacion en la base de datos correctamente!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new HabitacionException(HabitacionException.ErrorActualizarHabitacion);
        } finally {
            conn.desconectar();
        }
    }

    // ACTUALIZAR EL ELIMINADO:
    public void actualizarEliminadoHabitacion(int id) throws SQLException, ClassNotFoundException, HabitacionException, ConexionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(ACTUALIZAR_ELIMINADO_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException | ConexionException e){
            throw new HabitacionException(HabitacionException.ErrorEliminarHabitacion);
        } finally {
            conn.desconectar();
        }
    }

    // READ:
    public ArrayList<Habitacion> listarHabitaciones() throws SQLException, HabitacionException, ConexionException {
        Conexion conn = new Conexion();
        try {
            ArrayList<Habitacion> habitacions = new ArrayList<>();
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_HABITACIONES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipoHabitacionParam = rs.getString("tipo_habitacion");
                Blob blobImagen = rs.getBlob("imagen");
                byte[] imagen = blobImagen.getBytes(1, (int) blobImagen.length());
                double precio = rs.getDouble("precio");
                String estadoParam = rs.getString("estado");

                TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabitacionParam.toUpperCase());
                Estado estado = Estado.valueOf(estadoParam.toUpperCase());

                Habitacion habitacion = new Habitacion(id, tipoHabitacion, imagen, precio, estado);
                habitacions.add(habitacion);

                System.out.println(habitacion);
            }
            return habitacions;
        } catch (SQLException | ClassNotFoundException | ConexionException e) {
            throw new HabitacionException(HabitacionException.ErrorListarHabitaciones);
        } finally {
            conn.desconectar();
        }
    }

    // ELIMINAR PERMAMENTEMENTE:
    public void eliminarHabitacion(int id) throws HabitacionException {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ELIMINAR_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Se ha eliminado la habitacion en la base de datos correctamente!");
            conn.close();
        } catch (SQLException | ClassNotFoundException | ConexionException e) {
            throw new HabitacionException(HabitacionException.ErrorEliminarHabitacion);
        }
    }
}
