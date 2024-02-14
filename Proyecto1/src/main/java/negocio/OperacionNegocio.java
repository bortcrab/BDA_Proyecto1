/*
 * OperacionNegocio.java
 */
package negocio;

import dtos.OperacionDTO;
import entidades.OperacionEntidad;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import persistencia.IOperacionDAO;
import persistencia.PersistenciaException;
import utilerias.Utilidades;

/**
 * La clase OperacionNegocio implementa la lógica de negocio relacionada con el
 * manejo de las operaciones. Utiliza una instancia de IOperacionDAO para
 * interactuar con la capa de persistencia.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class OperacionNegocio implements IOperacionNegocio {

    private IOperacionDAO operacionDAO;

    /**
     * Constructor de la clase OperacionNegocio.
     *
     * @param operacionDAO el objeto que implementa la interfaz IOperacionDAO
     */
    public OperacionNegocio(IOperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO;
    }

    /**
     * Busca operaciones en una tabla por criterios específicos.
     *
     * @param idCliente el ID del cliente
     * @param filtroTipo el tipo de operación a filtrar
     * @param inicio la fecha de inicio del período a considerar
     * @param fin la fecha de fin del período a considerar
     * @param pagina el número de página de resultados
     * @param limite el número máximo de resultados por página
     * @return una lista de objetos OperacionDTO
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public List<OperacionDTO> buscarOperacionesTabla(int idCliente, String filtroTipo, Date inicio, Date fin, int pagina, int limite) throws NegocioException {
        try {
            if (limite < 0) {
                throw new NegocioException("El parámetro límite no puede ser negativo.");
            }
            if (pagina < 0) {
                throw new NegocioException("El parámetro página no puede ser negativo.");
            }
            int offset = obtenerOFFSETMySQL(limite, pagina);
            List<OperacionEntidad> operacionesEntidadesLista = operacionDAO.buscarOperacionesTabla(idCliente, filtroTipo, inicio, fin, limite, offset);
            if (operacionesEntidadesLista == null) {
                throw new NegocioException("No se encontraron operaciones.");
            }
            List<OperacionDTO> operacionesLista = convertirListaOperacionEntidad_DTO(operacionesEntidadesLista);

            return operacionesLista;
        } catch (PersistenciaException pe) {
            // hacer uso de Logger
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Convierte una lista de entidades de operación a una lista de DTO de
     * operación.
     *
     * @param operacionesEntidadesLista la lista de entidades de operación a
     * convertir
     * @return una lista de objetos OperacionDTO
     */
    public List<OperacionDTO> convertirListaOperacionEntidad_DTO(List<OperacionEntidad> operacionesEntidadesLista) {
        List<OperacionDTO> operacionesDTOLista = new ArrayList<>();
        for (OperacionEntidad operacionEntidad : operacionesEntidadesLista) {
            OperacionDTO operacionDTO = convertirOperacionTabla(operacionEntidad);
            operacionesDTOLista.add(operacionDTO);
        }
        return operacionesDTOLista;
    }

    /**
     * Convierte una entidad de operación a un DTO de operación.
     *
     * @param operacionEntidad la entidad de operación a convertir
     * @return un objeto OperacionDTO
     */
    public OperacionDTO convertirOperacionTabla(OperacionEntidad operacionEntidad) {
        OperacionDTO operacionDTO = new OperacionDTO(
                operacionEntidad.getFolio(),
                String.valueOf(operacionEntidad.getMonto()),
                operacionEntidad.getTipo(),
                operacionEntidad.getFechaHoraEjec().toString(),
                operacionEntidad.getNumCuentaEmisora(),
                operacionEntidad.getNumCuentaReceptora(),
                operacionEntidad.getEstado(),
                operacionEntidad.getFechaHoraCobro());
        return operacionDTO;
    }

    /**
     * Guarda una operación en la base de datos.
     *
     * @param operacionDTO el objeto OperacionDTO a guardar
     * @return el objeto OperacionDTO guardado
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public OperacionDTO guardar(OperacionDTO operacionDTO) throws NegocioException {
        try {
            OperacionEntidad operacionEntidad = convertirOperacionDTO_Entidad(operacionDTO);
            operacionDAO.guardar(operacionEntidad);
            operacionDTO = convertirOperacionEntidad_DTO(operacionEntidad);
            return operacionDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Convierte una entidad de operación a un DTO de operación.
     *
     * @param operacionEntidad la entidad de operación a convertir
     * @return un objeto OperacionDTO
     */
    public OperacionDTO convertirOperacionEntidad_DTO(OperacionEntidad operacionEntidad) {
        OperacionDTO operacionDTO = new OperacionDTO(
                operacionEntidad.getFolio(),
                String.valueOf(operacionEntidad.getMonto()),
                operacionEntidad.getTipo(),
                operacionEntidad.getNumCuentaEmisora());
        return operacionDTO;
    }

    /**
     * Convierte un DTO de operación a una entidad de operación.
     *
     * @param operacionDTO el DTO de operación a convertir
     * @return la entidad de operación convertida
     */
    public OperacionEntidad convertirOperacionDTO_Entidad(OperacionDTO operacionDTO) {
        OperacionEntidad operacionEntidad = new OperacionEntidad(
                Float.parseFloat(operacionDTO.getMonto()),
                operacionDTO.getTipo(),
                operacionDTO.getNumCuentaOrigen(),
                operacionDTO.getNumCuentaDestino());
        return operacionEntidad;
    }

    /**
     * Obtiene la contraseña asociada a una operación.
     *
     * @param folio el folio de la operación
     * @return la contraseña asociada a la operación
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public String obtenerContrasenia(int folio) throws NegocioException {
        try {
            String contrasenia = operacionDAO.obtenerContrasenia(folio);
            return contrasenia;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Obtiene una operación por su folio.
     *
     * @param folio el folio de la operación
     * @return un objeto OperacionDTO que representa la operación encontrada
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public OperacionDTO obtenerOperacion(int folio) throws NegocioException {
        try {
            OperacionEntidad operacionEntidad = operacionDAO.obtenerOperacion(folio);
            OperacionDTO operacionDTO = convertirOperacionEntidad_DTO(operacionEntidad);
            return operacionDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Actualiza el estado de un retiro sin cuenta.
     *
     * @param folio el folio del retiro sin cuenta a actualizar
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public void actualizarRetiroSinCuenta(int folio) throws NegocioException {
        try {
            operacionDAO.actualizarRetiroSinCuenta(folio);
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Obtiene el offset de una consulta paginada para MySQL.
     *
     * @param limit el límite de resultados por página
     * @param pagina el número de página
     * @return el valor de offset correspondiente
     */
    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }
}
