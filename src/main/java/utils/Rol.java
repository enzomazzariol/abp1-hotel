package utils;

public enum Rol {
    ADMIN("admin"),
    RECEPCIONISTA("recepcionista"),
    CLIENTE("cliente");

    private final String nombre;

    Rol(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
