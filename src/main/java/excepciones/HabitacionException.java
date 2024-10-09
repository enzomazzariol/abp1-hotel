package excepciones;

public class HabitacionException extends Exception {
    // Definición de códigos de error
    public static final int ErrorInsertarHabitacion = 0;
    public static final int ErrorActualizarHabitacion = 1;
    public static final int ErrorEliminarHabitacion = 2;
    public static final int ErrorListarHabitaciones = 3;

    // Descripciones de los errores
    public static String[] errores = {
            "Error al insertar la habitación. Verifica los datos.",
            "Error al actualizar la habitación. Verifica los datos.",
            "Error al eliminar la habitación. Verifica el ID.",
            "Error al listar las habitaciones."
    };

    // Atributo para almacenar el código de error
    private int mensaje;

    // Constructor que recibe el código de error
    public HabitacionException(int mensaje) {
        this.mensaje = mensaje;
    }

    // Sobreescritura del método getMessage para devolver el mensaje de error correspondiente
    @Override
    public String getMessage() {
        return errores[this.mensaje];
    }
}
