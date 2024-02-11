/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.logging.Logger;

/**
 *
 * @author bortc
 */
public class CuentaDAO implements ICuentaDAO {
    public IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(CuentaDAO.class.getName());
    
    public CuentaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
}
