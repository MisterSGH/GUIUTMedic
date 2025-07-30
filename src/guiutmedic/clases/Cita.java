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
    private int idMotivo;
    private String fecha;
    private String hora;
    private String observaciones;
    private String estado; // ← NUEVO CAMPO

    // Constructor vacío
    public Cita() {}

    // Constructor sin idCita (se genera en la BD)
    public Cita(int idPerfil, int idMedico, int idMotivo, String fecha, String hora, String observaciones, String estado) {
        this.idPerfil = idPerfil;
        this.idMedico = idMedico;
        this.idMotivo = idMotivo;
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    // Constructor completo para leer/consultar
    public Cita(int idCita, int idPerfil, int idMedico, int idMotivo, String fecha, String hora, String observaciones, String estado) {
        this.idCita = idCita;
        this.idPerfil = idPerfil;
        this.idMedico = idMedico;
        this.idMotivo = idMotivo;
        this.fecha = fecha;
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
    { return fecha; }
    
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
    
    public void setFecha(String fecha) { this.fecha = fecha; }
    
    public void setHora(String hora) 
    { this.hora = hora; }
    
    public void setObservaciones(String observaciones)
    { this.observaciones = observaciones; }
    
    public void setEstado(String estado)
    { this.estado = estado; }
}
