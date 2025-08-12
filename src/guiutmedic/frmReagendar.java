package guiutmedic;

import guiutmedic.clases.Cita;
import guiutmedic.clases.CitaBD;
import guiutmedic.clases.ConexionBD;
import guiutmedic.clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class frmReagendar extends javax.swing.JInternalFrame {

    private final int idPerfil;
    private final Map<Integer, Cita> mapaCitas = new HashMap<>();
    private final CitaBD citaBD = new CitaBD();
    private final ConexionBD conexionBD = new ConexionBD();
    private final Usuario usuario;
    

    public frmReagendar(Usuario usuario) throws ClassNotFoundException {
        initComponents();
        this.usuario = usuario;
        this.idPerfil = usuario != null ? usuario.getIdPerfil() : -1;

        if (this.idPerfil <= 0) {
            JOptionPane.showMessageDialog(this,
                "Perfil inválido. No se pueden cargar las citas.",
                "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        initFormulario();

        
        cmbCita.addActionListener(e -> onCitaSeleccionada());
        btnReagendar.addActionListener(e -> onReagendar());
    }

    private void initFormulario() {
        cargarMotivos();
        cargarCitasPaciente();
        habilitarControles(false);
        cmbCita.setSelectedIndex(-1);
    }

    private void cargarMotivos() {
    cbmMotivo.removeAllItems();
    try (Connection conn = conexionBD.conexionDataBase();
         PreparedStatement ps = conn.prepareStatement(
             "SELECT idMotivo, descripcion FROM motivo WHERE idMotivo IN (20, 21, 22) ORDER BY idMotivo");
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("idMotivo");
            String descripcion = rs.getString("descripcion");
            cbmMotivo.addItem(id + " - " + descripcion);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar motivos: " + ex.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void cargarCitasPaciente() {
        mapaCitas.clear();
        cmbCita.removeAllItems();

        System.out.println("Cargando citas para idPerfil: " + idPerfil);

        try (Connection conn = conexionBD.conexionDataBase();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT c.idCita, c.fecha, c.hora, c.idMotivo, c.estado, m.descripcion, c.idPerfil " +
                 "FROM cita c JOIN motivo m ON c.idMotivo = m.idMotivo " +
                 "WHERE c.idPerfil = ? AND c.estado IN ('Programada', 'Reprogramada')")) {

            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();

            int contador = 0;
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt("idCita"));
                cita.setFecha(rs.getString("fecha"));
                cita.setHora(rs.getString("hora"));
                cita.setIdMotivo(rs.getInt("idMotivo"));
                cita.setEstado(rs.getString("estado"));

                mapaCitas.put(cita.getIdCita(), cita);
                String texto = "Cita #" + cita.getIdCita() + " - " + cita.getFecha()
                        + " " + cita.getHora() + " / Motivo: " + rs.getString("descripcion");
                cmbCita.addItem(texto);
                contador++;
            }

            System.out.println("Total de citas cargadas: " + contador);

            if (contador == 0) {
                JOptionPane.showMessageDialog(this, "No hay citas agendadas para mostrar.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
                habilitarControles(false);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar citas: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCitaSeleccionada() {
        Object sel = cmbCita.getSelectedItem();
        if (sel == null) {
            habilitarControles(false);
            return;
        }
        int idCita = extraerIdCita(sel.toString());
        Cita cita = mapaCitas.get(idCita);
        if (cita != null) {
            cargarDatosCita(cita);
            habilitarControles(true);
        } else {
            habilitarControles(false);
        }
    }

    private void onReagendar() {
    if (cmbCita.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "Seleccione una cita primero",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (jDateChooser1.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Seleccione una fecha válida",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String nuevaFecha = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());

    
    String horaOriginal = timePicker1.getText(); 
    String nuevaHora;
    try {
        java.text.DateFormat formato12h = new java.text.SimpleDateFormat("hh:mm a");
        java.text.DateFormat formato24h = new java.text.SimpleDateFormat("HH:mm");
        java.util.Date horaParseada = formato12h.parse(horaOriginal);
        nuevaHora = formato24h.format(horaParseada); // Ej. "11:30" o "23:15"
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Formato de hora inválido. Use formato AM/PM válido",
                "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Object motivoSel = cbmMotivo.getSelectedItem();
    if (motivoSel == null) {
        JOptionPane.showMessageDialog(this, "Seleccione un motivo",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    int nuevoIdMotivo = extraerIdMotivoCombo(motivoSel.toString());
    if (nuevoIdMotivo <= 0) {
        JOptionPane.showMessageDialog(this, "Motivo inválido",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int idCita = extraerIdCita(cmbCita.getSelectedItem().toString());
    if (idCita <= 0 || !mapaCitas.containsKey(idCita)) {
        JOptionPane.showMessageDialog(this, "Cita inválida",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try (Connection conn = conexionBD.conexionDataBase()) {
        boolean exito = citaBD.reagendarCita(conn, idCita, nuevoIdMotivo, nuevaFecha, nuevaHora);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Cita reagendada con éxito",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarCitasPaciente();
            habilitarControles(false);
            cmbCita.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo reagendar la cita",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al reagendar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void cargarDatosCita(Cita cita) {
        try {
            jDateChooser1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(cita.getFecha()));
            timePicker1.setText(cita.getHora());
            seleccionarMotivoPorId(cita.getIdMotivo());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void habilitarControles(boolean habilitar) {
        jDateChooser1.setEnabled(habilitar);
        timePicker1.setEnabled(habilitar);
        cbmMotivo.setEnabled(habilitar);
        btnReagendar.setEnabled(habilitar);
    }

    private void seleccionarMotivoPorId(int idMotivo) {
        for (int i = 0; i < cbmMotivo.getItemCount(); i++) {
            String item = cbmMotivo.getItemAt(i);
            if (item != null && item.startsWith(idMotivo + " -")) {
                cbmMotivo.setSelectedIndex(i);
                return;
            }
        }
    }

    private int extraerIdCita(String textoCita) {
        try {
            int startIndex = textoCita.indexOf("#") + 1;
            int endIndex = textoCita.indexOf(" -", startIndex);
            return Integer.parseInt(textoCita.substring(startIndex, endIndex).trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private int extraerIdMotivoCombo(String item) {
        try {
            return Integer.parseInt(item.split("-")[0].trim());
        } catch (Exception e) {
            return -1;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCita = new javax.swing.JComboBox<>();
        lblHora = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblMotivo = new javax.swing.JLabel();
        btnReagendar = new javax.swing.JButton();
        cbmMotivo = new javax.swing.JComboBox<>();
        lblCita = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        timePicker1 = new com.github.lgooddatepicker.components.TimePicker();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        cmbCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCitaActionPerformed(evt);
            }
        });

        lblHora.setText("Hora:");

        lblFecha.setText("Fecha:");

        lblMotivo.setText("Motivo:");

        btnReagendar.setText("Reagendar Cita");
        btnReagendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReagendarActionPerformed(evt);
            }
        });

        cbmMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Situacion Personal", "Horario Ocupado", "Otro" }));
        cbmMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmMotivoActionPerformed(evt);
            }
        });

        lblCita.setText("Elige la Cita:");

        btnCancelar.setText("Cancelar Cita");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReagendar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblCita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHora)
                            .addComponent(lblMotivo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(199, 199, 199))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCita)
                    .addComponent(cmbCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFecha)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHora)
                    .addComponent(timePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbmMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMotivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReagendar)
                    .addComponent(btnCancelar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCitaActionPerformed
if (cmbCita.getSelectedItem() == null) return;

        int idCita = extraerIdCita(cmbCita.getSelectedItem().toString());
        Cita cita = mapaCitas.get(idCita);

        if (cita != null) {
            cargarDatosCita(cita);
            habilitarControles(true);
        }
    }//GEN-LAST:event_cmbCitaActionPerformed

    private void btnReagendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReagendarActionPerformed
  if (cmbCita.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una fecha valida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nuevaFecha = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
        String nuevaHora = timePicker1.getText();
        if (nuevaHora == null || nuevaHora.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una hora valida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String motivoTexto = cbmMotivo.getSelectedItem().toString();
        int nuevoIdMotivo = extraerIdMotivoCombo(motivoTexto);

        int idCita = extraerIdCita(cmbCita.getSelectedItem().toString());

        try (Connection conn = conexionBD.conexionDataBase()) {
            boolean exito = citaBD.reagendarCita(conn, idCita, nuevoIdMotivo, nuevaFecha, nuevaHora);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Cita reagendada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarCitasPaciente();
                habilitarControles(false);
                cmbCita.setSelectedIndex(-1);
            } else {
                JOptionPane.showMessageDialog(this, "Error al reagendar la cita", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al reagendar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_btnReagendarActionPerformed

    private void cbmMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmMotivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmMotivoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
      Object sel = cmbCita.getSelectedItem();
    if (sel == null) {
        JOptionPane.showMessageDialog(this, "Seleccione una cita para cancelar",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int idCita = extraerIdCita(sel.toString());
    if (idCita <= 0 || !mapaCitas.containsKey(idCita)) {
        JOptionPane.showMessageDialog(this, "Cita inválida",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea cancelar esta cita?",
            "Confirmar cancelación", JOptionPane.YES_NO_OPTION);

    if (confirm != JOptionPane.YES_OPTION) return;

    try (Connection conn = conexionBD.conexionDataBase();
         PreparedStatement ps = conn.prepareStatement("DELETE FROM cita WHERE idCita = ?")) {

        ps.setInt(1, idCita);
        int filas = ps.executeUpdate();

        if (filas > 0) {
            JOptionPane.showMessageDialog(this, "Cita cancelada exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarCitasPaciente();
            habilitarControles(false);
            cmbCita.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cancelar la cita",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al cancelar cita: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnReagendar;
    private javax.swing.JComboBox<String> cbmMotivo;
    private javax.swing.JComboBox<String> cmbCita;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel lblCita;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblMotivo;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    // End of variables declaration//GEN-END:variables
}