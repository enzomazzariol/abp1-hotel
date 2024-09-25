package Model;

import java.util.Arrays;

public class Actividad {
 private int id;
 private String nombre_actividad;
 private String descripcion;
 private String imagenes;
 private double precio;
 private int cupo;
 private String fecha_actividad;

    public Actividad(int id, String nombre_actividad, String descripcion, String imagenes, double precio, int cupo, String fecha_actividad) {
        this.id = id;
        this.nombre_actividad = nombre_actividad;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.precio = precio;
        this.cupo = cupo;
        this.fecha_actividad = fecha_actividad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public void setFecha_actividad(String fecha_actividad) {
        this.fecha_actividad = fecha_actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public String getImagenes() {
        return imagenes;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCupo() {
        return cupo;
    }

    public String getFecha_actividad() {
        return fecha_actividad;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre_actividad='" + nombre_actividad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenes=" + imagenes +
                ", precio=" + precio +
                ", cupo=" + cupo +
                ", fecha_actividad='" + fecha_actividad + '\'' +
                '}';
    }
}
