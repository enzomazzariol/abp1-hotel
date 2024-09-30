package Utils;

public enum Estado {

    RESERVADO("reservado"),
    CANCELADO("cancelado"),
    LIBRE("libre"),
    COMPLETADO("completado");


    private final String estado;

    Estado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
