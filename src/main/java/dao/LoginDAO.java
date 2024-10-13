package dao;

import model.Usuario;
import excepciones.ConexionException;
import excepciones.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    // Constantes SQL
    public static final String SELECT_USUARIOS_NOMBRE_CONTRASENA = "SELECT id, nombre, email FROM usuarios WHERE nombre = ? AND password = ?";

    public Usuario checklogin(String nombre, String password) throws LoginException, ConexionException {
        Usuario usuario = new Usuario();
        Conexion conn = new Conexion();
        try {
            Connection connection = conn.conectar();
            PreparedStatement ps = connection.prepareStatement(SELECT_USUARIOS_NOMBRE_CONTRASENA);
            ps.setString(1, nombre);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
            } else {
                throw new LoginException(LoginException.ErrorCredencialesInvalidas);
            }
            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConexionException(ConexionException.ErrorConexionBD);
        } finally {
            try {
                conn.desconectar();
            } catch (SQLException e) {
                throw new ConexionException(ConexionException.ErrorConexionBD);
            }
        }
    }
}
