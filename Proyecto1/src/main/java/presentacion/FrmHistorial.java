/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.Datos;
import dtos.OperacionDTO;
import dtos.Usuario;
import java.awt.Component;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import negocio.ICuentaNegocio;
import negocio.IOperacionNegocio;
import negocio.NegocioException;

/**
 *
 * @author Usuario
 */
public class FrmHistorial extends javax.swing.JFrame {

    private Datos datos;
    private Usuario usuario;
    private IOperacionNegocio operacionNegocio;
    private ICuentaNegocio cuentaNegocio;
    private int pagina = 1;
    private final int LIMITE = 3;
    private int anchoOriginal5;
    private int anchoOriginal6;
    private int anchoOriginal7;

    /**
     * Creates new form FrmHistorial
     */
    public FrmHistorial(Datos datos, Usuario usuario) {
        initComponents();
        this.datos = datos;
        this.usuario = usuario;
        this.operacionNegocio = datos.getOperacionNegocio();
        this.cuentaNegocio = datos.getCuentaNegocio();
        cargarOperacionesEnTabla(usuario.getCliente().getId(), "Todos", null, null);
        anchoOriginal5 = tblOperaciones.getColumnModel().getColumn(5).getWidth();
        anchoOriginal6 = tblOperaciones.getColumnModel().getColumn(6).getWidth();
        anchoOriginal7 = tblOperaciones.getColumnModel().getColumn(7).getWidth();
    }

    private void cargarOperacionesEnTabla(int idCliente, String tipo, Date inicio, Date fin) {
        try {
            List<OperacionDTO> operacionesLista = operacionNegocio.buscarOperacionesTabla(idCliente, tipo, inicio, fin, pagina, LIMITE);
            llenarTablaClientes(operacionesLista, tipo);
            lblPagina.setText("Página " + pagina);
        } catch (NegocioException ex) {
            pagina--;
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarTablaClientes(List<OperacionDTO> operacionesLista, String tipo) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblOperaciones.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }
        
        switch (tipo) {
            case "Todos":
                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("Cuenta origen");
                tblOperaciones.getColumnModel().getColumn(5).setHeaderValue("Cuenta destino");
//                tblOperaciones.getColumnModel().getColumn(5).setMinWidth(anchoOriginal5);
//                tblOperaciones.getColumnModel().getColumn(5).setMaxWidth(anchoOriginal5);
//                tblOperaciones.getColumnModel().getColumn(5).setWidth(anchoOriginal5);
//                
//                tblOperaciones.getColumnModel().getColumn(6).setMinWidth(anchoOriginal6);
//                tblOperaciones.getColumnModel().getColumn(6).setMaxWidth(anchoOriginal6);
//                tblOperaciones.getColumnModel().getColumn(6).setWidth(anchoOriginal6);
//
//                tblOperaciones.getColumnModel().getColumn(7).setMinWidth(anchoOriginal7);
//                tblOperaciones.getColumnModel().getColumn(7).setMaxWidth(anchoOriginal7);
//                tblOperaciones.getColumnModel().getColumn(7).setWidth(anchoOriginal7);
                break;
            case "Transferencia":
                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("Cuenta origen");
                tblOperaciones.getColumnModel().getColumn(5).setHeaderValue("Cuenta destino");
//                tblOperaciones.getColumnModel().getColumn(5).setMinWidth(1);
//                tblOperaciones.getColumnModel().getColumn(5).setMaxWidth(1);
//                tblOperaciones.getColumnModel().getColumn(5).setWidth(1);
//                
//                tblOperaciones.getColumnModel().getColumn(6).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(6).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(6).setWidth(0);
//
//                tblOperaciones.getColumnModel().getColumn(7).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(7).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(7).setWidth(0);
                break;
            case "Retiro":
                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("No. de cuenta");
//                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("No. de cuenta");
//                tblOperaciones.getColumnModel().getColumn(5).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(5).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(5).setWidth(0);
//                
//                tblOperaciones.getColumnModel().getColumn(6).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(6).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(6).setWidth(0);
//
//                tblOperaciones.getColumnModel().getColumn(7).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(7).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(7).setWidth(0);
                break;
            case "Retiro sin cuenta":
                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("No. de cuenta");
//                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("No. de cuenta");
//                tblOperaciones.getColumnModel().getColumn(5).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(5).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(5).setWidth(0);
//                
//                tblOperaciones.getColumnModel().getColumn(6).setMinWidth(1);
//                tblOperaciones.getColumnModel().getColumn(6).setMaxWidth(1);
//                tblOperaciones.getColumnModel().getColumn(6).setWidth(1);
//
//                tblOperaciones.getColumnModel().getColumn(7).setMinWidth(1);
//                tblOperaciones.getColumnModel().getColumn(7).setMaxWidth(1);
//                tblOperaciones.getColumnModel().getColumn(7).setWidth(1);
                break;
            case "Depósito":
                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("No. de cuenta");
//                tblOperaciones.getColumnModel().getColumn(4).setHeaderValue("No. de cuenta");
//                tblOperaciones.getColumnModel().getColumn(5).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(5).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(5).setWidth(0);
//                
//                tblOperaciones.getColumnModel().getColumn(6).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(6).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(6).setWidth(0);
//
//                tblOperaciones.getColumnModel().getColumn(7).setMinWidth(0);
//                tblOperaciones.getColumnModel().getColumn(7).setMaxWidth(0);
//                tblOperaciones.getColumnModel().getColumn(7).setWidth(0);
                break;
            default:
                throw new AssertionError();
        }
        

        if (operacionesLista != null) {
            operacionesLista.forEach(row -> {
                Object[] fila = new Object[8];
                fila[0] = row.getFolio();
                fila[1] = row.getTipo();
                fila[2] = formatearMonto(Float.parseFloat(row.getMonto()));
                fila[3] = row.getFechaHora();
                fila[4] = "#" + row.getNumCuentaOrigen();
                fila[5] = row.getNumCuentaDestino() != 0 ? "#" + row.getNumCuentaDestino() : "-";
                fila[6] = row.getEstado() != null ? row.getEstado() : "-";
                fila[7] = row.getFechaHoraCobro() != null ? row.getFechaHoraCobro() : "-";

                modeloTabla.addRow(fila);
            });
        }
    }
    
