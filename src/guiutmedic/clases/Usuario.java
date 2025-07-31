/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiutmedic.clases;

/**
 *
 * @author 
 */
public class Usuario {
    
    private int idUsuario;
    private String matricula;
    private String password;
    private String usuario;
    private String rol;
    
    public Usuario(String matricula, String password, String usuario, String rol){
          this.idUsuario = 0;
        this.matricula=matricula;
        this.password =password;
        this.usuario = usuario;
        this.rol = rol;
    }

     public Usuario(){
           this.idUsuario = 0;
        this.matricula="";
        this.password ="";
        this.usuario = "";
        this.rol = "";
     }
    
    // Constructor
    public Usuario(int idUsuario, String matricula, String password, String usuario, String rol) {
        this.idUsuario = idUsuario;
        this.matricula=matricula;
        this.password = password;
        this.usuario = usuario;
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

    public void setUsuario(String email) {
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
