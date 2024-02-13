/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.OperacionDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IOperacionNegocio {
    public List<OperacionDTO> buscarOperacionesTabla(int idCliente, String filtroTipo, Date inicio, Date fin, int pagina, int limite) throws NegocioException;
    
    public String obtenerContrasenia(int folio) throws NegocioException;
    
    public OperacionDTO obtenerOperacion(int folio) throws NegocioException;
    
    public OperacionDTO guardar(OperacionDTO operacionDTO) throws NegocioException;
    
    public void actualizarRetiroSinCuenta(int folio) throws NegocioException;
}
