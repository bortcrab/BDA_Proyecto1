/*
 * IClienteDAO.java
 */
package persistencia;

import entidades.ClienteEntidad;

/**
 * Interfaz que define los metodos para la clase que implementa las funciones de
 * acceso a la información de un cliente en la base de datos
 *
 * @author jorge
 */
public interface IClienteDAO {

    /**
     * Realiza la consulta que busca un cliente en la base de datos dado un
     * objeto de ClienteEntidad
     *
     * @param clienteEntidad Objeto que representa el cliente que se desea
     * buscar en la base de datos
     * @return Cliente obtenido en la consulta a la base de datos
     * @throws PersistenciaException Cuando ocurre un error al realizar la
     * consulta
     */
    public ClienteEntidad buscarCliente(ClienteEntidad clienteEntidad) throws PersistenciaException;

    /**
     * Guarda en la base de datos la información de un cliente nuevo
     *
     * @param clienteEntidad Cliente con la información a guardar en la base de
     * datos
     * @return Cliente con la información guardada
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public ClienteEntidad guardar(ClienteEntidad clienteEntidad) throws PersistenciaException;

    /**
     * Edita la información de un cliente existente en la base de datos
     *
     * @param clienteEntidad Cliente con la información a ingresar a la base de
     * datos de un cliente existente
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public void editar(ClienteEntidad clienteEntidad) throws PersistenciaException;
    
    public String obtenerContrasenia(int idCliente) throws PersistenciaException;
}
