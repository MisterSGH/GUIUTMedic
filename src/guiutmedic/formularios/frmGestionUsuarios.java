
package guiutmedic.formularios;

import guiutmedic.clases.ConexionBD;
import guiutmedic.clases.PerfilBD;
import guiutmedic.clases.Usuario;
import guiutmedic.clases.UsuarioBD;
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
 * Fecha: 09/07/2025
 * @author Adrían
 */
public class frmGestionUsuarios extends javax.swing.JInternalFrame {
    
    ConexionBD objetoConexionBD = new ConexionBD();
    Connection conn;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    
    public frmGestionUsuarios(Usuario usuario) throws ClassNotFoundException {
        initComponents();
        rbdUsuario.setSelected(false);
        rbdTodos.setSelected(true);
        llenarDataTable();
        btnAgregar.setEnabled(false);
    }
    
    public final void llenarDataTable() throws ClassNotFoundException {
        ArrayList<Usuario> arregloUsuarios = new ArrayList<>();
        String sql = "SELECT idusuario, matricula, password, rol, usuario FROM usuario";
        String dato = "";

        try {
            conn = objetoConexionBD.conexionDataBase();

            // Filtrado según el radio button seleccionado
            if (rbdPersonal.isSelected()) {
                sql += " WHERE rol='medico'";
            } else if (rbdPacientes.isSelected()) {
                sql += " WHERE rol='paciente'";
            } else if (rbdAdministrador.isSelected()) {
                sql += " WHERE rol='ADMIN'";
            } else if (rbdUsuario.isSelected()) {
                dato = txtDatoBuscar.getText().trim();
                if (dato.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Escribe un nombre de usuario para buscar.");
                    return;
                }
            sql += " WHERE usuario=?";
            }
            // Si no hay filtros seleccionados, no se agrega WHERE
            if (rbdTodos.isSelected()) {
                // No agrega filtro WHERE, carga todos
                
            }

            // Preparar y asignar parámetros si es necesario
            stmt = conn.prepareStatement(sql);

            if (rbdUsuario.isSelected()) {
                stmt.setString(1, dato);
            }
            System.out.println("Consulta SQL: " + sql);
            System.out.println("Dato: " + dato);
            
            rs = stmt.executeQuery();

            // Verificar si hay resultados
            boolean tieneDatos = false;
            while (rs.next()) {
                tieneDatos = true;
                Usuario usuario = new Usuario(
                        rs.getInt("idusuario"),
                        rs.getString("matricula"),
                        rs.getString("password"),
                        rs.getString("rol"),
                        rs.getString("usuario")
                );
                arregloUsuarios.add(usuario);
            }

            if (!tieneDatos) {
                JOptionPane.showMessageDialog(null, "¡Datos no localizados!");
            }

            // Actualizar tabla
            DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
            modelo.setRowCount(0); // Limpiar tabla
            for (Usuario item : arregloUsuarios) {
                Object[] fila = {
                    item.getIdUsuario(),
                    item.getMatricula(),
                    item.getPassword(),
                    item.getRol(),
                    item.getUsuario()
                };
                modelo.addRow(fila);
            }

            tblUsuarios.setModel(modelo);

            // Seleccionar la primera fila si hay datos
            if (tblUsuarios.getRowCount() > 0) {
                tblUsuarios.setRowSelectionInterval(0, 0);
                llenarTextBox(); // Llenar los textbox
            }

        } catch (SQLException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void llenarTextBox() {
        int fila = tblUsuarios.getSelectedRow();

        if (fila >= 0) {
            txtIdUsuario.setText(tblUsuarios.getValueAt(fila, 0).toString());
            txtMatricula.setText(tblUsuarios.getValueAt(fila, 1).toString());
            txtPassword.setText(tblUsuarios.getValueAt(fila, 2).toString());
            cmbRol.setSelectedItem(tblUsuarios.getValueAt(fila, 3).toString());
            txtUsuario.setText(tblUsuarios.getValueAt(fila, 4).toString());
        }

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgFiltrado = new javax.swing.ButtonGroup();
        frmBuscar = new javax.swing.JDialog();
        pnlDatoBuscar = new javax.swing.JPanel();
        rbdUsuario = new javax.swing.JRadioButton();
        rbdEmail = new javax.swing.JRadioButton();
        lblDatoBuscar = new javax.swing.JLabel();
        txtDatoBuscar = new javax.swing.JTextField();
        btnBuscarDato = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btgBuscar = new javax.swing.ButtonGroup();
        pnlRelacionEmpleados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        pnlCampos = new javax.swing.JPanel();
        lblIdUsuario = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        cmbRol = new javax.swing.JComboBox<>();
        pnlAcciones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        pnlConsultar = new javax.swing.JPanel();
        rbdPersonal = new javax.swing.JRadioButton();
        rbdPacientes = new javax.swing.JRadioButton();
        rbdAdministrador = new javax.swing.JRadioButton();
        rbdTodos = new javax.swing.JRadioButton();
        btnVer = new javax.swing.JButton();

        pnlDatoBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecciona el dato a buscar"));

        btgBuscar.add(rbdUsuario);
        rbdUsuario.setText("Usuario");

        btgBuscar.add(rbdEmail);
        rbdEmail.setText("Email");

        lblDatoBuscar.setText("Dato a buscar");

        btnBuscarDato.setText("Buscar");
        btnBuscarDato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDatoActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDatoBuscarLayout = new javax.swing.GroupLayout(pnlDatoBuscar);
        pnlDatoBuscar.setLayout(pnlDatoBuscarLayout);
        pnlDatoBuscarLayout.setHorizontalGroup(
            pnlDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatoBuscarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbdEmail)
                    .addComponent(rbdUsuario)
                    .addGroup(pnlDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlDatoBuscarLayout.createSequentialGroup()
                            .addComponent(btnBuscarDato)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCerrar))
                        .addGroup(pnlDatoBuscarLayout.createSequentialGroup()
                            .addComponent(lblDatoBuscar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDatoBuscarLayout.setVerticalGroup(
            pnlDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatoBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbdUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbdEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatoBuscar)
                    .addComponent(txtDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarDato)
                    .addComponent(btnCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frmBuscarLayout = new javax.swing.GroupLayout(frmBuscar.getContentPane());
        frmBuscar.getContentPane().setLayout(frmBuscarLayout);
        frmBuscarLayout.setHorizontalGroup(
            frmBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        frmBuscarLayout.setVerticalGroup(
            frmBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ventana de Gestion de Usuarios");

        pnlRelacionEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder("Relacion de usuarios"));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Usuario", "Matricula", "Password", "Rol", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        javax.swing.GroupLayout pnlRelacionEmpleadosLayout = new javax.swing.GroupLayout(pnlRelacionEmpleados);
        pnlRelacionEmpleados.setLayout(pnlRelacionEmpleadosLayout);
        pnlRelacionEmpleadosLayout.setHorizontalGroup(
            pnlRelacionEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelacionEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRelacionEmpleadosLayout.setVerticalGroup(
            pnlRelacionEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelacionEmpleadosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pnlCampos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro seleccionado"));

        lblIdUsuario.setText("ID Usuario");

        lblUsuario.setText("Matricula");

        lblPassword.setText("Password");

        lblEmail.setText("Rol");

        lblRol.setText("Usuario");

        txtIdUsuario.setEditable(false);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "paciente", "medico", "ADMIN" }));

        pnlAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones en Usuario"));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesLayout = new javax.swing.GroupLayout(pnlAcciones);
        pnlAcciones.setLayout(pnlAccionesLayout);
        pnlAccionesLayout.setHorizontalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAccionesLayout.setVerticalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(lblPassword)
                            .addComponent(lblEmail)
                            .addComponent(lblRol))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIdUsuario)
                        .addGap(18, 18, 18)))
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbRol, 0, 325, Short.MAX_VALUE)
                    .addComponent(txtMatricula)
                    .addComponent(txtPassword)
                    .addComponent(txtIdUsuario)
                    .addComponent(txtUsuario))
                .addGap(118, 118, 118)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdUsuario))
                .addGap(16, 16, 16)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRol)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addComponent(pnlAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar por:"));

        btgFiltrado.add(rbdPersonal);
        rbdPersonal.setText("Personal");
        rbdPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdPersonalActionPerformed(evt);
            }
        });

        btgFiltrado.add(rbdPacientes);
        rbdPacientes.setText("Pacientes");

        btgFiltrado.add(rbdAdministrador);
        rbdAdministrador.setText("Administrador");

        btgFiltrado.add(rbdTodos);
        rbdTodos.setSelected(true);
        rbdTodos.setText("Todos");

        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlConsultarLayout = new javax.swing.GroupLayout(pnlConsultar);
        pnlConsultar.setLayout(pnlConsultarLayout);
        pnlConsultarLayout.setHorizontalGroup(
            pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbdPersonal)
                    .addComponent(rbdPacientes)
                    .addComponent(rbdAdministrador)
                    .addComponent(rbdTodos)
                    .addGroup(pnlConsultarLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnVer)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        pnlConsultarLayout.setVerticalGroup(
            pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(rbdPersonal)
                .addGap(18, 18, 18)
                .addComponent(rbdPacientes)
                .addGap(18, 18, 18)
                .addComponent(rbdAdministrador)
                .addGap(18, 18, 18)
                .addComponent(rbdTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVer)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlRelacionEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlRelacionEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void rbdPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbdPersonalActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        btgFiltrado.clearSelection();
        try {
            // TODO add your handling code here:
            llenarDataTable();
            this.rbdUsuario.setSelected(false);
            btgBuscar.clearSelection();
            this.txtDatoBuscar.setText("");
            this.frmBuscar.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
        frmBuscar.setTitle("Busqueda de usuarios");
        frmBuscar.setSize(300, 300);
        frmBuscar.setLocationRelativeTo(this);
        this.frmBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        // Se llenan las cajas de texto con la informacion de los registros del jTable
        llenarTextBox();
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            // Se llama al metodo de registrar al usuario
            registrarUsuario();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Se desactiva el boton de agregar
        btnAgregar.setEnabled(false);
        // Se activa el boton de nuevo
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // Se desabilita el boton de nuevo
        btnNuevo.setEnabled(false);
        // Se limpian los campos para que los llenen
        txtIdUsuario.setText("");
        txtMatricula.setText("");
        txtPassword.setText("");
        txtUsuario.setText("");
        cmbRol.setSelectedItem("Paciente");
        // Se activa el boton para Agregar el nuevo registro
        btnAgregar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        
        try {
            int idUsuario = Integer.parseInt(txtIdUsuario.getText().trim());
            String matricula = txtMatricula.getText().trim();
            String password = txtPassword.getText().trim();
            String usuariox = txtUsuario.getText().trim();
            String rol = cmbRol.getSelectedItem().toString();
            
            Usuario usuario = new Usuario(matricula, password, usuariox, rol);
            UsuarioBD dao = new UsuarioBD();
            
            try (Connection conn = objetoConexionBD.conexionDataBase()) {
                boolean actualizado = dao.actualizarUsuario(conn, usuario, idUsuario);
                
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
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            int idUsuario = Integer.parseInt(txtIdUsuario.getText().trim());
            UsuarioBD dao = new UsuarioBD();
            try (Connection conn = objetoConexionBD.conexionDataBase()) {
                boolean eliminado = dao.eliminarUsuario(conn, idUsuario);
                
                boolean MEliminado = dao.eliminarEmpleado(conn, idUsuario);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "¡Usuario eliminado correctamente!");
                    llenarDataTable();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un usuario en el ID: "+ idUsuario);
                }
                
                if (MEliminado) {
                    JOptionPane.showMessageDialog(null, "¡Personal eliminado correctamente!");
                }
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar usuario: "+ ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "ID inválido. Verifique que sea un número.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador de base de datos: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        try {
            this.llenarDataTable();
        } catch ( ClassNotFoundException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnBuscarDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDatoActionPerformed
        // TODO add your handling code here:
        btgFiltrado.clearSelection();
        try {
            // TODO add your handling code here:
            llenarDataTable();
            this.rbdUsuario.setSelected(false);
            btgBuscar.clearSelection();
            this.txtDatoBuscar.setText("");
            this.frmBuscar.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarDatoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        //Borrando los criterios de busqueda
        try {
            this.rbdUsuario.setSelected(false);
            btgFiltrado.clearSelection();
            this.txtDatoBuscar.setText("");
            llenarDataTable();
            this.frmBuscar.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    
//    private void registrarUsuario() {
//        Usuario objUsuario = new Usuario(txtMatricula.getText(), txtPassword.getText(), txtUsuario.getText(), cmbRol.getSelectedItem().toString());
//        UsuarioBD objBD = new UsuarioBD();
//        PerfilDB perBD = new PerfilDB();
//        String rol = objUsuario.getRol();
//        
//        try (Connection conn = objetoConexionBD.conexionDataBase()) {
//            int idGenerado = objBD.insertarUsuario(conn, objUsuario);
//            if (idGenerado > 0) {
//                JOptionPane.showMessageDialog(null, "¡Usuario registrado correctamente! ID generado: "+ idGenerado);
//                System.out.println("Antes de insertar perfil");
//                perBD.insertarPerfil(conn, objUsuario, idGenerado);
//                System.out.println("Después de insertar perfil");
//                JOptionPane.showMessageDialog(null, "¡Perfil insertado correctamente!");
//                
//                if (rol.equalsIgnoreCase("paciente")) {
//                    objBD.insertarUsuario(conn, objUsuario);
//                    JOptionPane.showMessageDialog(null, "¡Paciente insertado correctamente!");
//                } else if (rol.equalsIgnoreCase("medico")) {
//                    objBD.insertarEmpleado(conn, objUsuario, idGenerado);
//                    JOptionPane.showMessageDialog(null, "¡Personal insertado corretamente!");
//                }
//                
//                
//                llenarDataTable();
//                if(tblUsuarios.getRowCount() > 0) {
//                    int ultimaFila = tblUsuarios.getRowCount() - 1;
//                    tblUsuarios.setRowSelectionInterval(ultimaFila, ultimaFila);
//                    llenarTextBox();
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario.");
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
////            JOptionPane.showMessageDialog(null, "Error al registrar usuario: "+ ex.getMessage());
//        }
//    }
    
    private void registrarUsuario() throws ClassNotFoundException {
    Usuario objUsuario = new Usuario(
        txtMatricula.getText(),
        txtPassword.getText(),
        txtUsuario.getText(),
        cmbRol.getSelectedItem().toString()
    );

    UsuarioBD objBD = new UsuarioBD();
    PerfilBD perBD = new PerfilBD();
    String rol = objUsuario.getRol();

    System.out.println("[DEBUG] Iniciando registro de usuario...");

    try (Connection conn = objetoConexionBD.conexionDataBase()) {

        System.out.println("[DEBUG] Conexión establecida.");

        int idGenerado = objBD.insertarUsuario(conn, objUsuario);
        System.out.println("[DEBUG] ID generado por usuario: " + idGenerado);

        if (idGenerado > 0) {
            JOptionPane.showMessageDialog(null, "¡Usuario registrado correctamente! ID generado: " + idGenerado);

            try {
                System.out.println("[DEBUG] Insertando perfil...");
                perBD.insertarPerfil(conn, objUsuario, idGenerado);
                System.out.println("[DEBUG] Perfil insertado.");
                JOptionPane.showMessageDialog(null, "¡Perfil insertado correctamente!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error insertando perfil: " + e.getMessage());
                e.printStackTrace();
            }

//            if (rol.equalsIgnoreCase("paciente")) {
//                try {
//                    System.out.println("[DEBUG] Insertando paciente...");
//                    objBD.insertarUsuario(conn, objUsuario);
//                    JOptionPane.showMessageDialog(null, "¡Paciente insertado correctamente!");
//                } catch (SQLException e) {
//                    JOptionPane.showMessageDialog(null, "Error insertando paciente: " + e.getMessage());
//                    e.printStackTrace();
//                }
            if (rol.equalsIgnoreCase("medico")) {
                try {
                    System.out.println("[DEBUG] Insertando personal médico...");
                    objBD.insertarEmpleado(conn, objUsuario, idGenerado);
                    JOptionPane.showMessageDialog(null, "¡Personal insertado correctamente!");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error insertando médico: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error general en registrarUsuario: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgBuscar;
    private javax.swing.ButtonGroup btgFiltrado;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarDato;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JDialog frmBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatoBuscar;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlConsultar;
    private javax.swing.JPanel pnlDatoBuscar;
    private javax.swing.JPanel pnlRelacionEmpleados;
    private javax.swing.JRadioButton rbdAdministrador;
    private javax.swing.JRadioButton rbdEmail;
    private javax.swing.JRadioButton rbdPacientes;
    private javax.swing.JRadioButton rbdPersonal;
    private javax.swing.JRadioButton rbdTodos;
    private javax.swing.JRadioButton rbdUsuario;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtDatoBuscar;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
