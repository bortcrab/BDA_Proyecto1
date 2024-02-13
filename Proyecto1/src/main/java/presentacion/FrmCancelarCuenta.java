/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CuentaDTO;
import dtos.Datos;
import dtos.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import negocio.IClienteNegocio;
import negocio.ICuentaNegocio;
import negocio.NegocioException;

/**
 *
 * @author jorge
 */
public class FrmCancelarCuenta extends javax.swing.JFrame {

    Datos datos;
    Usuario usuario;
    IClienteNegocio clienteNegocio;
    ICuentaNegocio cuentaNegocio;

    /**
     * Creates new form FrmCuenta
     */
    public FrmCancelarCuenta(Datos datos, Usuario usuario) {
        initComponents();
        this.datos = datos;
        this.usuario = usuario;
        this.clienteNegocio = datos.getClienteNegocio();
        this.cuentaNegocio = datos.getCuentaNegocio();

        obtenerCuentas();
    }

    private void obtenerCuentas() {
        try {
            List<CuentaDTO> listaCuentasDTO = cuentaNegocio.obtenerCuentas(usuario.getCliente().getId());
            DefaultComboBoxModel<CuentaDTO> modelo = new DefaultComboBoxModel<CuentaDTO>();
            for (CuentaDTO cuenta : listaCuentasDTO) {
                modelo.addElement(cuenta);
            }
            comboCuentas.setModel(modelo);
            CuentaDTO primerElemento = (CuentaDTO) comboCuentas.getSelectedItem();
            String saldoFormateado = formatearSaldo(Float.parseFloat(primerElemento.getSaldo()));
            txtSaldo.setText(saldoFormateado);
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCancelarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String formatearSaldo(float saldo) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(saldo);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pwdContrasenia = new javax.swing.JPasswordField();
        btnCancelar = new javax.swing.JButton();
        comboCuentas = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Cancelar cuenta");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Cuidado, estás a punto de cancelar tu cuenta.");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Saldo:");

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Contraseña:");

        pwdContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        comboCuentas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comboCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCuentasActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Cuenta:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(115, 115, 115))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(btnConfirmar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pwdContrasenia))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pwdContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        FrmMenu frmMenu = new FrmMenu(datos, usuario);
        frmMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        String contrasenia = "";
        try {
            contrasenia = clienteNegocio.obtenerContrasenia(usuario.getCliente().getId());
        } catch (NegocioException ex) {
            Logger.getLogger(FrmDepositoRetiro.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (contrasenia.equals(pwdContrasenia.getText())) {
            try {
                CuentaDTO cuentaDTO = (CuentaDTO) comboCuentas.getSelectedItem();
                cuentaNegocio.buscarCuenta(cuentaDTO.getNumCuenta());
                cuentaNegocio.borrar(cuentaDTO);
                FrmMenu frmMenu = new FrmMenu(datos, usuario);
                frmMenu.setVisible(true);
                dispose();
                JOptionPane.showMessageDialog(this, "Su cuenta ha sido cancelada.\nNúmero de cuenta: " + cuentaDTO.getNumCuenta(),
                        "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
            } catch (NegocioException ex) {
                Logger.getLogger(FrmAbrirCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Contraseña errónea.");
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private String hashContrasenia(String contraseniaOriginal) throws NoSuchAlgorithmException {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            
            // Actualizar el digest con los datos proporcionados
            md.update(contraseniaOriginal.getBytes());
            
            // Calcular el hash
            byte[] hashBytes = md.digest();
            
            // Convertir el hash a una representación hexadecimal
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmAbrirCuenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new NoSuchAlgorithmException(ex.getMessage());
        }
    }
    
    private void comboCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCuentasActionPerformed
        CuentaDTO cuentaDTO = (CuentaDTO) comboCuentas.getSelectedItem();
        String saldoFormateado = formatearSaldo(Float.parseFloat(cuentaDTO.getSaldo()));
        txtSaldo.setText(saldoFormateado);
    }//GEN-LAST:event_comboCuentasActionPerformed

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
//            java.util.logging.Logger.getLogger(FrmCancelarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmCancelarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmCancelarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmCancelarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmCancelarCuenta().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<CuentaDTO> comboCuentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField pwdContrasenia;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables
}
