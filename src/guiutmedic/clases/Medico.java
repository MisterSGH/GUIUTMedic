package guiutmedic.clases;

/**
 *Clase con los atributos y metodos del medico
 * @author santi
 */

public class Medico extends PersonalSalud {
    private String tipoMedico; // "General", "Especialista", etc.
    private String horarioAtencion;

    public Medico() {}

    public Medico(String idProfesional, String nombre, String correo, String telefono,
                  String tipoMedico, String horarioAtencion) {
        super(idProfesional, nombre, "Médico", correo, telefono);
        this.tipoMedico = tipoMedico;
        this.horarioAtencion = horarioAtencion;
    }

    public String getTipoMedico() { return tipoMedico; }
    public void setTipoMedico(String tipoMedico) { this.tipoMedico = tipoMedico; }

    public String getHorarioAtencion() { return horarioAtencion; }
    public void setHorarioAtencion(String horarioAtencion) { this.horarioAtencion = horarioAtencion; }

    public void realizarChequeoGeneral() {
        System.out.println("Realizando chequeo general...");
    }

    public void prescribirMedicamento(String medicamento) {
        System.out.println("Prescribiendo: " + medicamento);
    }
}


