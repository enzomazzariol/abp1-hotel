package model;

import java.util.Arrays;

public class Actividad {
    private int id;
    private String nombre_actividad;
    private String descripcion;
    private byte[] imagen;
    private double precio;
    private int cupo;
    private String fecha_actividad;
    private boolean eliminado;

    public Actividad(int id, String nombre_actividad, String descripcion, byte[] imagen, double precio, int cupo, String fecha_actividad) {
        this.id = id;
        this.nombre_actividad = nombre_actividad;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.cupo = cupo;
        this.fecha_actividad = fecha_actividad;
        this.eliminado = false; // Inicialmente no eliminada
    }

    public Actividad(String nombre_actividad, String descripcion, byte[] imagen, double precio, int cupo, String fecha_actividad) {
        this.nombre_actividad = nombre_actividad;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.cupo = cupo;
        this.fecha_actividad = fecha_actividad;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getFecha_actividad() {
        return fecha_actividad;
    }

    public void setFecha_actividad(String fecha_actividad) {
        this.fecha_actividad = fecha_actividad;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre_actividad='" + nombre_actividad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen=" + Arrays.toString(imagen) + // Mostrar array de bytes
                ", precio=" + precio +
                ", cupo=" + cupo +
                ", fecha_actividad='" + fecha_actividad + '\'' +
                ", eliminado=" + eliminado +
                '}';
    }
}
