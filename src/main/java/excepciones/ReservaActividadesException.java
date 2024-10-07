package excepciones;

public class ReservaActividadesException extends Exception {
    // Definición de códigos de error
    public static final int ErrorListarReservas = 0;
    public static final int ErrorInsertarReserva = 1;
    public static final int ErrorActualizarReserva = 2;
    public static final int ErrorEliminarReserva = 3;

    // Descripciones de los errores
    public static String[] errores = {
            "Error al listar las reservas de actividades.",
            "Error al insertar la reserva de actividad.",
            "Error al actualizar la reserva de actividad.",
            "Error al eliminar la reserva de actividad."
    };

    // Atributo para almacenar el código de error
    private int mensaje;

    // Constructor que recibe el código de error
    public ReservaActividadesException(int mensaje) {
        this.mensaje = mensaje;
    }

    // Sobreescritura del método getMessage para devolver el mensaje de error correspondiente
    @Override
    public String getMessage() {
        return errores[this.mensaje];
    }
}
