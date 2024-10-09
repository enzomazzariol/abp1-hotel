package excepciones;

public class LoginException extends Exception {
    // Definición de los códigos de error
    public static final int ErrorUsuarioNoEncontrado = 0;
    public static final int ErrorCredencialesInvalidas = 1;
    public static final int ErrorUsuarioContraseña = 2;

    // Descripciones de los errores
    public static String[] errores = {
            "Usuario no encontrado.",
            "Credenciales inválidas. Por favor, verifica tu nombre de usuario y contraseña.",
            "Error en el usuario o contraseña."
    };

    // Atributo para almacenar el código de error
    private int mensaje;

    // Constructor que recibe el código de error
    public LoginException(int mensaje) {
        this.mensaje = mensaje;
    }

    // Sobreescritura del método getMessage para devolver el mensaje de error correspondiente
    @Override
    public String getMessage() {
        return errores[this.mensaje];
    }
}
