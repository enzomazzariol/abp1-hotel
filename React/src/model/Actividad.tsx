class Actividad {
    constructor(
        public id: number = 0,
        public nombre: string = "",
        public descripcion: string = "",
        public imagen: any,
        public precio: number = 0,
        public cupo: number = 0,
        public fecha_actividad: Date,
        public eliminado: boolean = false
    ){ }
}

export default Actividad