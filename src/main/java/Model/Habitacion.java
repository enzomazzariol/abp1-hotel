package Model;

import Utils.Estado;
import Utils.TipoHabitacion;

public class Habitacion {

    private int id;
    private TipoHabitacion tipoHabitacion;
    private String imagen;
    private double precio;
    private Estado estado;
    private boolean eliminado;

    public Habitacion(int id, TipoHabitacion tipoHabitacion, String imagen, double precio, Estado estado) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.imagen = imagen;
        this.precio = precio;
        this.estado = estado;
    }

    public Habitacion(TipoHabitacion tipoHabitacion, String imagen, double precio, Estado estado) {
        this.tipoHabitacion = tipoHabitacion;
        this.imagen = imagen;
        this.precio = precio;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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
