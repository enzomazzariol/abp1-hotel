package DAO;


import Model.Usuario;
import Utils.Rol;
import java.sql.*;
import java.util.ArrayList;

import excepciones.ActividadesException;
import excepciones.UsuariosException;
import excepciones.ConexionException;

public class UsuariosDAO extends Conexion {

    // Constantes SQL para el CRUD
    public static final String SELECT_USUARIOS = "SELECT id, nombre, email, password, rol, fecha_registro FROM usuarios";
    public static final String INSERT_USUARIO = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_USUARIO = "UPDATE usuarios SET nombre = ?, email = ?, password = ?, rol = ? WHERE id = ?";
    public static final String DELETE_USUARIO = "UPDATE usuarios SET eliminado = 1 WHERE id = ?"; // Marcar como eliminado

    // Método para listar todos los usuarios de la base de datos
    public ArrayList<Usuario> listarUsuarios() throws SQLException, UsuariosException, ConexionException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Conexion conn = new Conexion();

        try {
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_USUARIOS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String rolParam = rs.getString("rol"); // Convertir el rol a enum
                String fechaRegistro = rs.getString("fecha_registro");

                Rol rol = Rol.valueOf(rolParam.toUpperCase());

                Usuario nuevoUsuario = new Usuario(id, nombre, email, password, rol, fechaRegistro);
                listaUsuarios.add(nuevoUsuario);

                System.out.println(nuevoUsuario);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new UsuariosException(UsuariosException.ErrorListarUsuarios);
        } finally {
            conn.desconectar();
        }
        return listaUsuarios;
    }

    // Método para insertar un nuevo usuario en la base de datos
    public void insertarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException  {
        try (Connection connection = new Conexion().conectar();
             PreparedStatement ps = connection.prepareStatement(INSERT_USUARIO)) {

            // Asignar los valores a la consulta SQL
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol().name()); // Convertir Rol a String usando .name()
            //ps.setString(5, usuario.getFechaRegistro()); // Agregar la fecha de registro

            // Ejecutar la consulta
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar el usuario", e);
        }
    }

    // Método para actualizar los datos de un usuario (no actualiza la fecha de registro)
    public void actualizarUsuario(Usuario usuario) throws SQLException, UsuariosException, ConexionException  {
        try (Connection connection = new Conexion().conectar();
             PreparedStatement ps = connection.prepareStatement(UPDATE_USUARIO)) {

            // Asignar los valores a la consulta SQL
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol().name()); // Convertir Rol a String usando .name()
            ps.setInt(5, usuario.getId());

            // Ejecutar la actualización
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    // Método para eliminar (marcar como eliminado) un usuario
    public void eliminarUsuario(int id) throws UsuariosException {
        try (Connection connection = new Conexion().conectar();
             PreparedStatement ps = connection.prepareStatement(DELETE_USUARIO)) {

            // Asignar el valor del ID
            ps.setInt(1, id);

            // Ejecutar la actualización
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException | ConexionException e) {
            throw new RuntimeException("Error al eliminar el usuario", e);
        }
    }
}
