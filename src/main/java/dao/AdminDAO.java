package dao;

import excepciones.ConexionException;
import model.ReservaGeneral;
import model.Usuario;
import utils.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO extends Conexion {

    public static final String SELECT_ALL_RESERVAS = "select * from AllReservas";
    public static final String ACTUALIZAR_ROL_USUARIO = "update usuarios set rol = 'admin' where id = ?";
    public static final String ACTUALIZAR_ESTADO_HABITACION = "update habitaciones set estado = ?, precio = ? where id = ?";

    public ArrayList<ReservaGeneral> getAllReservas() {
        ArrayList<ReservaGeneral> reservas = new ArrayList<>();
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_ALL_RESERVAS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReservaGeneral reserva = new ReservaGeneral();
                reserva.setUsuario(rs.getString("usuario"));
                reserva.setTipoReserva(rs.getString("tipo_reserva"));
                reserva.setDetalleReserva(rs.getString("detalle_reserva"));
                reserva.setEstado(rs.getString("estado"));
                reserva.setFechaEntrada(rs.getString("fecha_entrada"));
                reserva.setFechaSalida(rs.getString("fecha_salida"));
                reserva.setFechaReserva(rs.getTimestamp("fecha_reserva"));
                reservas.add(reserva);
                System.out.println(reserva);
            }
        } catch (SQLException | ClassNotFoundException | ConexionException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public void actualizarRolUsuario(int id) throws ConexionException, SQLException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(ACTUALIZAR_ROL_USUARIO);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ConexionException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.desconectar();
        }
    }

    public void actualizarEstadoHabitacion(Estado estado, int precio, int id) throws ConexionException, SQLException {
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(ACTUALIZAR_ESTADO_HABITACION);
            ps.setString(1, String.valueOf(estado));
            ps.setInt(2, precio);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (ConexionException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.desconectar();
        }
    }
}
