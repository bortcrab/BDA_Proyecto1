/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dtos.OperacionDTO;
import entidades.OperacionEntidad;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IOperacionDAO {
    public List<OperacionEntidad> buscarOperacionesTabla(int idCliente, String filtroTipo, Date inicio, Date fin, int limite, int offset) throws PersistenciaException;
    
    public String obtenerContrasenia(int folio) throws PersistenciaException;
    
    public OperacionEntidad obtenerOperacion(int folio) throws PersistenciaException;
    
    public OperacionEntidad guardar(OperacionEntidad operacionEntidad) throws PersistenciaException;
    
    public void actualizarRetiroSinCuenta(int folio) throws PersistenciaException;
}
