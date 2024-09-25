package Model;

import Utils.Estado;

public class ReservaActividad extends Reservas{

    private int idActividad;

    public ReservaActividad(int idUsuario, int idActividad, Estado estado, String fechaReserva) {
        super(idUsuario, estado, fechaReserva);
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
