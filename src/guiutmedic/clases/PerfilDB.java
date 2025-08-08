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
}
//int idPerfil  = perfilDB.obtenerIdPerfilPorIdUsuario(conn, objetoMenuP.getIdUsuario());