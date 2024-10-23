package dao;

import model.ReservaHabitacion;
import utils.Estado;
import excepciones.ConexionException;
import excepciones.ReservaHabitacionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaHabitacionDAO {
    // Constantes SQL para el CRUD
    public static final String AGREGAR_RESERVA_HABITACION = "INSERT INTO reserva_habitaciones (id_usuario, id_habitacion, fecha_entrada, fecha_salida, estado, fecha_reserva) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String ACTUALIZAR_RESERVA_HABITACION = "UPDATE reserva_habitaciones SET id_usuario = ?, id_habitacion = ?, fecha_entrada = ?, fecha_salida = ?, estado = ?, fecha_reserva = ? WHERE id = ?";
    public static final String ACTUALIZAR_ESTADO_RESERVA_HABITACION = "UPDATE reserva_habitaciones SET  estado = ? WHERE id = ?";
    public static final String ELIMINAR_RESERVA_HABITACION = "DELETE FROM reserva_habitaciones WHERE id = ?";
    public static final String SELECT_RESERVA_HABITACION_POR_ID = "SELECT * FROM reserva_habitaciones WHERE id = ?";
    public static final String SELECT_RESERVA_HABITACIONES = "SELECT * FROM reserva_habitaciones WHERE eliminado = 0";
    public static final String ACTUALIZAR_ELIMINADO_RESERVA_HABITACION = "UPDATE reserva_habitaciones SET eliminado = 1, estado = 'cancelado' WHERE id = ?";

    // INSERT:
    public void agregarReservaHabitacion(ReservaHabitacion reservaHabitacion) throws SQLException, ConexionException, ReservaHabitacionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(AGREGAR_RESERVA_HABITACION);
            ps.setInt(1, reservaHabitacion.getIdUsuario());
            ps.setInt(2, reservaHabitacion.getIdHabitacion());
            ps.setString(3, reservaHabitacion.getFechaEntrada());
            ps.setString(4, reservaHabitacion.getFechaSalida());
            ps.setString(5, reservaHabitacion.getEstado().name());
            ps.setString(6, reservaHabitacion.getFechaReserva());
            ps.executeUpdate();
            System.out.println("Se ha insertado la reserva en la base de datos correctamente!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ReservaHabitacionException(ReservaHabitacionException.ErrorInsertarReserva);
        } finally {
            conn.desconectar();
        }
    }

    // ACTUALIZAR:
    public void actualizarReserva(ReservaHabitacion reservaHabitacion) throws SQLException, ConexionException, ReservaHabitacionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(ACTUALIZAR_RESERVA_HABITACION);
            ps.setInt(1, reservaHabitacion.getIdUsuario());
            ps.setInt(2, reservaHabitacion.getIdHabitacion());
            ps.setString(3, reservaHabitacion.getFechaEntrada());
            ps.setString(4, reservaHabitacion.getFechaSalida());
            ps.setString(5, reservaHabitacion.getEstado().name());
            ps.setString(6, reservaHabitacion.getFechaReserva());
            ps.setInt(7, reservaHabitacion.getId());
            ps.executeUpdate();
            System.out.println("Se ha actualizado la reserva en la base de datos correctamente!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ReservaHabitacionException(ReservaHabitacionException.ErrorActualizarReserva);
        } finally {
            conn.desconectar();
        }
    }

    // ACTUALIZAR ESTADO:
    public void actualizarEstadoReservaHabitacion(ReservaHabitacion reservaHabitacion) throws SQLException, ConexionException, ReservaHabitacionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(ACTUALIZAR_ESTADO_RESERVA_HABITACION);
            ps.setString(1, reservaHabitacion.getEstado().name());
            ps.setInt(2, reservaHabitacion.getId());
            ps.executeUpdate();
            System.out.println("Se ha actualizado el estado de la reserva en la base de datos correctamente!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ReservaHabitacionException(ReservaHabitacionException.ErrorActualizarReserva);
        } finally {
            conn.desconectar();
        }
    }

    // ACTUALIZAR EL ELIMINADO:
    public void actualizarEliminadoReservaHabitacion(int id) throws SQLException, ClassNotFoundException, ConexionException, ReservaHabitacionException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(ACTUALIZAR_ELIMINADO_RESERVA_HABITACION);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new ReservaHabitacionException(ReservaHabitacionException.ErrorEliminarReserva);
        } finally {
            conn.desconectar();
        }
    }


    // READ:
    public ArrayList<ReservaHabitacion> listarResevaHabitaciones() throws SQLException, ConexionException, ReservaHabitacionException {
        Conexion conn = new Conexion();
        try {
            ArrayList<ReservaHabitacion> reservaHabitacions = new ArrayList<>();
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_RESERVA_HABITACIONES);
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

                ReservaHabitacion reservaHabitacion = new ReservaHabitacion(id, idUsuario, estado, fechaReserva, idHabitacion, fechaEntrada, fechaSalida);
                reservaHabitacions.add(reservaHabitacion);

                System.out.println(reservaHabitacion);
            }
            return reservaHabitacions;

        } catch (SQLException | ClassNotFoundException e) {
            throw new ReservaHabitacionException(ReservaHabitacionException.ErrorListarReservas);
        } finally {
            conn.desconectar();
        }
    }

    // Funciones por si sirven en un futuro:

    // VER UNA RESERVA:
    public ArrayList<ReservaHabitacion> selectReserva(int id) throws ReservaHabitacionException {
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

        } catch (SQLException | ClassNotFoundException | ConexionException e) {
            throw new ReservaHabitacionException(ReservaHabitacionException.ErrorSeleccionarReserva);
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
        } catch (SQLException | ClassNotFoundException | ConexionException e) {
            throw new RuntimeException(e);
        }
    }


}
