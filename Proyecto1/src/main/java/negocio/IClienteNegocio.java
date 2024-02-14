/*
 * IClienteNegocio.java
 */
package negocio;

import dtos.ClienteDTO;

/**
 * Interfaz de ClienteNegocio.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public interface IClienteNegocio {
    
    /**
     * Busca un cliente en función de la información proporcionada en el objeto ClienteDTO.
     * @param clienteDTO Objeto ClienteDTO que contiene la información del cliente a buscar.
     * @return ClienteDTO que representa el cliente encontrado.
     * @throws NegocioException si ocurre un error durante la búsqueda del cliente.
     */
    public ClienteDTO buscarCliente(ClienteDTO clienteDTO) throws NegocioException;

    /**
     * Guarda un cliente en el sistema.
     * @param clienteDTO Objeto ClienteDTO que contiene la información del cliente a guardar.
     * @return ClienteDTO que representa el cliente guardado.
     * @throws NegocioException si ocurre un error durante el proceso de guardado del cliente.
     */
    public ClienteDTO guardar(ClienteDTO clienteDTO) throws NegocioException;

    /**
     * Edita la información de un cliente existente en el sistema.
     * @param clienteDTO Objeto ClienteDTO que contiene la información actualizada del cliente.
     * @throws NegocioException si ocurre un error durante el proceso de edición del cliente.
     */
    public void editar(ClienteDTO clienteDTO) throws NegocioException;
    
    /**
     * Obtiene la contraseña asociada a un cliente mediante su identificador.
     * @param idCliente Identificador del cliente del cual se desea obtener la contraseña.
     * @return La contraseña asociada al cliente.
     * @throws NegocioException si ocurre un error durante la obtención de la contraseña.
     */
    public String obtenerContrasenia(int idCliente) throws NegocioException;
}