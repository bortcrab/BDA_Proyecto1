/*
 * FrmRetiroSinCuenta.java
 */
package presentacion;

import dtos.CuentaDTO;
import negocio.Datos;
import dtos.OperacionDTO;
import static enumeradores.AccionCatalogoEnumerador.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.ICuentaNegocio;
import negocio.IOperacionNegocio;
import negocio.NegocioException;

/**
 * Esta clase representa una ventana de interfaz de usuario para realizar
 * retiros sin cuenta.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class FrmRetiroSinCuenta extends javax.swing.JFrame {

    Datos datos;
    ICuentaNegocio cuentaNegocio;
    IOperacionNegocio operacionNegocio;

    /**
     * Constructor de la clase FrmRetiroSinCuenta.
     *
     * @param datos Objeto de datos que contiene las instancias de los objetos
     * de negocio necesarios.
     */
    public FrmRetiroSinCuenta(Datos datos) {
        initComponents();
        this.datos = datos;
        this.cuentaNegocio = datos.getCuentaNegocio();
        this.operacionNegocio = datos.getOperacionNegocio();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pwdContrasenia = new javax.swing.JPasswordField();
        btnRetirar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Retiro sin cuenta");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Folio:");

        txtFolio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFolio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFolioKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Contraseña:");

        pwdContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pwdContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pwdContraseniaKeyTyped(evt);
            }
        });

        btnRetirar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRetirar.setText("Retirar");
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ingrese el folio y contraseña.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(163, 163, 163)
                        .addComponent(btnRetirar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pwdContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFolio))))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pwdContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRetirar)
                    .addComponent(btnCancelar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción realizada cuando se presiona el botón "Retirar". Verifica la
     * validez del folio y la contraseña ingresados, y realiza el retiro sin
     * cuenta si son correctos.
     *
     * @param evt El evento de acción.
     */
    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        if (txtFolio.getText().equals("") && pwdContrasenia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Favor de ingresar un folio y una contraseña válida.", "Oops!", JOptionPane.ERROR_MESSAGE);
        } else if (txtFolio.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Favor de ingresar un folio válido.", "Oops!", JOptionPane.ERROR_MESSAGE);
        } else if (pwdContrasenia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Favor de ingresar una contraseña válida.", "Oops!", JOptionPane.ERROR_MESSAGE);
        } else {
            String contrasenia = "";
            try {
                OperacionDTO operacionDTO = operacionNegocio.obtenerOperacion(Integer.parseInt(txtFolio.getText()));
                if (operacionDTO.getTipo().equals("Retiro sin cuenta")) {
                    contrasenia = operacionNegocio.obtenerContrasenia(Integer.parseInt(txtFolio.getText()));
                    if (contrasenia.equals(pwdContrasenia.getText())) {
                        try {
                            CuentaDTO cuentaDTO = cuentaNegocio.buscarCuenta(Integer.parseInt(txtFolio.getText()), pwdContrasenia.getText());
                            operacionDTO = operacionNegocio.obtenerOperacion(Integer.parseInt(txtFolio.getText()));
                            cuentaDTO.setSaldo(String.valueOf(operacionDTO.getMonto()));
                            operacionNegocio.actualizarRetiroSinCuenta(Integer.parseInt(txtFolio.getText()));
                            cuentaNegocio.editar(cuentaDTO, EDITAR);
                            JOptionPane.showMessageDialog(this, "Operación exitosa.", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
                            FrmLogin frmLogin = new FrmLogin(datos);
                            frmLogin.setVisible(true);
                            dispose();
                        } catch (NegocioException ex) {
                            Logger.getLogger(FrmRetiroSinCuenta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Contraseña errónea.", "Oops!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró un retiro con tal folio.", "Oops!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Oops!", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmDepositoRetiro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRetirarActionPerformed

    /**
     * Acción realizada cuando se presiona el botón "Cancelar". Se muestra el
     * formulario de inicio de sesión.
     *
     * @param evt El evento de acción.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        FrmLogin frmLogin = new FrmLogin(datos);
        frmLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Método que permite ingresar solo números en el campo de texto de la
     * contraseña.
     *
     * @param evt El evento de teclado.
     */
    private void pwdContraseniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwdContraseniaKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_pwdContraseniaKeyTyped

    /**
     * Método que permite ingresar solo números en el campo de texto del folio.
     *
     * @param evt El evento de teclado.
     */
    private void txtFolioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolioKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFolioKeyTyped

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
//            java.util.logging.Logger.getLogger(FrmRetiroSinCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmRetiroSinCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmRetiroSinCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmRetiroSinCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmRetiroSinCuenta().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField pwdContrasenia;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
}
