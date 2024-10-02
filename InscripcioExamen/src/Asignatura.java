import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private String nombre;
    private int añoEstudio;
    private List<Examen> examenes;

    public Asignatura(String nombre, int añoEstudio) {
        this.nombre = nombre;
        this.añoEstudio = añoEstudio;
        this.examenes = new ArrayList<>();
    }

    // Métodos para agregar examen
    public void agregarExamen(Examen examen) {
        examenes.add(examen);
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getAñoEstudio() { return añoEstudio; }
    public List<Examen> getExamenes() { return examenes; }
}