    private String formatearMonto(float monto) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(monto);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOperaciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboTipos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dtchInicio = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dtchFin = new com.toedter.calendar.JDateChooser();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblOperaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblOperaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Folio", "Tipo", "Monto", "Fecha y hora ejecución", "Cuenta origen", "Cuenta destino", "Estado", "Fecha y hora de cobro"
            }
        ));
        jScrollPane2.setViewportView(tblOperaciones);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Historial de operaciones");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Filtrar por tipo");

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Transferencia", "Retiro", "Retiro sin cuenta", "Depósito" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Filtrar por periodo");

        dtchInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Inicio:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Fin:");

        dtchFin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLimpiar.setText("Limpiar filtros");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(comboTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtchInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtchFin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLimpiar)
                        .addComponent(btnBuscar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(dtchInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(dtchFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAnterior.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        lblPagina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPagina.setText("Página 000");

        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPagina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btnVolver)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAnterior)
                        .addComponent(lblPagina)
                        .addComponent(btnSiguiente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        FrmMenu frmMenu = new FrmMenu(datos, usuario);
        frmMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        JButton boton = (JButton)evt.getSource();
        if (boton.getText().equals("Buscar")) {
            pagina = 1;
        }
        int idCliente = usuario.getCliente().getId();
        String tipo = (String) comboTipos.getSelectedItem();
        Date inicio, fin;
        if (dtchInicio.getDate() != null) {
            inicio = new java.sql.Date(dtchInicio.getDate().getTime());
        } else {
            inicio = null;
        }
        
        if (dtchFin.getDate() != null) {
            fin = new java.sql.Date(dtchFin.getDate().getTime());
        } else {
            fin = null;
        }
        
        cargarOperacionesEnTabla(idCliente, tipo, inicio, fin);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        comboTipos.setSelectedIndex(0);
        dtchInicio.setDate(null);
        for (Component component : dtchInicio.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
                break;
            }
        }
        
        dtchFin.setDate(null);
        for (Component component : dtchInicio.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
                break;
            }
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (pagina > 1) {
            pagina--;
            btnBuscarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        pagina++;
        btnBuscarActionPerformed(evt);
    }//GEN-LAST:event_btnSiguienteActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        </editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmHistorial().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboTipos;
    private com.toedter.calendar.JDateChooser dtchFin;
    private com.toedter.calendar.JDateChooser dtchInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JTable tblOperaciones;
    // End of variables declaration//GEN-END:variables
}
