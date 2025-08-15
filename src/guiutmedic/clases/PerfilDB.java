package guiutmedic.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class PerfilDB {

    public int obtenerIdPerfilPorIdUsuario(Connection conn, int idUsuario) throws SQLException {
        int idPerfil = -1;
        String sql = "SELECT idPerfil FROM perfil WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idPerfil = rs.getInt("idPerfil");
                }
            }
        }
        return idPerfil;
    }
    
    public int insertarPerfil(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "INSERT INTO perfil (nombre, apellido_paterno, apellido_materno, telefono, contactoEmergencia, alergias, peso, condicionMedica, foto, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  //se arma la sentencia de la consulta con parametros
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {   //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getUsuario()); // Se establecen los parametros 
            stmt.setString(2, "");
            stmt.setString(3, "");
            stmt.setString(4, "");
            stmt.setString(5, "");
            stmt.setString(6, "");
            stmt.setDouble(7, 0.0);
            stmt.setString(8, "");
            stmt.setString(9, "");
            stmt.setInt(10, idUsuario);
            stmt.executeUpdate();
            int filas = stmt.executeUpdate();
            return filas; // o algún otro valor como id generadoint filas = stmt.executeUpdate();
        }
    }
}
