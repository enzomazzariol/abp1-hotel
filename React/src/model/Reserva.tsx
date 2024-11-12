export interface DetalleReservaActividad {
    nombreActividad: string;
    estadoReservaActividad: string;
    fechaReservaActividad: string;
    id?: number;
}

export interface DetalleReservaHabitacion {
    tipoDeHabitacion: string;
    estadoReserva: string;
    fechaReserva: string;
    fechas?: string;
    id?: number;
}