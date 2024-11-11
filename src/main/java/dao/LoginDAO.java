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

                // Descifrar la contraseña almacenada en la base de datos
                String passwordDescifrada = cifradoService.descifrarDES(passwordCifrada, clave);

                // Comparar la contraseña ingresada con la descifrada
                if (passwordIngresada.equals(passwordDescifrada)) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setPassword(passwordCifrada); // Guardamos la cifrada en el usuario
                    usuario.setEliminado(rs.getBoolean("eliminado"));
                }
            }

            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConexionException(ConexionException.ErrorConexionBD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.desconectar();
            } catch (SQLException e) {
                throw new ConexionException(ConexionException.ErrorConexionBD);
            }
        }
    }
}
