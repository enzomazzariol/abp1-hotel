package model;

public class DetalleReservaHabitacion {
    private String tipoDeHabitacion;
    private String estadoReserva;
    private String fechaReserva;
    private String fechas;
    private int id;

    public DetalleReservaHabitacion(String tipoDeHabitacion, String estadoReserva, String fechaReserva, String fechas, int id) {
        this.tipoDeHabitacion = tipoDeHabitacion;
        this.estadoReserva = estadoReserva;
        this.fechaReserva = fechaReserva;
        this.fechas = fechas;
        this.id = id;
    }

    public String getTipoDeHabitacion() {
        return tipoDeHabitacion;
    }

    public void setTipoDeHabitacion(String tipoDeHabitacion) {
        this.tipoDeHabitacion = tipoDeHabitacion;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
