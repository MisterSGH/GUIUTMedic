package guiutmedic.clases;

/**
 * clase padre de medico,psiclogo y nutriologo
 * @author santi
 */

public class PersonalSalud {
    private String idProfesional;
    private String nombre;
    private String profesion; // Sea Médico,Nutriólogo o Psicólogo
    private String correo;
    private String telefono;

    private boolean activo; // para activar/desactivar cuenta

    // Constructor vacío preguntar
    public PersonalSalud() {}
    
    // Constructor 
    public PersonalSalud(String idProfesional, String nombre, String profesion,
                         String correo, String telefono) {
        this.idProfesional = idProfesional;
        this.nombre = nombre;
        this.profesion = profesion;
        this.correo = correo;
        this.telefono = telefono;
        this.activo = true;
    }

    // Getters
    
     public String getIdProfesional() 
     { return idProfesional; }
     
     public String getNombre() 
     { return nombre; }
     
     public String getProfesion() 
     { return profesion; }
     
     public String getCorreo() 
     { return correo; }
     
     public String getTelefono() 
     { return telefono; }
     
  //Setters  
     
    public void setIdProfesional(String idProfesional) 
    { this.idProfesional = idProfesional; }
    
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
        return "ID: " + idProfesional + "\nNombre: " + nombre + "\nProfesión: " + profesion +
               "\nCorreo: " + correo + "\nTeléfono: " + telefono + "\nActivo: " + activo;
    }
}
