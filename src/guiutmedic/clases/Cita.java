package guiutmedic.clases;

import java.util.Date;

/**
 * Clase Cita que conectará al paciente con usuarios.
 *
 * @author santi
 */
public class Cita {

    private int idCita;
    private int idPerfil; // Requerido para conocer el cuadro completo del paciente
    private int idPersonal;
    private String profesion;
    private int idMotivo;
    private String motivo;
    private String fechaMSQL = "//";
    private Date fecha;

    public String getFechaMSQL() {
        return fechaMSQL;
    }

    public void setFechaMSQL(String fechaMSQL) {
        this.fechaMSQL = fechaMSQL;
    }
    private String hora;
    private String observaciones;
    private String estado; // estado de la cita: programada, cancelada o atendida
    private Perfil perfil; // Objeto Perfil del paciente
    private Usuario paciente;

    // Constructor sin idCita (se genera en la BD)
    public Cita(int idPerfil, int idMedico, int idMotivo, String fecha, String hora, String observaciones, String estado) {
        this.idPerfil = idPerfil;
        this.idPersonal = idMedico;
        this.idMotivo = idMotivo;
        this.fechaMSQL = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    // Constructor sin idCita, idMedico
    public Cita(int idPerfil, String profesion, String motivo, String fecha, String hora) {
        this.idPerfil = idPerfil;
        this.profesion = profesion;
        this.motivo = motivo;
        this.fechaMSQL = fecha;
        this.hora = hora;
    }

    // Constructor completo para leer/consultar
    public Cita(int idCita, int idPerfil, int idMedico, int idMotivo, String fecha, String hora, String observaciones, String estado) {
        this.idCita = idCita;
        this.idPerfil = idPerfil;
        this.idPersonal = idMedico;
        this.idMotivo = idMotivo;
        this.fechaMSQL = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    // Constructor con motivo y perfil
    public Cita(int idCita, String fechaMSQL, String hora, int idMotivo, String estado, String motivo, Perfil perfil) {
        this.idCita = idCita;
        this.fechaMSQL = fechaMSQL;
        this.hora = hora;
        this.idMotivo = idMotivo;
        this.estado = estado;
        this.motivo = motivo;
        this.perfil = perfil;
        this.idPerfil = (perfil != null) ? perfil.getIdUsuario() : 0;
    }
// Constructor con idCita, fecha, hora, estado, motivo y perfil
public Cita(int idCita, String fechaMSQL, String hora, String estado, String motivo, Perfil perfil) {
    this.idCita = idCita;
    this.fechaMSQL = fechaMSQL;
    this.hora = hora;
    this.estado = estado;
    this.motivo = motivo;
    this.perfil = perfil;
    this.idPerfil = (perfil != null) ? perfil.getIdUsuario() : 0;
}

    // Constructor vacío
    public Cita() {}

    // Getters
    public int getIdCita() { return idCita; }
    public int getIdPerfil() { return idPerfil; }
    public int getIdPersonal() { return idPersonal; }
    public int getIdMotivo() { return idMotivo; }
    public String getFecha() { return fechaMSQL; }
    public String getHora() { return hora; }
    public String getObservaciones() { return observaciones; }
    public String getEstado() { return estado; }
    public String getProfesion() { return profesion; }
    public String getMotivo() { return motivo; }
    public Perfil getPerfil() { return perfil; }
    public Usuario getPaciente() { return paciente; }

    // Setters
    public void setIdCita(int idCita) { this.idCita = idCita; }
    public void setIdPerfil(int idPerfil) { this.idPerfil = idPerfil; }
    public void setPersonal(int idPersonal) { this.idPersonal = idPersonal; }
    public void setIdMotivo(int idMotivo) { this.idMotivo = idMotivo; }
    public void setFecha(String fechaMSQL) { this.fechaMSQL = fechaMSQL; }
    public void setHora(String hora) { this.hora = hora; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setProfesion(String profesion) { this.profesion = profesion; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setPerfil(Perfil perfil) { 
        this.perfil = perfil; 
        this.idPerfil = (perfil != null) ? perfil.getIdUsuario() : 0;
    }
    public void setPaciente(Usuario paciente) { this.paciente = paciente; }
        public Cita(int idCita, int idPaciente, int idMedico, Date fecha, String hora, String estado, String motivo) {
        this.idCita = idCita;
        this.idPerfil = idPaciente;
        this.idPersonal = idMedico;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.motivo = motivo;
    }
}
