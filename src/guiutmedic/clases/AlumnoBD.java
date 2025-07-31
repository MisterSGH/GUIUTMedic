package guiutmedic.clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoBD {
    
    // Insertar un nuevo alumno
    public boolean insertarAlumno(Alumno alumno) {
        boolean resultado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "INSERT INTO alumno (nombre, matricula, telefono, genero, idUsuario) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getMatricula());
            stmt.setString(3, alumno.getTelefono());
            stmt.setString(4, alumno.getGenero());
            stmt.setInt(5, alumno.getIdUsuario());

            int filas = stmt.executeUpdate();
            resultado = filas > 0;

        } catch (Exception e) {
            System.err.println("Error al insertar alumno: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return resultado;
    }

    // Buscar alumno por ID
    public Alumno buscarAlumnoPorId(int idAlumno) {
        Alumno alumno = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "SELECT * FROM alumno WHERE idAlumno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAlumno);
            rs = stmt.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setTelefono(rs.getString("telefono"));
                alumno.setGenero(rs.getString("genero"));
                alumno.setIdUsuario(rs.getInt("idUsuario"));
            }

        } catch (Exception e) {
            System.err.println("Error al buscar alumno: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }

        return alumno;
    }

    // Actualizar un alumno existente
    public boolean actualizarAlumno(Alumno alumno) {
        boolean resultado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "UPDATE alumno SET nombre=?, matricula=?, telefono=?, genero=?, idUsuario=? WHERE idAlumno=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getMatricula());
            stmt.setString(3, alumno.getTelefono());
            stmt.setString(4, alumno.getGenero());
            stmt.setInt(5, alumno.getIdUsuario());
            stmt.setInt(6, alumno.getIdAlumno());

            int filas = stmt.executeUpdate();
            resultado = filas > 0;

        } catch (Exception e) {
            System.err.println("Error al actualizar alumno: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return resultado;
    }

    // Eliminar alumno por ID
    public boolean eliminarAlumno(int idAlumno) {
        boolean resultado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "DELETE FROM alumno WHERE idAlumno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAlumno);

            int filas = stmt.executeUpdate();
            resultado = filas > 0;

        } catch (Exception e) {
            System.err.println("Error al eliminar alumno: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return resultado;
    }

    // Obtener lista completa de alumnos
    public List<Alumno> obtenerTodosLosAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "SELECT * FROM alumno";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setTelefono(rs.getString("telefono"));
                alumno.setGenero(rs.getString("genero"));
                alumno.setIdUsuario(rs.getInt("idUsuario"));
                lista.add(alumno);
            }

        } catch (Exception e) {
            System.err.println("Error al obtener alumnos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }

        return lista;
    }
    
    // Métodos de cierre
    private void cerrarRecursos(PreparedStatement stmt, Connection conn) {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

    private void cerrarRecursos(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            cerrarRecursos(stmt, conn);
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
