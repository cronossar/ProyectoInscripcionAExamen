import java.util.List;

public class Alumno {
    private String nombre;
    private String apellido;
    private String dni;
    private List<Asignatura> asignaturasAdeudadas;

    public Alumno(String nombre, String apellido, String dni, List<Asignatura> asignaturasAdeudadas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.asignaturasAdeudadas = asignaturasAdeudadas;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public List<Asignatura> getAsignaturasAdeudadas() { return asignaturasAdeudadas; }
}

