import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private int idAsignatura;
    private String nombreAsignatura;
    private int añoEstudio;

    public Asignatura(int idAsignatura, String nombreAsignatura, int añoEstudio) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.añoEstudio = añoEstudio;
    }

    // Getters y Setters

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public int getAñoEstudio() {
        return añoEstudio;
    }

    public void setAñoEstudio(int añoEstudio) {
        this.añoEstudio = añoEstudio;
    }
}

