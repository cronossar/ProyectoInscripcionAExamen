package Servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Examen {
    private Asignatura asignatura;
    private Date fecha;
    private List<Alumno> inscriptos;
    private List<Docente> docentes;

    public Examen(Asignatura asignatura, Date fecha) {
        this.asignatura = asignatura;
        this.fecha = fecha;
        this.inscriptos = new ArrayList<>();
        this.docentes = new ArrayList<>();
    }

    public void inscribirAlumno(Alumno alumno) {
        inscriptos.add(alumno);
    }

    public void agregarDocente(Docente docente) {
        docentes.add(docente);
    }

    // Getters
    public Asignatura getAsignatura() { return asignatura; }
    public Date getFecha() { return fecha; }
    public List<Alumno> getInscriptos() { return inscriptos; }
    public List<Docente> getDocentes() { return docentes; }
}

