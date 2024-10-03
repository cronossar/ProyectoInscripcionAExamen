package Servicios;

import java.time.LocalDateTime;

public class CronoMesaExamen {
    private int idMesa;
    private Asignatura asignatura;
    private LocalDateTime fechaHora;

    public CronoMesaExamen(int idMesa, Asignatura asignatura, LocalDateTime fechaHora) {
        this.idMesa = idMesa;
        this.asignatura = asignatura;
        this.fechaHora = fechaHora;
    }

    // Getters y Setters

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}