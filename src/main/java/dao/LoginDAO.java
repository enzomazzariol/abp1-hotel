package dao;

import model.Usuario;
import excepciones.ConexionException;
import excepciones.LoginException;
import service.CifradoService;

import javax.crypto.SecretKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    // Cambiar consulta SQL para solo seleccionar por nombre
    public static final String SELECT_USUARIOS_NOMBRE = "SELECT * FROM usuarios WHERE nombre = ?";

    CifradoService cifradoService;

    public LoginDAO(){
        cifradoService = new CifradoService();
    }

    public Usuario checklogin(String nombre, String passwordIngresada) throws LoginException, ConexionException {
        Usuario usuario = null;
        Conexion conn = new Conexion();

        try {
            Connection connection = conn.conectar();
            PreparedStatement ps = connection.prepareStatement(SELECT_USUARIOS_NOMBRE);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String passwordCifrada = rs.getString("password");

                // Obtener la clave DES desde la base de datos
                SecretKey clave = cifradoService.obtenerClaveDesdeBD();
                if (clave == null) {
                    throw new RuntimeException("Error al obtener la clave DES");
                }

                // Descifrar la contraseña
                String passwordDescifrada = cifradoService.descifrarDES(passwordCifrada, clave);

                // Verificar si la contraseña ingresada coincide con la descifrada
                if (!passwordDescifrada.equals(passwordIngresada)) {
                    throw new LoginException(1); // Contraseña incorrecta
                }

                // Crear el usuario si la autenticación fue exitosa
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(passwordCifrada); // Guardamos la cifrada en el usuario
                usuario.setEliminado(rs.getBoolean("eliminado"));
            } else {
                throw new LoginException(0); // Usuario no encontrado
            }

            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConexionException(ConexionException.ErrorConexionBD);
        } catch (LoginException e) {
            throw e; // Relanzamos el error para manejarlo más arriba
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado: " + e.getMessage());
        } finally {
            try {
                conn.desconectar();
            } catch (SQLException e) {
                throw new ConexionException(ConexionException.ErrorConexionBD);
            }
        }
    }

    public Usuario checkRegistro(String nombre, String passwordIngresada) throws LoginException, ConexionException {
        Usuario usuario = null;
        Conexion conn = new Conexion();

        try {
            Connection connection = conn.conectar();
            PreparedStatement ps = connection.prepareStatement(SELECT_USUARIOS_NOMBRE);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String passwordCifrada = rs.getString("password");

                // Crear el usuario si la autenticación fue exitosa
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(passwordCifrada); // Guardamos la cifrada en el usuario
                usuario.setEliminado(rs.getBoolean("eliminado"));
            } else {
                throw new LoginException(0); // Usuario no encontrado
            }

            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConexionException(ConexionException.ErrorConexionBD);
        } catch (LoginException e) {
            throw e; // Relanzamos el error para manejarlo más arriba
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado: " + e.getMessage());
        } finally {
            try {
                conn.desconectar();
            } catch (SQLException e) {
                throw new ConexionException(ConexionException.ErrorConexionBD);
            }
        }
    }



}
