package Servicios;

public class Docente {
    private int idDocente;
    private String apellido;
    private String nombre;
    private String dni;

    public Docente(int idDocente, String apellido, String nombre, String dni) {
        this.idDocente = idDocente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
    }

    // Getters y Setters

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}

