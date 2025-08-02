/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiutmedic.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian
 */
public class PersonalSaludBD {
    // Insertar un nuevo personal
    public boolean insertarPersonal(PersonalSalud personal) {
        boolean resultado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "INSERT INTO personal_salud (nombre, profesion, correo, telefono, idUsuario) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, personal.getNombre());
            stmt.setString(2, personal.getProfesion());
            stmt.setString(3, personal.getCorreo());
            stmt.setString(4, personal.getTelefono());
            stmt.setInt(5, personal.getIdUsuario());

            int filas = stmt.executeUpdate();
            resultado = filas > 0;

        } catch (Exception e) {
            System.err.println("Error al insertar personal: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return resultado;
    }

    // Buscar personal por ID
    public PersonalSalud buscarPersonalPorId(int idUsuario) {
        PersonalSalud personal = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "SELECT * FROM personal_salud WHERE idUsuario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                personal = new PersonalSalud();
                personal.setIdPersonal(rs.getInt("idPersonal"));
                personal.setNombre(rs.getString("nombre"));
                personal.setProfesion(rs.getString("profesion"));
                personal.setCorreo(rs.getString("correo"));
                personal.setTelefono(rs.getString("telefono"));
                personal.setIdUsuario(rs.getInt("idUsuario"));
            }

        } catch (Exception e) {
            System.err.println("Error al buscar personal: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }

        return personal;
    }

    // Actualizar personal existente
    public boolean actualizarPersonal(PersonalSalud personal) {
        boolean resultado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "UPDATE personal_salud SET nombre=?, profesion=?, correo=?, telefono=?, idUsuario=? WHERE idPersonal=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, personal.getNombre());
            stmt.setString(2, personal.getProfesion());
            stmt.setString(3, personal.getCorreo());
            stmt.setString(4, personal.getTelefono());
            stmt.setInt(5, personal.getIdUsuario());
            stmt.setInt(6, personal.getIdPersonal());

            int filas = stmt.executeUpdate();
            resultado = filas > 0;

        } catch (Exception e) {
            System.err.println("Error al actualizar personal: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return resultado;
    }

    // Eliminar personal por ID
    public boolean eliminarPersonal(int idPersonal) {
        boolean resultado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "DELETE FROM personal_salud WHERE idPersonal = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPersonal);

            int filas = stmt.executeUpdate();
            resultado = filas > 0;

        } catch (Exception e) {
            System.err.println("Error al eliminar personal: " + e.getMessage());
        } finally {
            cerrarRecursos(stmt, conn);
        }

        return resultado;
    }

    // Obtener lista completa de personal
    public List<PersonalSalud> obtenerTodosPersonal() {
        List<PersonalSalud> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexionBD conexion = new ConexionBD();
            conn = conexion.conexionDataBase();

            String sql = "SELECT * FROM personal_salud";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PersonalSalud personal = new PersonalSalud();
                personal.setIdPersonal(rs.getInt("idPersonal"));
                personal.setNombre(rs.getString("nombre"));
                personal.setProfesion(rs.getString("profesion"));
                personal.setCorreo(rs.getString("correo"));
                personal.setTelefono(rs.getString("telefono"));
                personal.setIdUsuario(rs.getInt("idUsuario"));
                lista.add(personal);
            }

        } catch (Exception e) {
            System.err.println("Error al obtener personal: " + e.getMessage());
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

