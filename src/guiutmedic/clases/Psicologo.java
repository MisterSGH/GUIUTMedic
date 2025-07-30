package guiutmedic.clases;

/**
 * *Clase con los atributos y metodos del psicologo
 * @author santi
 */

public class Psicologo extends PersonalSalud {
    private String tipoTerapia;
    private String frecuenciaSesiones;
    private String horarioDisponible;

    public Psicologo() {}

    public Psicologo(String idProfesional, String nombre, String correo, String telefono,
                     String tipoTerapia, String frecuenciaSesiones, String horarioDisponible) {
        super(idProfesional, nombre, "Psicólogo", correo, telefono);
        this.tipoTerapia = tipoTerapia;
        this.frecuenciaSesiones = frecuenciaSesiones;
        this.horarioDisponible = horarioDisponible;
    }

    public String getTipoTerapia() { return tipoTerapia; }
    public void setTipoTerapia(String tipoTerapia) { this.tipoTerapia = tipoTerapia; }

    public String getFrecuenciaSesiones() { return frecuenciaSesiones; }
    public void setFrecuenciaSesiones(String frecuenciaSesiones) { this.frecuenciaSesiones = frecuenciaSesiones; }

    public String getHorarioDisponible() { return horarioDisponible; }
    public void setHorarioDisponible(String horarioDisponible) { this.horarioDisponible = horarioDisponible; }

    public void realizarEvaluacionPsicologica() {
        System.out.println("Realizando evaluación psicológica...");
    }

    public void darTerapiaIndividual() {
        System.out.println("Dando terapia individual...");
    }
}
