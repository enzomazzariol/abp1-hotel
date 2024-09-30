package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Constantes para la configuración de la conexión a la base de datos
    public static final String SCHEMA_NAME = "hotel"; // Cambiar al nombre de tu esquema (Base de datos)
    public static final String USER = "root";         // Usuario de la base de datos
    public static final String PASS = "jupiter*";         // Contraseña de la base de datos
    public static final String URL = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=True";

    private Connection conexion;

    // Método para conectarse a la base de datos
    public Connection conectar() throws SQLException, ClassNotFoundException {
        // Cargar el driver de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establecer la conexión
        conexion = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Conexión exitosa a la base de datos: " + SCHEMA_NAME);
        return conexion;
    }

    // Método para desconectarse de la base de datos
    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión cerrada.");
        }
    }
}
