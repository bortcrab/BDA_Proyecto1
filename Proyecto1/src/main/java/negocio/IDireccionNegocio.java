/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.ClienteDTO;
import dtos.DireccionDTO;

/**
 *
 * @author jorge
 */
public interface IDireccionNegocio {
    public int buscarPorId(int id) throws NegocioException;

    public void guardar(DireccionDTO direccionDTO) throws NegocioException;

    public void editar(DireccionDTO direccionDTO) throws NegocioException;
}
