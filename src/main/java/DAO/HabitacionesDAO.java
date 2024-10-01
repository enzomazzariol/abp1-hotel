package DAO;

import Model.Habitacion;
import Utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HabitacionesDAO {
    // Constantes SQL para el CRUD:
    public static final String AGREGAR_HABITACION = "INSERT INTO habitaciones (tipo_habitacion, imagen, precio, estado) VALUES (?, ?, ?, ?)";
    public static final String ACTUALIZAR_HABITACION = "UPDATE habitaciones SET tipo_habitacion = ?, imagen = ?, precio = ?, estado = ? WHERE id = ?";
    public static final String ELIMINAR_HABITACION = "DELETE FROM habitaciones WHERE id = ?";

    // INSERTAR:
    public static void agregarHabitacion(Habitacion habitacion) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(AGREGAR_HABITACION);
            ps.setString(1, habitacion.getTipoHabitacion().name());
            ps.setString(2, habitacion.getImagen());
            ps.setDouble(3, habitacion.getPrecio());
            ps.setString(4, habitacion.getEstado().name());
            ps.executeUpdate();
            System.out.println("Se ha insertado la habitacion en la base de datos correctamente!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ACTUALIZAR:
    public static void actualizarHabitacion(Habitacion habitacion){
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // ELIMINAR:
    public static void eliminarHabitacion(int id) {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ELIMINAR_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Se ha eliminado la habitacion en la base de datos correctamente!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
