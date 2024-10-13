package utils;

public enum TipoHabitacion {

    SENCILLA("sencilla"),
    DOBLE("doble"),
    SUITE("suite");

    private final String tipoHabitacion;

    TipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }
}
