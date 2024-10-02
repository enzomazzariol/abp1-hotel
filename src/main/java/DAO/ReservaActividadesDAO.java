package DAO;

import Model.Actividad;
import Model.ReservaActividad;
import Utils.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaActividadesDAO extends Conexion{

    // Constantes SQL para el CRUD
    public static final String SELECT_RESERVA_ACTIVIDAD= "SELECT id, id_usuario, id_actividad, estado, fecha_reserva, eliminado FROM reserva_actividades";

    public static final String INSERT_RESERVA_ACTIVIDAD = "INSERT INTO reserva_actividades (id_usuario, id_actividad, estado) values (?, ?, ?)";

    public static final String UPDATE_RESERVA_ACTIVIDADES = "update reserva_actividades set estado = ?, fecha_reserva = ? where id = ?";

    // Eliminar registro permanentemente
    public static final String DELETE_RESERVA_ACTIVIDADES = "delete from reserva_actividades where id = ?";

    // Elimina registro indefinidamente
    public static final String UPDATE_RESERVA_ACTIVIDAD_ELIMINADO = "update reserva_actividades set eliminado = 1 where id = ?";


    // Metodo para lista todas las reservas actividades de la BBDD
    public ArrayList<ReservaActividad> listarReservaActividades() throws SQLException, ClassNotFoundException {
        ArrayList<ReservaActividad> listaReservaActividades = new ArrayList<>();
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_RESERVA_ACTIVIDAD);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int id_usuario = rs.getInt("id_usuario");
                int id_actividad = rs.getInt("id_actividad");
                String estadoParam = rs.getString("estado");
                String fecha_reserva = rs.getString("fecha_reserva");
                boolean eliminado = rs.getBoolean("eliminado");

                Estado estado = Estado.valueOf(estadoParam.toUpperCase());

                ReservaActividad reservaActividad = new ReservaActividad(id, id_usuario, id_actividad, estado, fecha_reserva, eliminado);
                listaReservaActividades.add(reservaActividad);

                System.out.println(reservaActividad);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            conn.desconectar();
        }
        return listaReservaActividades;
    }

    public void insertarActividad(ReservaActividad reservaActividad) throws SQLException {

        try (Connection connection = new Conexion().conectar();
             PreparedStatement ps = connection.prepareStatement(INSERT_RESERVA_ACTIVIDAD)) {

            // Asigna los valores a la consulta SQL
            ps.setInt(1, reservaActividad.getIdUsuario());
            ps.setInt(2, reservaActividad.getIdActividad());
            ps.setString(3, reservaActividad.getEstado().name());

            // Ejecuta la consulta
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar la reserva actividad", e);
        }
    }

    public void actualizarReservaActividad(ReservaActividad reservaActividad){
        Conexion conn = new Conexion();

        try (Connection connection = conn.conectar();
             PreparedStatement ps = connection.prepareStatement(UPDATE_RESERVA_ACTIVIDADES)) {
            ps.setString(1, reservaActividad.getEstado().name());
            ps.setString(2, reservaActividad.getFechaReserva());
            ps.setInt(3, reservaActividad.getId());

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar la actividad", e);
        }
    }

    public void eliminarReservaActividad(int id) throws SQLException, ClassNotFoundException {
        Conexion conn = new Conexion();

        try(Connection connection = conn.conectar()){
            PreparedStatement ps = connection.prepareStatement(UPDATE_RESERVA_ACTIVIDAD_ELIMINADO);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error al eliminar la reserva de la actividad", e);
        }
    }}
