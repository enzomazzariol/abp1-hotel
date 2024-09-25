package Model;

public class Reservas {
    protected int idUsuario;
    protected String estado;
    protected String fechaReserva;

    public Reservas(int idUsuario, String estado, String fechaReserva) {
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.fechaReserva = fechaReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
