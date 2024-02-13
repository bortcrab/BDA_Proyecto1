/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.CuentaDTO;
import enumeradores.AccionCatalogoEnumerador;
import java.util.List;

/**
 *
 * @author bortc
 */
public interface ICuentaNegocio {
    public List<CuentaDTO> obtenerCuentas(int idCliente) throws NegocioException;
    
    public CuentaDTO buscarCuenta(int numCuenta) throws NegocioException;
    
    public CuentaDTO buscarCuenta(int folio, String contrasenia) throws NegocioException;
    
    public CuentaDTO guardar(CuentaDTO cuentaDTO) throws NegocioException;
    
    public void editar(CuentaDTO cuentaDTO, AccionCatalogoEnumerador accion) throws NegocioException;
    
    public void borrar(CuentaDTO cuentaDTO) throws NegocioException;
    
    public void transferir(int numCuentaOrigen, int numCuentaDestino, float monto) throws NegocioException;
}
