package guiutmedic.clases;

/**
 * clase padre de medico,psiclogo y nutriologo
 * @author santi
 */

public class PersonalSalud {
    private int idPersonal;
    private int idUsuario;
    private String nombre;
    private String profesion; // Sea Médico,Nutriólogo o Psicólogo
    private String correo;
    private String telefono;

    private boolean activo; // para activar/desactivar cuenta

    // Constructor vacío preguntar
    public PersonalSalud() {}
    
    // Constructor 
    public PersonalSalud(int idPersonal, int idUsuario, String nombre, String profesion,
                         String correo, String telefono) {
        this.idPersonal = idPersonal;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.profesion = profesion;
        this.correo = correo;
        this.telefono = telefono;
        this.activo = true;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Getters
    
     public int getIdPersonal() 
     { return idPersonal; }
     
     public String getNombre() 
     { return nombre; }
     
     public String getProfesion() 
     { return profesion; }
     
     public String getCorreo() 
     { return correo; }
     
     public String getTelefono() 
     { return telefono; }
     
  //Setters  
     
    public void setIdPersonal(int idPersonal) 
    { this.idPersonal = idPersonal; }
    
    public void setNombre(String nombre) 
    { this.nombre = nombre; }
    
    public void setProfesion(String profesion) 
    { this.profesion = profesion; }
    
    public void setCorreo(String correo) 
    { this.correo = correo; }
    
    public void setTelefono(String telefono) 
    { this.telefono = telefono; }

    public boolean isActivo() { return activo; }

    //se usara para definir como disponibel al profesionista
    public void activarUsuario() {
        this.activo = true;
    }
//se usara por si el profesionista no esta disponible
    public void desactivarUsuario() {
        this.activo = false;
    }

    public String mostrarInfo() {
        return "ID: " + idPersonal + "\nNombre: " + nombre + "\nProfesión: " + profesion +
               "\nCorreo: " + correo + "\nTeléfono: " + telefono + "\nActivo: " + activo;
    }
}
