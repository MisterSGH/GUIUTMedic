
package guiutmedic.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Adrian
 */
public class UsuarioBD {
    
    
    public int insertarUsuario(Connection conn, Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (matricula, usuario, password, rol) VALUES (?, ?, ?, ?)";  //se arma la sentencia de la consulta con parametros
        //  Statement.RETURN_GENERATED_KEYS --> constante que  regresa el id generado despues de insertar
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getUsuario());    // Se establecen los parametros 
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getUsuario());
            stmt.setString(4, usuario.getRol());
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {// se obteniene el idGenerado
                    if (rs.next()) {
                        return rs.getInt(1);   
                    }
                }
            }
        }
        return -1;
    }

    public void insertarCliente(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "INSERT INTO cliente (nombre, beca, porcentaje, idusuario) VALUES (?, ?, ?, ?)"; //se arma la sentencia de la consulta con parametros
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {   //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getUsuario());   // Se establecen los parametros 
            stmt.setBoolean(2, false);
            stmt.setInt(3, 0);
            stmt.setInt(4, idUsuario);
            stmt.executeUpdate();
        }
    }

    public void insertarEmpleado(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "INSERT INTO empleado (nombre, turno, idusuario) VALUES (?, ?, ?)";  //se arma la sentencia de la consulta con parametros
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {   //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getUsuario()); // Se establecen los parametros 
            stmt.setString(2, "");
            stmt.setInt(3, idUsuario);
            stmt.executeUpdate();
        }
    }

    public boolean eliminarUsuario(Connection conn, int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?"; //se arma la sentencia de la consulta
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {  //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setInt(1, idUsuario); 
            return stmt.executeUpdate() > 0;  //se ejecuta la consulta y se recibe en el result set --> rs
        }
    }

    public boolean actualizarUsuario(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "UPDATE usuario SET matricula = ?, password = ?, usuario = ?, rol = ? WHERE idUsuario = ?"; //se arma la sentencia de la consulta
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {  //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getMatricula()); // Se establecen los parametros 
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getUsuario());
            stmt.setString(4, usuario.getRol());
            stmt.setInt(5, idUsuario);
            return stmt.executeUpdate() > 0;  //se ejecuta la consulta y se recibe en el result set --> rs
        }
    }
    
    public ResultSet consultarUsuarioLogin(Connection conn, String usuario, String password) throws SQLException {
        String sql = "SELECT idUsuario, usuario, password, rol, matricula FROM usuario WHERE usuario = ? AND password = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, usuario);
    stmt.setString(2, password);

    System.out.println(sql);
    System.out.println(usuario + password);

    ResultSet rs = stmt.executeQuery();
    return rs;
}

    

}
