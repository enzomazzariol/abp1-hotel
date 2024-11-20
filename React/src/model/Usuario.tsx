class Usuario {
    constructor(
        public id: number = 0,
        public nombre: string = "",
        public correo: string = "",
        public contrasena: string = "",
        public fechaNacimiento: Date,
        public rol: string = "usuario", 
        public imagen: string = "",
        public fechaRegistro: Date = new Date(),
        public eliminado: boolean = false
    ) { }
}

export default Usuario;
