/*
 * IDireccionDAO.java
 */
package persistencia;

import entidades.DireccionEntidad;

/**
 * Interfaz que define los metodos para la clase que implementa las funciones de
 * acceso a la información de una dirección en la base de datos
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public interface IDireccionDAO {

    /**
     * Realiza la consulta que busca una dirección en la base de datos dado un
     * id de cliente
     *
     * @param idCliente id del cliente que tiene la dirección que se busca
     * @return Dirección obtenida en la consulta a la base de datos
     * @throws PersistenciaException Cuando ocurre un error al realizar la
     * consulta
     */
    public DireccionEntidad buscarDireccion(int idCliente) throws PersistenciaException;

    /**
     * Guarda en la base de datos la información de una cuenta nueva
     *
     * @param direccionEntidad Dirección con la información a guardar en la base
     * de datos
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public void guardar(DireccionEntidad direccionEntidad) throws PersistenciaException;

    /**
     * Edita la información de una cuenta existente en la base de datos
     *
     * @param direccionEntidad Direccion con la información a ingresar a la base
     * de datos de una dirección existente
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public void editar(DireccionEntidad direccionEntidad) throws PersistenciaException;
}
