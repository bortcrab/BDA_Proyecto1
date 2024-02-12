/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidades.DireccionEntidad;

/**
 *
 * @author jorge
 */
public interface IDireccionDAO {
    public DireccionEntidad buscarDireccion(int idCliente) throws PersistenciaException;

    public void guardar(DireccionEntidad direccionEntidad) throws PersistenciaException;

    public void editar(DireccionEntidad direccionEntidad) throws PersistenciaException;
}
