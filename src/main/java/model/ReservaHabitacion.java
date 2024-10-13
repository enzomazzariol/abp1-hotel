package model;

import utils.Estado;

public class ReservaHabitacion extends Reservas{
    private int idHabitacion;
    private String fechaEntrada;
    private String fechaSalida;

    public ReservaHabitacion(int id, int idUsuario, Estado estado, String fechaReserva, int idHabitacion, String fechaEntrada, String fechaSalida) {
        super(id, idUsuario, estado, fechaReserva);
        this.idHabitacion = idHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public ReservaHabitacion(int idUsuario, Estado estado, String fechaReserva, int idHabitacion, String fechaEntrada, String fechaSalida) {
        super(idUsuario, estado, fechaReserva);
        this.idHabitacion = idHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        return "ReservaHabitacion{" +
                "idHabitacion=" + idHabitacion +
                ", fechaEntrada='" + fechaEntrada + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", idUsuario=" + idUsuario +
                ", estado=" + estado +
                ", fechaReserva='" + fechaReserva + '\'' +
                '}';
    }
}
