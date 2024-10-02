package Utils;

public enum Estado {

    DISPONIBLE("disponible"),
    OCUPADA("ocupada"),
    MANTENIMIENTO("mantenimiento"),
    RESERVADO("reservado"),
    CANCELADO("cancelado"),
    COMPLETADO("completado");


    private final String estado;

    Estado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
