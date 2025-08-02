package guiutmedic.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**27/06/2025
 *
 * @author santi
 */
public class ConexionBD {
    private String url="jdbc:mysql://localhost:3306/utmedic";
    private String user="root";
    private String password="";
    private Connection conn=null;


public Connection conexionDataBase()throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //conn = DriverManager.getConnection("jdbc:mysql localhost
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a la base de datos.");
            } catch (SQLException e) {
            System.err.println("Error al conectar:" + e.getMessage());
            }
            return conn;
        }
 public void  cerrarConexion (  Connection conn ) throws SQLException{
                      conn.close();  //se libera la conexión
        }
   
        
}
        
        
        