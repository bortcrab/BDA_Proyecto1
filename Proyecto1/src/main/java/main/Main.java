/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;

import negocio.IOperacionNegocio;
import negocio.OperacionNegocio;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
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

        FrmLogin principal = new FrmLogin(operacionNegocio);
        principal.show();
    }
}
