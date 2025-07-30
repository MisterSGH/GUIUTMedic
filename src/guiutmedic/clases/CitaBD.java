
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
    String sql = "INSERT INTO cita (idPerfil, idMedico, idMotivo, fecha, hora, observaciones, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, cita.getIdPerfil());
        ps.setInt(2, cita.getIdMedico());
        ps.setInt(3, cita.getIdMotivo());
        ps.setString(4, cita.getFecha());
        ps.setString(5, cita.getHora());
        ps.setString(6, cita.getObservaciones());
        ps.setString(7, cita.getEstado());

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

public boolean reagendarCita(Connection conn, int idCita, int idPerfil, String nuevaFecha, String nuevaHora) throws SQLException {
    String sql = "UPDATE cita SET fecha = ?, hora = ?, estado = 'Reprogramada' WHERE idCita = ? AND idPerfil = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nuevaFecha);
        ps.setString(2, nuevaHora);
        ps.setInt(3, idCita);
        ps.setInt(4, idPerfil);

        int filas = ps.executeUpdate();
        return filas > 0;
    }
}








}
