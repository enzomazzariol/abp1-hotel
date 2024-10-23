package model;

import java.sql.Date;
import java.sql.Timestamp;

public class ReservaGeneral {
    private String usuario;
    private String tipoReserva;
    private String detalleReserva;
    private String estado;
    private String fechaEntrada;
    private String fechaSalida;
    private Timestamp fechaReserva;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public String getDetalleReserva() {
        return detalleReserva;
    }

    public void setDetalleReserva(String detalleReserva) {
        this.detalleReserva = detalleReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    @Override
    public String toString() {
        return "ReservaGeneral{" +
                "usuario='" + usuario + '\'' +
                ", tipoReserva='" + tipoReserva + '\'' +
                ", detalleReserva='" + detalleReserva + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", fechaReserva=" + fechaReserva +
                '}';
    }
}
