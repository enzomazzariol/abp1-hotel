class Habitacion {
    constructor(
        public id: number,
        public tipoHabitacion: string,
        public imagen: string,
        public precio: number,
        public estado: string,
        public eliminado: boolean
    ){}
}

export default Habitacion