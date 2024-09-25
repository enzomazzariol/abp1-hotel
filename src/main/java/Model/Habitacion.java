package Model;

public class Habitacion {

    private int id;
    private String tipoHabitacion;
    private String imagen;
    private double precio;
    private String estado;

    public Habitacion(int id, String tipoHabitacion, String imagen, double precio, String estado) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.imagen = imagen;
        this.precio = precio;
        this.estado = estado;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "tipoHabitacion='" + tipoHabitacion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                '}';
    }
}
