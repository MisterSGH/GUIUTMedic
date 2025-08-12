
package guiutmedic.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**Clase encargada de la gestion (CRUD) de las citas del sistema
 *
 * @author santi
 */
public class CitaBD {

    
    
public boolean registrarCita(Connection conn, Cita cita) throws SQLException {
    String sql = "INSERT INTO cita (idPerfil, idPersonal, idMotivo, fecha, hora, observaciones, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, cita.getIdPerfil());
        ps.setInt(2, cita.getIdMedico());
        ps.setInt(3, cita.getIdMotivo());
        ps.setString(4, cita.getFecha());
        ps.setString(5, cita.getHora());
        ps.setString(6, cita.getObservaciones());
        ps.setString(7, "Programada");

        int filas = ps.executeUpdate();
        return filas > 0;
    }
    }

public boolean cancelarCita(Connection conn, int idCita, int idPerfil) throws SQLException {
    String sql = "UPDATE cita SET estado = 'Cancelada' WHERE idCita = ? AND idPerfil = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idCita);
        ps.setInt(2, idPerfil);
        int filas = ps.executeUpdate();
        return filas > 0;
    }
}

public boolean reagendarCita(Connection conn, int idCita, int idMotivo, String fecha, String hora) {
    try {
        String sql = "UPDATE cita SET "
                   + "fecha = ?, "
                   + "hora = ?, "
                   + "idMotivo = ?, "  // Actualizar idMotivo
                   + "estado = 'Reprogramada' "
                   + "WHERE idCita = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setInt(3, idMotivo);
            ps.setInt(4, idCita);
            
            return ps.executeUpdate() > 0;
        }
    } catch (SQLException ex) {
        System.err.println("Error al reagendar: " + ex.getMessage());
        return false;
    }
}








}
