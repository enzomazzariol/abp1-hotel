package dao;


import model.Usuario;
import service.CifradoService;
import utils.Rol;
import java.sql.*;
import java.util.ArrayList;

import excepciones.UsuariosException;
import excepciones.ConexionException;

import javax.crypto.SecretKey;

public class UsuariosDAO extends Conexion {

    CifradoService cifradoService;

    public UsuariosDAO(){
        cifradoService = new CifradoService();
    }

    // Constantes SQL para el CRUD
    public static final String SELECT_USUARIOS = "SELECT id, nombre, email, password, rol, fecha_registro, eliminado FROM usuarios where eliminado = 0";
    public static final String INSERT_USUARIO = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, 'cliente')";
    public static final String INSERT_USUARIO_ROL = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, '?')";
    public static final String UPDATE_USUARIO = "UPDATE usuarios SET nombre = ?, email = ?, password = ?, rol = ? WHERE id = ?";
    public static final String DELETE_USUARIO = "UPDATE usuarios SET eliminado = 1 WHERE id = ?"; // Marcar como eliminado
    public static final String UPDATE_IMAGEN_USUARIO = "UPDATE usuarios SET imagen = ? WHERE id = ?";
    public static final String RECOGER_DATOS_BY_USUARIO = "SELECT id, nombre, email, password, rol, fecha_registro, imagen, eliminado FROM usuarios WHERE id = ?";
    public static final String UPDATE_NOMBRE_USUARIO = "UPDATE usuarios SET nombre = ? WHERE id = ?";
    public static final String UPDATE_EMAIL_USUARIO = "UPDATE usuarios SET email = ? WHERE id = ?";
    public static final String UPDATE_CONTRASEÑA_USUARIO = "UPDATE usuarios SET password = ? WHERE id = ?";
    public static final String UPDATE_ALL_USUARIO = "UPDATE usuarios SET nombre = ?, email = ?, password = ?, imagen = ?, rol = ? WHERE id = ?";

    // -----------------------------------------SELECTS------------------------------------------------------

    // LISTAR TODOS LOS USUARIOS
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
                boolean eliminado = rs.getBoolean("eliminado");
                Rol rol = Rol.valueOf(rolParam.toUpperCase());

                Usuario nuevoUsuario = new Usuario(id, nombre, email, password, rol, fechaRegistro, eliminado);
                listaUsuarios.add(nuevoUsuario);

                System.out.println(nuevoUsuario);
            }
        } catch (Exception e) {
            throw new UsuariosException(UsuariosException.ErrorListarUsuarios);
        } finally {
            conn.desconectar();
        }
        return listaUsuarios;
    }

    // SELECT USUARIO POR ID
    public Usuario usuarioById(int idUsuario) throws ConexionException, SQLException {
        Usuario nuevoUsuario = null;
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(RECOGER_DATOS_BY_USUARIO);

            // Configura el parámetro de la consulta (id)
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // Si encuentra un resultado
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String rolParam = rs.getString("rol");
                String fechaRegistro = rs.getString("fecha_registro");
                String imagen = rs.getString("imagen");
                boolean eliminado = rs.getBoolean("eliminado");

                // Convierte el rol a Enum, asegurando que el string esté en mayúsculas
                Rol rol = Rol.valueOf(rolParam.toUpperCase());

                // Obtener la clave DES desde la base de datos
                SecretKey clave = cifradoService.obtenerClaveDesdeBD();
                if (clave == null) {
                    throw new RuntimeException();
                }

                String passwordDescifrada = cifradoService.descifrarDES(password, clave);

                // Crea el objeto Usuario
                nuevoUsuario = new Usuario(id, nombre, email, passwordDescifrada, rol, fechaRegistro, eliminado, imagen);

                System.out.println(nuevoUsuario); // Imprime el usuario encontrado
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Cerrar recursos
            conn.desconectar(); // Asegúrate de tener un método para cerrar la conexión
        }

        return nuevoUsuario; // Retorna el usuario, o null si no encontró nada
    }

    // -----------------------------------------INSERTS------------------------------------------------------

    // INSERTAR NUEVO USUARIO
    public void insertarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException, UsuariosException, ConexionException  {
        try (Connection connection = new Conexion().conectar();
             PreparedStatement ps = connection.prepareStatement(INSERT_USUARIO)) {

            // Asignar los valores a la consulta SQL
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            //ps.setString(4, usuario.getRol().name()); // Convertir Rol a String usando .name()
            //ps.setString(5, usuario.getFechaRegistro()); // Agregar la fecha de registro

            // Ejecutar la consulta
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar el usuario", e);
        }
    }

    // -----------------------------------------UPDATES------------------------------------------------------

    // ACTUALIZAR USUARIO
    public void actualizarAllUsuario(Usuario usuario) throws SQLException, UsuariosException, ConexionException  {
        try (Connection connection = new Conexion().conectar();
             PreparedStatement ps = connection.prepareStatement(UPDATE_ALL_USUARIO)) {

            // Asignar los valores a la consulta SQL
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getImagen());
            ps.setString(5, usuario.getRol().name()); // Convertir Rol a String usando .name()
            ps.setInt(6, usuario.getId());

            // Ejecutar la actualización
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
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

    // -----------------------------------------DELETES------------------------------------------------------

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
