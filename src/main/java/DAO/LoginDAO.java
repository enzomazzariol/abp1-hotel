package DAO;

import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    // Constantes SQL
    public static final String SELECT_USUARIOS_NOMBRE_CONTRASENA = "SELECT id, nombre, email FROM usuarios WHERE nombre = ? AND password = ?";


    public Usuario checklogin(String nombre, String password) {
        Usuario usuario = new Usuario();
        try {
            Connection conn = new Conexion().conectar();
            PreparedStatement ps = conn.prepareStatement(SELECT_USUARIOS_NOMBRE_CONTRASENA);
            ps.setString(1, nombre);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));

            }
            conn.close();
            return usuario;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
