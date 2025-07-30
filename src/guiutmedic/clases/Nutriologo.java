package guiutmedic.clases;

/**
 * *Clase con los atributos y metodos del Nut
 * @author santi
 */

public class Nutriologo extends PersonalSalud {
    private String certificacionNutricional;
    private String horarioAtencion;

    public Nutriologo() {}

    public Nutriologo(String idProfesional, String nombre, String correo, String telefono,
                      String certificacionNutricional, String horarioAtencion) {
        super(idProfesional, nombre, "Nutriólogo", correo, telefono);
        this.certificacionNutricional = certificacionNutricional;
        this.horarioAtencion = horarioAtencion;
    }

    public String getCertificacionNutricional() { return certificacionNutricional; }
    public void setCertificacionNutricional(String certificacionNutricional) { this.certificacionNutricional = certificacionNutricional; }

    public String getHorarioAtencion() { return horarioAtencion; }
    public void setHorarioAtencion(String horarioAtencion) { this.horarioAtencion = horarioAtencion; }

    public void evaluarIMC() {
        System.out.println("Evaluando IMC...");
    }

    public void crearPlanNutricional() {
        System.out.println("Creando plan nutricional...");
    }
}

