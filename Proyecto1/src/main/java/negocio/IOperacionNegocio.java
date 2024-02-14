/*
 * IOperacionNegocio.java
 */
package negocio;

import dtos.OperacionDTO;
import java.sql.Date;
import java.util.List;

/**
 * Interfaz de OperacionNegocio.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public interface IOperacionNegocio {

    /**
     * Busca operaciones las operaciones que irán en la tabla.
     *
     * @param idCliente Identificador del cliente del cual se desean buscar las
     * operaciones.
     * @param filtroTipo Tipo de operación a filtrar (opcional).
     * @param inicio Fecha de inicio del rango de tiempo de las operaciones a
     * buscar (opcional).
     * @param fin Fecha de fin del rango de tiempo de las operaciones a buscar
     * (opcional).
     * @param pagina Número de página de resultados (para paginación).
     * @param limite Límite de resultados por página (para paginación).
     * @return Lista de objetos OperacionDTO que representan las operaciones
     * encontradas según los criterios especificados.
     * @throws NegocioException si ocurre un error durante la búsqueda de las
     * operaciones.
     */
    public List<OperacionDTO> buscarOperacionesTabla(int idCliente, String filtroTipo, Date inicio, Date fin, int pagina, int limite) throws NegocioException;

    /**
     * Obtiene la contraseña asociada a un retiro sin cuenta mediante su folio.
     *
     * @param folio Folio de la operación de la cual se desea obtener la
     * contraseña.
     * @return La contraseña asociada a el retiro sin cuenta.
     * @throws NegocioException si ocurre un error durante la obtención de la
     * contraseña.
     */
    public String obtenerContrasenia(int folio) throws NegocioException;

    /**
     * Obtiene los detalles de una operación mediante su folio.
     *
     * @param folio Folio de la operación de la cual se desean obtener los
     * detalles.
     * @return OperacionDTO que representa los detalles de la operación.
     * @throws NegocioException si ocurre un error durante la obtención de la
     * operación.
     */
    public OperacionDTO obtenerOperacion(int folio) throws NegocioException;

    /**
     * Guarda una nueva operación en el sistema o actualiza una existente.
     *
     * @param operacionDTO Objeto OperacionDTO que contiene la información de la
     * operación a guardar o actualizar.
     * @return OperacionDTO que representa la operación guardada o actualizada.
     * @throws NegocioException si ocurre un error durante el proceso de
     * guardado o actualización de la operación.
     */
    public OperacionDTO guardar(OperacionDTO operacionDTO) throws NegocioException;

    /**
     * Actualiza un retiro sin cuenta.
     *
     * @param folio Folio del retiro sin cuenta a actualizar.
     * @throws NegocioException si ocurre un error durante el proceso de
     * actualización.
     */
    public void actualizarRetiroSinCuenta(int folio) throws NegocioException;

}
