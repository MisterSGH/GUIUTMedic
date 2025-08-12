package guiutmedic.clases;

/**
 * Clase Cita que conectará al paciente con usuarios.
 *
 * @author santi
 */
public class Cita {
    private int idCita;
    private int idPerfil; // Requerido para conocer el cuadro completo del paciente
    private int idMedico;
    private String profesion;
    private int idMotivo;
    private String motivo;
    private String fechaMSQL="//";
    private String hora;
    private String observaciones;
    private String estado; // ← NUEVO CAMPO

    // Constructor vacío
    public Cita(int idCita1, String fecha, String hora1, int idMotivo1) {}

    // Constructor sin idCita (se genera en la BD)
    public Cita(int idPerfil, int idMedico, int idMotivo, String fecha, String hora, String observaciones, String estado) {
        this.idPerfil = idPerfil;
        this.idMedico = idMedico;
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
        this.idMedico = idMedico;
        this.idMotivo = idMotivo;
        this.fechaMSQL = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    // Getters
    public int getIdCita()
    { return idCita; }
    
    public int getIdPerfil() 
    { return idPerfil; }
    
    public int getIdMedico() 
    { return idMedico; }
    
    public int getIdMotivo() 
    { return idMotivo; }
    
    public String getFecha()
    { return fechaMSQL; }
    
    public String getHora() 
    { return hora; }
    
    public String getObservaciones() 
    { return observaciones; }
    
    public String getEstado()
    { return estado; }

    //setters
    public void setIdCita(int idCita)
    { this.idCita = idCita; }
    
    public void setIdPerfil(int idPerfil) 
    { this.idPerfil = idPerfil; }
    
    public void setIdMedico(int idMedico)
    { this.idMedico = idMedico; }
    
    public void setIdMotivo(int idMotivo)
    { this.idMotivo = idMotivo; }
    
    public void setFecha(String fecha) { this.fechaMSQL = fecha; }
    
    public void setHora(String hora) 
    { this.hora = hora; }
    
    public void setObservaciones(String observaciones)
    { this.observaciones = observaciones; }
    
    public void setEstado(String estado)
    { this.estado = estado; }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public Cita() {
    
}
}
