/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import persistencia.ICuentaDAO;
import persistencia.IDireccionDAO;

/**
 *
 * @author bortc
 */
public class CuentaNegocio implements ICuentaNegocio {
    private ICuentaDAO cuentaDAO;

    public CuentaNegocio(ICuentaDAO cuentaDAO) {
        this.cuentaDAO = cuentaDAO;
    }
}
