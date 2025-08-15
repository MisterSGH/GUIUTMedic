
package guiutmedic.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**Clase encargada de la gestion (CRUD) de las citas del sistema
 *
 * @author santi
 */
public class CitaBD {

    
    
public int registrarCita(Connection conn, Cita cita) throws SQLException {
        String sql = "INSERT INTO cita (idPerfil, idPersonal, idMotivo, fecha, hora, observaciones, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int idCitaGenerada = -1; // Inicializamos la variable con un valor por defecto

        // Modificamos el PreparedStatement para que devuelva las claves generadas
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cita.getIdPerfil());
            ps.setInt(2, cita.getIdPersonal());
            ps.setInt(3, cita.getIdMotivo());
            ps.setString(4, cita.getFecha());
            ps.setString(5, cita.getHora());
            ps.setString(6, cita.getObservaciones());
            ps.setString(7, "Programada");

            ps.executeUpdate();

            // Obtenemos el ResultSet de las claves generadas
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                // Si hay una fila, significa que se generó una clave
                idCitaGenerada = rs.getInt(1);
            }
        }
        return idCitaGenerada; // Devolvemos el ID de la cita
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
