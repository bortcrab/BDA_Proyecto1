/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidades.CuentaEntidad;
import enumeradores.AccionCatalogoEnumerador;
import java.util.List;
import negocio.NegocioException;

/**
 *
 * @author bortc
 */
public interface ICuentaDAO {
    public List<CuentaEntidad> obtenerCuentas(int idCliente) throws PersistenciaException;
    
    public CuentaEntidad buscarCuenta(int numCuenta) throws PersistenciaException;
    
    public CuentaEntidad buscarCuenta(int folio, String contrasenia) throws PersistenciaException;
    
    public CuentaEntidad guardar(CuentaEntidad cuentaEntidad) throws PersistenciaException;
    
    public void editar(CuentaEntidad cuentaEntidad, AccionCatalogoEnumerador accion) throws PersistenciaException;
    
    public void borrar(CuentaEntidad cuentaEntidad) throws PersistenciaException;
    
    public void transferir(int numCuentaOrigen, int numCuentaDestino, float monto) throws PersistenciaException;
}
