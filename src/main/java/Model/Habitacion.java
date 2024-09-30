package Model;

public class Habitacion {

    private int id;
    private String tipoHabitacion;
    private String imagen;
    private double precio;
    private String estado;
    private boolean eliminado;

    public Habitacion(int id, String tipoHabitacion, String imagen, double precio, String estado, boolean eliminado) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.imagen = imagen;
        this.precio = precio;
        this.estado = estado;
        this.eliminado = eliminado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
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
