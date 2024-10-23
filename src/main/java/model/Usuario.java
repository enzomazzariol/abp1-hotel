package model;

import utils.Rol;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String password;
    private Rol rol;
    private String fechaRegistro;
    private String imagen;
    private boolean eliminado;

    public Usuario(int id, String nombre, String email, String password, Rol rol, String fechaRegistro, boolean eliminado, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
        this.imagen = imagen;
    }

    public Usuario(int id, String nombre, String email, String password, String imagen, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.imagen = imagen;
        this.rol = rol;
    }

    public Usuario(int id, String nombre, String email, String password, Rol rol, String fechaRegistro, boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.eliminado =eliminado;
    }

    public Usuario(int id, String nombre, String email, String password, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;

        // Obtener la fecha actual en el formato dd-MM-yyyy para fechaRegistro
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaRegistro = LocalDate.now().format(formatter);*/
    }

    public Usuario(String nombre, String email, String password, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", eliminado=" + eliminado +
                '}';
    }
}
