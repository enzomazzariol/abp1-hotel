package Model;

public class ReservaHabitacion extends Reservas{
    private int idHabitacion;
    private String fechaEntrada;
    private String fechaSalida;

    public ReservaHabitacion(int idUsuario, int idHabitacion, String estado, String fechaReserva, String fechaEntrada, String fechaSalida) {
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
}
