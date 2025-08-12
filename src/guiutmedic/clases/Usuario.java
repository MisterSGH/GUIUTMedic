package guiutmedic.clases;

/**
 *
 * @author 
 */
public class Usuario {
    
    private int idUsuario;
    private int idPaciente;
    private int idPerfil;
    private String matricula;
    private String password;
    private String usuario;
    private String rol;

    public Usuario() {
        this.idUsuario = 0;
        this.idPaciente = 0;
        this.idPerfil = 0;
        this.usuario = "";
        this.password = "";
        this.matricula = "";
        this.rol = "";
    }

    public Usuario(String matricula, String password, String usuario, String rol) {
        this.idUsuario = 0;
        this.idPaciente = 0;
        this.idPerfil = 0;
        this.usuario = usuario;
        this.password = password;
        this.matricula = matricula;
        this.rol = rol;
    }

    // Constructor
    public Usuario(int idUsuario, int idPaciente, int idPerfil, String matricula, String usuario, String password, String rol) {
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
        this.idPerfil = idPerfil;
        this.usuario = usuario;
        this.password = password;
        this.matricula = matricula;
        this.rol = rol;
    }

    // Setters
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public int getIdPaciente() {
        return idPaciente;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public String getMatricula() {
        return matricula;
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