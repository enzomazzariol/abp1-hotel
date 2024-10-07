package DAO;

import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    // Constantes SQL
    public static final String SELECT_USUARIOS_NOMBRE_CONTRASENA = "SELECT id, nombre, email FROM usuarios WHERE nombre = ? AND password = ?";


    public Usuario checklogin(String nombre, String password) throws SQLException {
        Usuario usuario = new Usuario();
        Conexion conn = new Conexion();
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(SELECT_USUARIOS_NOMBRE_CONTRASENA);
            ps.setString(1, nombre);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
            }
            return usuario;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            conn.desconectar();
        }

    }
}
