package utils;

import dao.Conexion;
import excepciones.ConexionException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class CifradoUtils {

    // Genera y guarda una clave DES en la base de datos.
    public SecretKey generarClaveDES() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);  // La longitud de la clave DES es 56 bits
        SecretKey llave_secreta = keyGenerator.generateKey();

        // Guardar la clave en la base de datos
        guardarClaveEnBD(llave_secreta);

        return llave_secreta;
    }

    // Guarda la clave secreta en la base de datos
    private void guardarClaveEnBD(SecretKey clave) throws Exception {
        Conexion conn = new Conexion();
        String sql = "INSERT INTO cifrado (llave_secreta) VALUES (?)";
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ps.setBytes(1, clave.getEncoded());  // Guardamos la clave como bytes
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException | ConexionException e) {
            throw new RuntimeException(e);
        }
    }

    // Recupera la clave secreta de la base de datos
    public SecretKey obtenerClaveDesdeBD() throws Exception {
        Conexion conn = new Conexion();
        String sql = "SELECT llave_secreta FROM cifrado LIMIT 1";
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] keyData = rs.getBytes("llave_secreta");
                return new SecretKeySpec(keyData, "DES");
            }
        } catch(Exception e){
            throw new Exception("Clave secreta no encontrada en la base de datos");
        }
        return null;
    }

    // Cifra el texto usando la clave DES
    public String cifrarDES(String texto, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] textoCifrado = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));

        // Convertir a base64 para almacenar en la base de datos
        return Base64.getEncoder().encodeToString(textoCifrado);
    }


    // Descifra el texto cifrado usando la clave DES
    public String descifrarDES(String textoCifradoBase64, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, clave);

        // Decodificar base64 antes de descifrar
        byte[] textoCifrado = Base64.getDecoder().decode(textoCifradoBase64);
        byte[] textoDescifrado = cipher.doFinal(textoCifrado);

        return new String(textoDescifrado, StandardCharsets.UTF_8);
    }
}
