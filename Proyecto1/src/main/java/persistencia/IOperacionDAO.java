/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidades.OperacionEntidad;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IOperacionDAO {
    public List<OperacionEntidad> buscarOperacionesTabla(int idCliente) throws PersistenciaException;
    
    public OperacionEntidad guardar(OperacionEntidad operacionEntidad) throws PersistenciaException;
}
