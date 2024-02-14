/*
 * ICuentaDAO.java
 */
package persistencia;

import entidades.CuentaEntidad;
import enumeradores.AccionCatalogoEnumerador;
import java.util.List;

/**
 * Interfaz que define los metodos para la clase que implementa las funciones de
 * acceso a la información de una cuenta en la base de datos
 *
 * @author bortc
 */
public interface ICuentaDAO {

    /**
     * Obtiene las cuentas que estan a nombre del cliente con el id dado por el
     * parametro
     *
     * @param idCliente id del cliente del cual se desea obtener sus cuentas
     * @return Una lista con las cuentas del cliente cuyo id coincide con el del
     * parametro
     * @throws PersistenciaException Si ocurre un error al obtener la
     * información de las cuentas de la base de datos
     */
    public List<CuentaEntidad> obtenerCuentas(int idCliente) throws PersistenciaException;

    /**
     * Realiza la consulta que busca una cuenta en la base de datos dado un
     * número de cuenta
     *
     * @param numCuenta Número de la cuenta a buscar en la base de datos
     * @return Cuenta obtenida en la consulta a la base de datos
     * @throws PersistenciaException Cuando ocurre un error al realizar la
     * consulta
     */
    public CuentaEntidad buscarCuenta(int numCuenta) throws PersistenciaException;

    /**
     * Realiza la consulta que busca una cuenta en la base de datos dado un
     * número de cuenta
     *
     * @param folio
     * @param contrasenia
     * @return Cuenta obtenida en la consulta a la base de datos
     * @throws PersistenciaException Cuando ocurre un error al realizar la
     * consulta
     */
    public CuentaEntidad buscarCuenta(int folio, String contrasenia) throws PersistenciaException;

    /**
     * Guarda en la base de datos la información de una cuenta nueva
     *
     * @param cuentaEntidad Cuenta con la información a guardar en la base de
     * datos
     * @return Cuenta con la información guardada
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public CuentaEntidad guardar(CuentaEntidad cuentaEntidad) throws PersistenciaException;

    /**
     * Edita la información de una cuenta existente en la base de datos
     *
     * @param cuentaEntidad Cuenta con la información a ingresar a la base de
     * datos de una cuenta existente
     * @param accion Tipo de edición a realizar
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public void editar(CuentaEntidad cuentaEntidad, AccionCatalogoEnumerador accion) throws PersistenciaException;

    /**
     * Realiza una transferencia entre 2 cuentas
     *
     * @param numCuentaOrigen Numero de cuenta de origen de la transacción
     * @param numCuentaDestino Numero de cuenta de Destino de la transacción
     * @param monto Monto a transferir
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    public void transferir(int numCuentaOrigen, int numCuentaDestino, float monto) throws PersistenciaException;

    /**
     * Elimina una cuenta asignando estatus de eliminada en la base de datos
     *
     * @param cuentaEntidad Cuenta a eliminar en la base de datos
     * @throws PersistenciaException Si ocurre un error al actualizar el estatus
     * de la cuenta en la base de datos
     */
    public void eliminar(CuentaEntidad cuentaEntidad) throws PersistenciaException;
}
