package guiutmedic.clases;

/**
 * Clase Alumno, esta representa a un paciente(alumno) dentro de utmedic
 * @author santi
 */
public class Alumno {
    private int idAlumno;
    private String nombre;
    private String matricula;
    private String telefono;
    private String genero;
    private int idUsuario; // clave foránea

    // Constructor vacío
    public Alumno() {
        this.idAlumno = 0;
        this.nombre = "";
        this.matricula = "";
        this.telefono = "";
        this.genero = "";
        this.idUsuario = 0;
    }

    // Constructor con todos los campos
    public Alumno(int idAlumno, String nombre, String matricula, String telefono, String genero, int idUsuario) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
        this.genero = genero;
        this.idUsuario = idUsuario;
    }

    // Getters
    public int getIdAlumno() {
        return idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getGenero() {
        return genero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Setters
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
