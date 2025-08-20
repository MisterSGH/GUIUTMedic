/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package guiutmedic.formularios;


import guiutmedic.clases.ConexionBD;
import guiutmedic.clases.PersonalSalud;
import guiutmedic.clases.PersonalSaludBD;
import guiutmedic.clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IdiomasB102
 */
public class frmGestionProfesionistas extends javax.swing.JInternalFrame {

    ConexionBD objetoConexionBD = new ConexionBD();
    Connection conn;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String datoBuscar = "";
    String dato = "";
    
    public frmGestionProfesionistas(Usuario usuario) throws ClassNotFoundException {
        initComponents();
        llenarDataTable();
    }
    
    public final void llenarDataTable() throws ClassNotFoundException {
        ArrayList<PersonalSalud> arregloPersonal = new ArrayList<>();
        String sql = "SELECT idPersonal, nombre, profesion, correo, telefono, idUsuario FROM personal_salud";

        try {
            conn = objetoConexionBD.conexionDataBase();
            
            
            if (dato != "") {
                sql += " where nombre='"+dato+"'";
                
            }
            
            
            // Preparar y asignar parámetros si es necesario
            stmt = conn.prepareStatement(sql);

//                stmt.setString(1, dato);
//            if (rbdUsuario.isSelected()) {
//            }
            System.out.println("Consulta SQL: " + sql);
            System.out.println("Dato: " + dato);
            
            rs = stmt.executeQuery();

            // Verificar si hay resultados
            boolean tieneDatos = false;
            while (rs.next()) {
                tieneDatos = true;
                PersonalSalud personal = new PersonalSalud(
                        rs.getInt("idPersonal"),
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("profesion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
                arregloPersonal.add(personal);
            }

            if (!tieneDatos) {
                JOptionPane.showMessageDialog(null, "¡Datos no localizados!");
            }

            // Actualizar tabla
            DefaultTableModel modelo = (DefaultTableModel) tblPersonal.getModel();
            modelo.setRowCount(0); // Limpiar tabla
            for (PersonalSalud item : arregloPersonal) {
                Object[] fila = {
                    item.getIdPersonal(),
                    item.getIdUsuario(),
                    item.getNombre(),
                    item.getProfesion(),
                    item.getCorreo(),
                    item.getTelefono()
                };
                modelo.addRow(fila);
            }

            tblPersonal.setModel(modelo);

            // Seleccionar la primera fila si hay datos
            if (tblPersonal.getRowCount() > 0) {
                tblPersonal.setRowSelectionInterval(0, 0);
                llenarTextBox(); // Llenar los textbox
            }

        } catch (SQLException ex) {
            Logger.getLogger(frmGestionProfesionistas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos de forma segura
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmGestionProfesionistas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void llenarTextBox() {
        int fila = tblPersonal.getSelectedRow();

        if (fila >= 0) {
            txtIdPersonal.setText(tblPersonal.getValueAt(fila, 0).toString());
            txtIdUsuario.setText(tblPersonal.getValueAt(fila, 1).toString());
            txtNombre.setText(tblPersonal.getValueAt(fila, 2).toString());
            cobProfesion.setSelectedItem(tblPersonal.getValueAt(fila, 3).toString());
            txtCorreo.setText(tblPersonal.getValueAt(fila, 4).toString());
            txtTelefono.setText(tblPersonal.getValueAt(fila, 5).toString());
            
        }

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgBecado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPanel = new javax.swing.JScrollPane();
        tblPersonal = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblIdPersonal = new javax.swing.JLabel();
        lblIdUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblProfesion = new javax.swing.JLabel();
        txtIdPersonal = new javax.swing.JTextField();
        txtIdUsuario = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnVerTodos = new javax.swing.JButton();
        cobProfesion = new javax.swing.JComboBox<>();
        lblTelefono = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion de Personal");
        setToolTipText("");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados del sistema"));

        tblPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Personal", "ID Usuario", "Nombre", "Profesión", "Correo", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPersonalMouseClicked(evt);
            }
        });
        jScrollPanel.setViewportView(tblPersonal);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPanel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro seleccionado"));

        lblIdPersonal.setText("ID Personal");

        lblIdUsuario.setText("ID Usuario");

        lblNombre.setText("Nombre");

        lblProfesion.setText("Profesión");

        txtIdPersonal.setEditable(false);
        txtIdPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPersonalActionPerformed(evt);
            }
        });

        txtIdUsuario.setEditable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnVerTodos.setText("Ver todos");
        btnVerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerTodos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cobProfesion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Médico", "Psicólogo", "Nutricionista" }));

        lblTelefono.setText("Correo");

        lblCorreo.setText("Teléfono");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdPersonal)
                    .addComponent(lblIdUsuario)
                    .addComponent(lblNombre)
                    .addComponent(lblProfesion)
                    .addComponent(lblCorreo)
                    .addComponent(lblTelefono))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cobProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdPersonal)
                            .addComponent(txtIdUsuario)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(txtCorreo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdPersonal)
                            .addComponent(txtIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdUsuario)
                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cobProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProfesion))
                        .addGap(2, 31, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefono))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPersonalActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            datoBuscar = JOptionPane.showInputDialog( "Proporciona el nombre a buscar:");
            dato = datoBuscar.toString();
            llenarDataTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmGestionProfesionistas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            int fila = tblPersonal.getSelectedRow();
            int idPersonal = Integer.parseInt(txtIdPersonal.getText().trim());
            int idUsuario = Integer.parseInt(txtIdUsuario.getText().trim());
            String usuariox = txtNombre.getText().trim();
            String profesion = ((String) cobProfesion.getSelectedItem());
            String correo = txtCorreo.getText().trim();
            String telefono = txtTelefono.getText().trim();
            
            
            PersonalSalud personal = new PersonalSalud(idPersonal, idUsuario, usuariox, profesion, correo, telefono);
            PersonalSaludBD dao = new PersonalSaludBD();
            
            try (Connection conn = objetoConexionBD.conexionDataBase()) {
                boolean actualizado = dao.actualizarPersonal(personal);
                
                if (actualizado) {
                    JOptionPane.showMessageDialog(null, "¡Usuario actualizado correctamente!");
                    llenarDataTable();
                } else {
                    JOptionPane.showMessageDialog(null, "¡No se encontró un usuario con ese ID!");
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar usuario: "+ ex.getMessage());
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "ID de usuario inválido.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador de base de datos: "+ ex.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        try {
            // TODO add your handling code here:
            dato = "";
            llenarDataTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmGestionProfesionistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnVerTodosActionPerformed

    private void tblPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPersonalMouseClicked
        // TODO add your handling code here:
        llenarTextBox();
    }//GEN-LAST:event_tblPersonalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgBecado;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JComboBox<String> cobProfesion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblIdPersonal;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblProfesion;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tblPersonal;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtIdPersonal;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
