package Model;

import Utils.Estado;

public class ReservaActividad extends Reservas{

    private int idActividad;

    public ReservaActividad(int id, int idUsuario, int idActividad, Estado estado, String fechaReserva, boolean eliminado) {
        super(id, idUsuario, estado, fechaReserva);
        this.idActividad = idActividad;
        this.eliminado = eliminado;
    }

    public ReservaActividad(int idUsuario, int idActividad, Estado estado){
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
    }

    public ReservaActividad(int id, Estado estado, String fechaReserva){
        this.id = id;
        this.estado = estado;
        this.fechaReserva = fechaReserva;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public String toString() {
        return "ReservaActividad{" +
                "idActividad=" + idActividad +
                ", id=" + id +
                ", idUsuario=" + idUsuario +
                ", estado=" + estado +
                ", fechaReserva='" + fechaReserva + '\'' +
                ", eliminado=" + eliminado +
                '}';
    }
}
