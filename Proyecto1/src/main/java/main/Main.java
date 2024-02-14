/*
 * Main.java
 */
package main;

import dtos.Datos;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.DireccionNegocio;
import negocio.IClienteNegocio;
import negocio.ICuentaNegocio;
import negocio.IDireccionNegocio;
import negocio.IOperacionNegocio;
import negocio.OperacionNegocio;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.CuentaDAO;
import persistencia.DireccionDAO;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.ICuentaDAO;
import persistencia.IDireccionDAO;
import persistencia.IOperacionDAO;
import persistencia.OperacionDAO;
import presentacion.FrmLogin;
import utilerias.Validadores;

/**
 * Clase principal del programa
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Establecer el Look and Feel deseado (por ejemplo, Nimbus)
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        IConexionBD conexionBD = new ConexionBD();

        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);

        IDireccionDAO direccionDAO = new DireccionDAO(conexionBD);
        IDireccionNegocio direccionNegocio = new DireccionNegocio(direccionDAO);

        ICuentaDAO cuentaDAO = new CuentaDAO(conexionBD);
        ICuentaNegocio cuentaNegocio = new CuentaNegocio(cuentaDAO);

        IOperacionDAO operacionDAO = new OperacionDAO(conexionBD);
        IOperacionNegocio operacionNegocio = new OperacionNegocio(operacionDAO);
        
        Validadores validadores = new Validadores();
        
        Datos datos = new Datos(clienteNegocio, direccionNegocio, cuentaNegocio, operacionNegocio, validadores);

        FrmLogin principal = new FrmLogin(datos);
        principal.setVisible(true);
    }
}
