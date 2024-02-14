/*
 * IOperacionDAO.java
 */
package persistencia;

import entidades.OperacionEntidad;
import java.sql.Date;
import java.util.List;

/**
 * Interfaz que define los metodos para la clase que implementa las funciones de
 * acceso a la información de una operación en la base de datos
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public interface IOperacionDAO {

    /**
     * Obtiene las operaciones realizadas por el cliente con el id dado por el
     * parametro
     *
     * @param idCliente id del cliente del cual se desea obtener sus operaciones
     * @param filtroTipo
     * @param inicio
     * @param fin
     * @param limite
     * @param offset
     * @return Una lista con las operaciones del cliente cuyo id coincide con el
     * del parametro
     * @throws PersistenciaException Si ocurre un error al obtener la
     * información de las operaciones de la base de datos
     */
    public List<OperacionEntidad> buscarOperacionesTabla(int idCliente, String filtroTipo, Date inicio, Date fin, int limite, int offset) throws PersistenciaException;
    
    public String obtenerContrasenia(int folio) throws PersistenciaException;

    public OperacionEntidad obtenerOperacion(int folio) throws PersistenciaException;

    /**
     * Guarda en la base de datos la información de una operacion nueva
     *
     * @param operacionEntidad Operacion con la información a guardar en la base
     * de datos
     * @return Operación con la información guardada
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public OperacionEntidad guardar(OperacionEntidad operacionEntidad) throws PersistenciaException;

    public void actualizarRetiroSinCuenta(int folio) throws PersistenciaException;
}
