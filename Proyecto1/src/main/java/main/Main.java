/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;

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

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        IOperacionDAO operacionDAO = new OperacionDAO(conexionBD);
        IOperacionNegocio operacionNegocio = new OperacionNegocio(operacionDAO);
        
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);
        
        IDireccionDAO direccionDAO = new DireccionDAO(conexionBD);
        IDireccionNegocio direccionNegocio = new DireccionNegocio(direccionDAO);
        
        ICuentaDAO cuentaDAO = new CuentaDAO(conexionBD);
        ICuentaNegocio cuentaNegocio = new CuentaNegocio(cuentaDAO);

        FrmLogin principal = new FrmLogin(clienteNegocio, direccionNegocio, cuentaNegocio, operacionNegocio);
        principal.setVisible(true);
    }
}
