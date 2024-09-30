package Model;

import Utils.Estado;

public class Reservas {
    protected int id;
    protected int idUsuario;
    protected Estado estado;
    protected String fechaReserva;
    protected boolean eliminado;

    public Reservas(int id, int idUsuario, Estado estado, String fechaReserva, boolean eliminado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.fechaReserva = fechaReserva;
        this.eliminado = eliminado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
