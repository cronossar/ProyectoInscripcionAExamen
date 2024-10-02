public class Inscripcion {
    private Alumno alumno;
    private Examen examen;

    public Inscripcion(Alumno alumno, Examen examen) {
        this.alumno = alumno;
        this.examen = examen;
    }

    public void imprimirInscripcion() {
        System.out.println("Inscripción de: " + alumno.getNombre() + " " + alumno.getApellido() +
                " para el examen de " + examen.getAsignatura().getNombre() + " el " + examen.getFecha());
    }

    public String generarArchivoTexto() {
        return "Inscripción: " + alumno.getNombre() + " " + alumno.getApellido() +
                ", Examen: " + examen.getAsignatura().getNombre() +
                ", Fecha: " + examen.getFecha();
    }
}

