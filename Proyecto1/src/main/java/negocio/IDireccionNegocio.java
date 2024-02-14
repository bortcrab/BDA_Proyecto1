/*
 * IDireccionNegocio.java
 */
package negocio;

import dtos.ClienteDTO;
import dtos.DireccionDTO;

/**
 * Interfaz de DireccionNegocio.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public interface IDireccionNegocio {

    /**
     * Busca la dirección asociada a un cliente mediante su identificador.
     *
     * @param idCliente Identificador del cliente del cual se desea buscar la
     * dirección.
     * @return DireccionDTO que representa la dirección encontrada.
     * @throws NegocioException si ocurre un error durante la búsqueda de la
     * dirección.
     */
    public DireccionDTO buscarDireccion(int idCliente) throws NegocioException;

    /**
     * Guarda una nueva dirección en el sistema o actualiza una existente.
     *
     * @param direccionDTO Objeto DireccionDTO que contiene la información de la
     * dirección a guardar o actualizar.
     * @throws NegocioException si ocurre un error durante el proceso de
     * guardado o actualización de la dirección.
     */
    public void guardar(DireccionDTO direccionDTO) throws NegocioException;

    /**
     * Edita la información de una dirección existente en el sistema.
     *
     * @param direccionDTO Objeto DireccionDTO que contiene la información
     * actualizada de la dirección.
     * @throws NegocioException si ocurre un error durante el proceso de edición
     * de la dirección.
     */
    public void editar(DireccionDTO direccionDTO) throws NegocioException;

}
