package DAO;

import Model.ReservaHabitacion;
import Utils.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaHabitacionDAO {
    // Constantes SQL para el CRUD
    public static final String AGREGAR_RESERVA_HABITACION = "INSERT INTO reserva_habitaciones (id_usuario, id_habitacion, fecha_entrada, fecha_salida, estado, fecha_reserva) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String ACTUALIZAR_RESERVA_HABITACION = "UPDATE reserva_habitaciones SET id_usuario = ?, id_habitacion = ?, fecha_entrada = ?, fecha_salida = ?, estado = ?, fecha_reserva = ? WHERE id = ?";
    public static final String ELIMINAR_RESERVA_HABITACION = "DELETE FROM reserva_habitaciones WHERE id = ?";
    public static final String SELECT_RESERVA_HABITACION_POR_ID = "SELECT * FROM reserva_habitaciones WHERE id = ?";
    public static final String SELECT_RESERVA_HABITACIONES = "SELECT * FROM reserva_habitaciones";
    public static final String ACTUALIZAR_ELIMINADO_RESERVA_HABITACION = "UPDATE reserva_habitaciones SET eliminado = 1 WHERE id = ?";

    // INSERT:
    public void agregarReservaHabitacion(ReservaHabitacion reservaHabitacion) {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(AGREGAR_RESERVA_HABITACION);
            ps.setInt(1, reservaHabitacion.getIdUsuario());
            ps.setInt(2, reservaHabitacion.getIdHabitacion());
            ps.setString(3, reservaHabitacion.getFechaEntrada());
            ps.setString(4, reservaHabitacion.getFechaSalida());
            ps.setString(5, reservaHabitacion.getEstado().name());
            ps.setString(6, reservaHabitacion.getFechaReserva());
            ps.executeUpdate();
            System.out.println("Se ha insertado la reserva en la base de datos correctamente!");
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // ACTUALIZAR:
    public void actualizarReserva(ReservaHabitacion reservaHabitacion) {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_RESERVA_HABITACION);
            ps.setInt(1, reservaHabitacion.getIdUsuario());
            ps.setInt(2, reservaHabitacion.getIdHabitacion());
            ps.setString(3, reservaHabitacion.getFechaEntrada());
            ps.setString(4, reservaHabitacion.getFechaSalida());
            ps.setString(5, reservaHabitacion.getEstado().name());
            ps.setString(6, reservaHabitacion.getFechaReserva());
            ps.setInt(7, reservaHabitacion.getId());
            ps.executeUpdate();
            System.out.println("Se ha actualizado la reserva en la base de datos correctamente!");
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // ELIMINAR PERMAMENTEMENTE:
    public void eliminarReserva(int id) {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ELIMINAR_RESERVA_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Se ha eliminado la reserva en la base de datos correctamente!");
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // ACTUALIZAR EL ELIMINADO:
    public void actualizarEliminadoReservaHabitacion(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_ELIMINADO_RESERVA_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException("Error al eliminar la actividad", e);
        }
    }

    // VER UNA RESERVA:
    public ArrayList<ReservaHabitacion> selectReserva(int id) {
        try {
            ArrayList<ReservaHabitacion> reservaHabitacions = new ArrayList<>();

            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(SELECT_RESERVA_HABITACION_POR_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                int idHabitacion = rs.getInt("id_habitacion");
                String fechaEntrada = rs.getString("fecha_entrada");
                String fechaSalida = rs.getString("fecha_salida");
                String estadoParam = rs.getString("estado");
                String fechaReserva = rs.getString("fecha_reserva");

                Estado estado = Estado.valueOf(estadoParam.toUpperCase());

                ReservaHabitacion reservaHabitacion = new ReservaHabitacion(idUsuario, estado, fechaReserva, idHabitacion, fechaEntrada, fechaSalida);
                reservaHabitacions.add(reservaHabitacion);
            }
            conn.close();
            return reservaHabitacions;

        } catch (SQLException | ClassNotFoundException e) {
             throw new RuntimeException(e);
        }

    }

    // READ:
    public ArrayList<ReservaHabitacion> listarResevaHabitaciones() {
        try {
            ArrayList<ReservaHabitacion> reservaHabitacions = new ArrayList<>();

            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(SELECT_RESERVA_HABITACIONES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuario = rs.getInt("id_usuario");
                int idHabitacion = rs.getInt("id_habitacion");
                String fechaEntrada = rs.getString("fecha_entrada");
                String fechaSalida = rs.getString("fecha_salida");
                String estadoParam = rs.getString("estado");
                String fechaReserva = rs.getString("fecha_reserva");

                Estado estado = Estado.valueOf(estadoParam.toUpperCase());

                ReservaHabitacion reservaHabitacion = new ReservaHabitacion(idUsuario, estado, fechaReserva, idHabitacion, fechaEntrada, fechaSalida);
                reservaHabitacions.add(reservaHabitacion);
            }
            conn.close();
            return reservaHabitacions;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
