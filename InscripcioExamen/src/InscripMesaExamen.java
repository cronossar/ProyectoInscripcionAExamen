public class InscripMesaExamen {
    private Alumno alumno;
    private CronoMesaExamen cronoMesa;

    public InscripMesaExamen(Alumno alumno, CronoMesaExamen cronoMesa) {
        this.alumno = alumno;
        this.cronoMesa = cronoMesa;
    }

    // Getters y Setters

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public CronoMesaExamen getCronoMesa() {
        return cronoMesa;
    }

    public void setCronoMesa(CronoMesaExamen cronoMesa) {
        this.cronoMesa = cronoMesa;
    }
}


