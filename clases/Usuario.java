package guiutmedic.clases;

/**
 *
 * @author 
 */
public class Usuario {
    private int idPerfil;

public int getIdPerfil() {
    return idPerfil;
}

public void setIdPerfil(int idPerfil) {
    this.idPerfil = idPerfil;
}

    private int idUsuario;
    private String matricula;
    private String password;
    private String usuario;
    private String rol;
    private String profesion; // Agregar este campo

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    
    public Usuario(String matricula, String password, String usuario, String rol){
          this.idUsuario = 0;
        this.usuario = usuario;
        this.password =password;
        this.matricula=matricula;
        this.rol = rol;
    }

     public Usuario(){
        this.idUsuario = 0;
        this.usuario = "";
        this.password ="";
        this.matricula="";
        this.rol = "";
     }
    
    // Constructor
    public Usuario(int idUsuario, String matricula, String usuario, String password, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.matricula=matricula;
        this.rol = rol;
    }

    //Setters  
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String usuario) {
        this.matricula = usuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getRol() {
        return rol;
    }

}
