package DAO;

import Model.Habitacion;
import Utils.Constants;
import Utils.Estado;
import Utils.TipoHabitacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitacionesDAO {
    // Constantes SQL para el CRUD:
    public static final String AGREGAR_HABITACION = "INSERT INTO habitaciones (tipo_habitacion, imagen, precio, estado) VALUES (?, ?, ?, ?)";
    public static final String ACTUALIZAR_HABITACION = "UPDATE habitaciones SET tipo_habitacion = ?, imagen = ?, precio = ?, estado = ? WHERE id = ?";
    public static final String ACTUALIZAR_ELIMINADO_HABITACION = "UPDATE habitaciones SET eliminado = 1 WHERE id = ?";
    public static final String ELIMINAR_HABITACION = "DELETE FROM habitaciones WHERE id = ?";
    public static final String SELECT_HABITACIONES = "SELECT * FROM habitaciones";

    // INSERTAR:
    public void agregarHabitacion(Habitacion habitacion) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(AGREGAR_HABITACION);
            ps.setString(1, habitacion.getTipoHabitacion().name());
            ps.setString(2, habitacion.getImagen());
            ps.setDouble(3, habitacion.getPrecio());
            ps.setString(4, habitacion.getEstado().name());
            ps.executeUpdate();
            System.out.println("Se ha insertado la habitacion en la base de datos correctamente!");
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ACTUALIZAR:
    public void actualizarHabitacion(Habitacion habitacion){
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_HABITACION);
            ps.setString(1, habitacion.getTipoHabitacion().name());
            ps.setString(2, habitacion.getImagen());
            ps.setDouble(3, habitacion.getPrecio());
            ps.setString(4, habitacion.getEstado().name());
            ps.setInt(5, habitacion.getId());
            ps.executeUpdate();
            System.out.println("Se ha actualizado la habitacion en la base de datos correctamente!");
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // ELIMINAR PERMAMENTEMENTE:
    public void eliminarHabitacion(int id) {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ELIMINAR_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Se ha eliminado la habitacion en la base de datos correctamente!");
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // ACTUALIZAR EL ELIMINADO:
    public void actualizarEliminadoHabitacion(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_ELIMINADO_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException("Error al eliminar la habitacion: ", e);
        }
    }

    // READ:
    public ArrayList<Habitacion> listarHabitaciones(){
        try {
            ArrayList<Habitacion> habitacions = new ArrayList<>();

            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(SELECT_HABITACIONES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipoHabitacionParam = rs.getString("tipo_habitacion");
                String imagen = rs.getString("imagen");
                double precio = rs.getDouble("precio");
                String estadoParam = rs.getString("estado");

                TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabitacionParam.toUpperCase());
                Estado estado = Estado.valueOf(estadoParam.toUpperCase());

                Habitacion habitacion = new Habitacion(id, tipoHabitacion, imagen, precio, estado);
                habitacions.add(habitacion);
            }
            conn.close();
            return habitacions;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
