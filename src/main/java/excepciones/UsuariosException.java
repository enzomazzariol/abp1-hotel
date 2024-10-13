package excepciones;

public class UsuariosException extends Exception {
    // Definición de códigos de error
    public static final int ErrorListarUsuarios = 0;
    public static final int ErrorInsertarUsuario = 1;
    public static final int ErrorActualizarUsuario = 2;
    public static final int ErrorEliminarUsuario = 3;

    // Descripciones de los errores
    public static String[] errores = {
            "Error al listar los usuarios.",
            "Error al insertar el usuario.",
            "Error al actualizar el usuario.",
            "Error al eliminar el usuario."
    };

    // Atributo para almacenar el código de error
    private int mensaje;

    // Constructor que recibe el código de error
    public UsuariosException(int mensaje) {
        this.mensaje = mensaje;
    }

    // Sobreescritura del método getMessage para devolver el mensaje de error correspondiente
    @Override
    public String getMessage() {
        return errores[this.mensaje];
    }
}
