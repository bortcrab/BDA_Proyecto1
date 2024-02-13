/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidades.ClienteEntidad;

/**
 *
 * @author jorge
 */
public interface IClienteDAO {
    public ClienteEntidad buscarCliente(ClienteEntidad clienteEntidad) throws PersistenciaException;

    public ClienteEntidad guardar(ClienteEntidad clienteEntidad) throws PersistenciaException;

    public void editar(ClienteEntidad clienteEntidad) throws PersistenciaException;
    
    public String obtenerContrasenia(int idCliente) throws PersistenciaException;
}
