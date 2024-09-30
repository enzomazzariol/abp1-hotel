package Model;

import Utils.Estado;

public class ReservaActividad extends Reservas{

    private int idActividad;

    public ReservaActividad(int id, int idUsuario, Estado estado, String fechaReserva, boolean eliminado, int idActividad) {
        super(id, idUsuario, estado, fechaReserva, eliminado);
        this.idActividad = idActividad;
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
                ", idActividad=" + idActividad +
                ", idUsuario=" + idUsuario +
                ", estado=" + estado +
                ", fechaReserva='" + fechaReserva + '\'' +
                '}';
    }
}
