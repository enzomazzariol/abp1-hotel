package Model;

public class ReservaActividad extends Reservas{
    private int idReservaActividad;
    private int idActividad;

    public ReservaActividad(int idUsuario, String estado, String fechaReserva, int idReservaActividad, int idActividad) {
        super(idUsuario, estado, fechaReserva);
        this.idReservaActividad = idReservaActividad;
        this.idActividad = idActividad;
    }

    public int getIdReservaActividad() {
        return idReservaActividad;
    }

    public void setIdReservaActividad(int idReservaActividad) {
        this.idReservaActividad = idReservaActividad;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
}
