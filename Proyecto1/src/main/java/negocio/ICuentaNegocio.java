/*
 * ICuentaNegocio.java
 */
package negocio;

import dtos.CuentaDTO;
import enumeradores.AccionCatalogoEnumerador;
import java.util.List;

/**
 * Interfaz de CuentaNegocio.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public interface ICuentaNegocio {

    /**
     * Obtiene todas las cuentas asociadas a un cliente dado su identificador.
     *
     * @param idCliente Identificador del cliente del cual se desean obtener las
     * cuentas.
     * @return Lista de objetos CuentaDTO que representan las cuentas asociadas
     * al cliente.
     * @throws NegocioException si ocurre un error durante la obtención de las
     * cuentas.
     */
    public List<CuentaDTO> obtenerCuentas(int idCliente) throws NegocioException;

    /**
     * Busca una cuenta en el sistema mediante su número de cuenta.
     *
     * @param numCuenta Número de cuenta de la cuenta a buscar.
     * @return CuentaDTO que representa la cuenta encontrada.
     * @throws NegocioException si ocurre un error durante la búsqueda de la
     * cuenta.
     */
    public CuentaDTO buscarCuenta(int numCuenta) throws NegocioException;

    /**
     * Busca una cuenta en el sistema mediante su folio y contraseña asociada.
     *
     * @param folio Folio de la cuenta a buscar.
     * @param contrasenia Contraseña asociada a la cuenta.
     * @return CuentaDTO que representa la cuenta encontrada.
     * @throws NegocioException si ocurre un error durante la búsqueda de la
     * cuenta.
     */
    public CuentaDTO buscarCuenta(int folio, String contrasenia) throws NegocioException;

    /**
     * Guarda una nueva cuenta en el sistema o actualiza una existente.
     *
     * @param cuentaDTO Objeto CuentaDTO que contiene la información de la
     * cuenta a guardar o actualizar.
     * @return CuentaDTO que representa la cuenta guardada o actualizada.
     * @throws NegocioException si ocurre un error durante el proceso de
     * guardado o actualización de la cuenta.
     */
    public CuentaDTO guardar(CuentaDTO cuentaDTO) throws NegocioException;

    /**
     * Edita la información de una cuenta existente en el sistema.
     *
     * @param cuentaDTO Objeto CuentaDTO que contiene la información actualizada
     * de la cuenta.
     * @param accion Acción a realizar sobre la cuenta (puede ser actualización,
     * eliminación, etc.).
     * @throws NegocioException si ocurre un error durante el proceso de edición
     * de la cuenta.
     */
    public void editar(CuentaDTO cuentaDTO, AccionCatalogoEnumerador accion) throws NegocioException;

    /**
     * Borra una cuenta del sistema.
     *
     * @param cuentaDTO Objeto CuentaDTO que representa la cuenta a borrar.
     * @throws NegocioException si ocurre un error durante el proceso de borrado
     * de la cuenta.
     */
    public void borrar(CuentaDTO cuentaDTO) throws NegocioException;

    /**
     * Transfiere un monto específico de una cuenta origen a una cuenta destino.
     *
     * @param numCuentaOrigen Número de cuenta de origen.
     * @param numCuentaDestino Número de cuenta de destino.
     * @param monto Monto a transferir.
     * @throws NegocioException si ocurre un error durante el proceso de
     * transferencia.
     */
    public void transferir(int numCuentaOrigen, int numCuentaDestino, float monto) throws NegocioException;

}
