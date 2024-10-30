package excepciones;

public class ActividadesException extends Exception {
    // Definición de códigos de error
    public static final int ErrorListarActividades = 0;
    public static final int ErrorInsertarActividad = 1;
    public static final int ErrorActualizarActividad = 2;
    public static final int ErrorEliminarActividad = 3;
    public static final int ErrorActualizarCupo = 4;

    // Descripciones de los errores
    public static String[] errores = {
            "Error al listar las actividades.",
            "Error al insertar la actividad.",
            "Error al actualizar la actividad.",
            "Error al eliminar la actividad.",
            "Error al actualizar los cupos de la actividad"
    };

    // Atributo para almacenar el código de error
    private int mensaje;

    // Constructor que recibe el código de error
    public ActividadesException(int mensaje) {
        this.mensaje = mensaje;
    }

    // Sobreescritura del método getMessage para devolver el mensaje de error correspondiente
    @Override
    public String getMessage() {
        return errores[this.mensaje];
    }
}
