package excepciones;

public class ReservaHabitacionException extends Exception {
    // Códigos de error
    public static final int ErrorInsertarReserva = 1;
    public static final int ErrorActualizarReserva = 2;
    public static final int ErrorEliminarReserva = 3;
    public static final int ErrorListarReservas = 4;
    public static final int ErrorSeleccionarReserva = 5;

    // Mensajes de error
    private static final String[] MENSAJES_ERROR = {
            "Error desconocido",
            "Error al insertar la reserva de habitación.",
            "Error al actualizar la reserva de habitación.",
            "Error al eliminar la reserva de habitación.",
            "Error al listar las reservas de habitación.",
            "Error al seleccionar una reserva de habitación."
    };

    // Atributo para almacenar el código de error
    private int codigoError;

    // Constructor que recibe el código de error
    public ReservaHabitacionException(int codigoError) {
        this.codigoError = codigoError;
    }

    // Sobrescribir el método getMessage para devolver el mensaje correspondiente
    @Override
    public String getMessage() {
        return MENSAJES_ERROR[codigoError];
    }
}
