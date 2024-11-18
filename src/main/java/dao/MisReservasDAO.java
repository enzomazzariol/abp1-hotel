package dao;

import excepciones.ConexionException;
import model.DetalleReservaActividad;
import model.DetalleReservaHabitacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MisReservasDAO {

    private static final String SELECT_RESERVA_ACTIVIDAD_BY_ID =
            "SELECT DISTINCT ra.id, " +
                    "       a.nombre_actividad, " +
                    "       ra.estado, " +
                    "       ra.fecha_reserva, " +
                    "       a.fecha_actividad AS fechasReservaHabitacion " +
                    "FROM reserva_actividades ra " +
                    "JOIN actividades a ON ra.id_actividad = a.id " +
                    "LEFT JOIN reserva_habitaciones rh ON ra.id_usuario = rh.id_usuario " +
                    "WHERE ra.id_usuario = ? " +
                    "GROUP BY ra.id, a.nombre_actividad, ra.estado, ra.fecha_reserva";


    private static String SELECT_RESERVA_HABITACION_BY_ID = "SELECT DISTINCT rh.id, h.tipo_habitacion, rh.estado, rh.fecha_reserva, " +
            "CONCAT(rh.fecha_entrada, ' al ', rh.fecha_salida) AS fechas, " +
            "rh.id_habitacion AS id " +
            "FROM reserva_habitaciones rh " +
            "JOIN habitaciones h ON rh.id_habitacion = h.id " +
            "WHERE rh.id_usuario = ?";

    public ArrayList<DetalleReservaActividad> reservaActividadesById(int idUsuario) {
        ArrayList<DetalleReservaActividad> reservas = new ArrayList<>();
        Conexion conn = new Conexion();

        try {

            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_RESERVA_ACTIVIDAD_BY_ID);
            ps.setInt(1, idUsuario); // Pasar el idUsuario como parámetro
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreActividad = rs.getString("nombre_actividad");
                String estadoReservaActividad = rs.getString("estado");
                String fechaReservaActividad = rs.getString("fecha_reserva");
                String fechasReservaHabitacion = rs.getString("fechasReservaHabitacion");

                DetalleReservaActividad detalle = new DetalleReservaActividad(nombreActividad, estadoReservaActividad, fechaReservaActividad, fechasReservaHabitacion, id);
                reservas.add(detalle);
            }

            return reservas;
        } catch (ConexionException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DetalleReservaHabitacion> reservaHabitacionesById(int idUsuario) {
        ArrayList<DetalleReservaHabitacion> reservasHabitaciones = new ArrayList<>();
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_RESERVA_HABITACION_BY_ID);
            ps.setInt(1, idUsuario); // Pasar el idUsuario como parámetro

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipoDeHabitacion = rs.getString("tipo_habitacion");
                String estadoReserva = rs.getString("estado");
                String fechaReserva = rs.getString("fecha_reserva");
                String fechas = rs.getString("fechas");

                DetalleReservaHabitacion detalle = new DetalleReservaHabitacion(tipoDeHabitacion, estadoReserva, fechaReserva, fechas, id);
                reservasHabitaciones.add(detalle);
            }

            return reservasHabitaciones;

        } catch (ConexionException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
