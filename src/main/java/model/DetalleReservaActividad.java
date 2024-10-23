package model;

public class DetalleReservaActividad {
    private String nombreActividad;
    private String estadoReservaActividad;
    private String fechaReservaActividad;
    private String fechasReservaHabitacion;
    private int id;

    public DetalleReservaActividad(String nombreActividad, String estadoReservaActividad, String fechaReservaActividad, String fechasReservaHabitacion, int id) {
        this.nombreActividad = nombreActividad;
        this.estadoReservaActividad = estadoReservaActividad;
        this.fechaReservaActividad = fechaReservaActividad;
        this.fechasReservaHabitacion = fechasReservaHabitacion;
        this.id = id;
    }

    public DetalleReservaActividad(String nombreActividad, String estadoReservaActividad, String fechaReservaActividad, String fechasReservaHabitacion) {
        this.nombreActividad = nombreActividad;
        this.estadoReservaActividad = estadoReservaActividad;
        this.fechaReservaActividad = fechaReservaActividad;
        this.fechasReservaHabitacion = fechasReservaHabitacion;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getEstadoReservaActividad() {
        return estadoReservaActividad;
    }

    public void setEstadoReservaActividad(String estadoReservaActividad) {
        this.estadoReservaActividad = estadoReservaActividad;
    }

    public String getFechaReservaActividad() {
        return fechaReservaActividad;
    }

    public void setFechaReservaActividad(String fechaReservaActividad) {
        this.fechaReservaActividad = fechaReservaActividad;
    }

    public String getFechasReservaHabitacion() {
        return fechasReservaHabitacion;
    }

    public void setFechasReservaHabitacion(String fechasReservaHabitacion) {
        this.fechasReservaHabitacion = fechasReservaHabitacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
