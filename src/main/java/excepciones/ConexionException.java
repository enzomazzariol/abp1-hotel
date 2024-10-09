package excepciones;

public class ConexionException extends Exception {
    // Definición de códigos de error
    public static final int ErrorCargarDriver = 0;
    public static final int ErrorConexionBD = 1;
    public static final int ErrorDesconexionBD = 2;

    // Descripciones de los errores
    public static String[] errores = {
            "Error al cargar el driver de la base de datos.",
            "Hubo un error en la conexión a la base de datos.",
            "Hubo un error al cerrar la conexión a la base de datos."
    };

    // Atributo para almacenar el código de error
    private int mensaje;

    // Constructor que recibe el código de error
    public ConexionException(int mensaje) {
        this.mensaje = mensaje;
    }

    // Sobreescritura del método getMessage para devolver el mensaje de error correspondiente
    @Override
    public String getMessage() {
        return errores[this.mensaje];
    }
}
