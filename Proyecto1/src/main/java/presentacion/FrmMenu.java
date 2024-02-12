/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.Datos;
import dtos.Usuario;
import static enumeradores.AccionCatalogoEnumerador.*;
import negocio.IOperacionNegocio;

/**
 *
 * @author Usuario
 */
public class FrmMenu extends javax.swing.JFrame {
    Datos datos;
    Usuario usuario;
    
    /**
     * Creates new form FrmPrincipal
     */
    public FrmMenu(Datos datos, Usuario usuario) {
        initComponents();
        this.datos = datos;
        this.usuario = usuario;
        lblBienvenida.setText("¡Bienvenido, " + usuario.getCliente().getNombres() + "!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPerfil = new javax.swing.JButton();
        btnTransferencia = new javax.swing.JButton();
        btnRetiroSinCuenta = new javax.swing.JButton();
        btnRetiro = new javax.swing.JButton();
        btnAbrirCuenta = new javax.swing.JButton();
        btnCancelarCuenta = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        btnDepositar = new javax.swing.JButton();
        lblBienvenida = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPerfil.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPerfil.setText("Perfil");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });

        btnTransferencia.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnTransferencia.setText("Transferencia");
        btnTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferenciaActionPerformed(evt);
            }
        });

        btnRetiroSinCuenta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRetiroSinCuenta.setText("Retiro sin cuenta");
        btnRetiroSinCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroSinCuentaActionPerformed(evt);
            }
        });

        btnRetiro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRetiro.setText("Retiro");
        btnRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroActionPerformed(evt);
            }
        });

        btnAbrirCuenta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAbrirCuenta.setText("Abrir cuenta");
        btnAbrirCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCuentaActionPerformed(evt);
            }
        });

        btnCancelarCuenta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCancelarCuenta.setText("Cancelar cuenta");
        btnCancelarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCuentaActionPerformed(evt);
            }
        });

        btnHistorial.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnHistorial.setText("Historial de operaciones");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        btnDepositar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnDepositar.setText("Depósito");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        lblBienvenida.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida.setText("¡Bienvenido, usuario!");

        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(btnCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDepositar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRetiroSinCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAbrirCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHistorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                .addGap(105, 105, 105))
            .addComponent(lblBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblBienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTransferencia)
                    .addComponent(btnDepositar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRetiro)
                    .addComponent(btnRetiroSinCuenta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirCuenta)
                    .addComponent(btnCancelarCuenta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHistorial)
                    .addComponent(btnPerfil))
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSesion)
                .addGap(10, 10, 10))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        FrmPerfil frmPerfil = new FrmPerfil(datos, usuario, EDITAR);
        frmPerfil.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferenciaActionPerformed
        FrmTransferencia frmTransferencia = new FrmTransferencia(datos, usuario);
        frmTransferencia.setVisible(true);
        dispose();     
    }//GEN-LAST:event_btnTransferenciaActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
//        FrmHistorial frmHistorial = new FrmHistorial(operacionNegocio);
//        frmHistorial.setVisible(true);
//        dispose();
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        FrmLogin frmLogin = new FrmLogin(datos);
        frmLogin.setVisible(true);
        dispose();        
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnAbrirCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCuentaActionPerformed
        FrmAbrirCuenta frmAbrirCuenta = new FrmAbrirCuenta(datos, usuario);
        frmAbrirCuenta.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAbrirCuentaActionPerformed

    private void btnCancelarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCuentaActionPerformed
        FrmCancelarCuenta frmCancelarCuenta = new FrmCancelarCuenta(datos, usuario);
        frmCancelarCuenta.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarCuentaActionPerformed

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        FrmEditarCuenta frmEditarCuenta = new FrmEditarCuenta(datos, usuario, DEPOSITO);
        frmEditarCuenta.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btnRetiroSinCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroSinCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRetiroSinCuentaActionPerformed

    private void btnRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroActionPerformed
        FrmEditarCuenta frmEditarCuenta = new FrmEditarCuenta(datos, usuario, RETIRO);
        frmEditarCuenta.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRetiroActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmMenu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCuenta;
    private javax.swing.JButton btnCancelarCuenta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JButton btnRetiro;
    private javax.swing.JButton btnRetiroSinCuenta;
    private javax.swing.JButton btnTransferencia;
    private javax.swing.JLabel lblBienvenida;
    // End of variables declaration//GEN-END:variables
}
