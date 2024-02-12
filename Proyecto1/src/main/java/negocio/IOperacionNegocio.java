/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.OperacionDTO;
import java.util.List;
import persistencia.OperacionDAO;

/**
 *
 * @author Usuario
 */
public interface IOperacionNegocio {
    public List<OperacionDTO> buscarOperacionesTabla() throws NegocioException;
    
    public OperacionDTO guardar(OperacionDTO operacionDTO) throws NegocioException;
}
