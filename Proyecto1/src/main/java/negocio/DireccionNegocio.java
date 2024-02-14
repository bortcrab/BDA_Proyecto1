/*
 * DireccionNegocio.java
 */
package negocio;

import dtos.DireccionDTO;
import entidades.DireccionEntidad;
import persistencia.IDireccionDAO;
import persistencia.PersistenciaException;

/**
 * La clase DireccionNegocio implementa la lógica de negocio relacionada con las
 * operaciones de direcciones. Utiliza una instancia de IDireccionDAO para
 * interactuar con la capa de persistencia.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class DireccionNegocio implements IDireccionNegocio {

    private IDireccionDAO direccionDAO;

    /**
     * Constructor de la clase DireccionNegocio.
     *
     * @param direccionDAO el objeto que implementa la interfaz IDireccionDAO
     */
    public DireccionNegocio(IDireccionDAO direccionDAO) {
        this.direccionDAO = direccionDAO;
    }

    /**
     * Busca una dirección por el ID del cliente.
     *
     * @param idCliente el ID del cliente
     * @return un objeto DireccionDTO que representa la dirección encontrada
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public DireccionDTO buscarDireccion(int idCliente) throws NegocioException {
        try {
            DireccionEntidad direccionEntidad = this.direccionDAO.buscarDireccion(idCliente);
            if (direccionEntidad == null) {
                throw new NegocioException("No se encontró la dirección con ese ID de cliente.");
            }
            DireccionDTO direccionDTO = convertirDireccionEntidad_DTO(direccionEntidad);
            return direccionDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Convierte una entidad de dirección a un DTO de dirección.
     *
     * @param direccionEntidad la entidad de dirección a convertir
     * @return un objeto DireccionDTO
     */
    public DireccionDTO convertirDireccionEntidad_DTO(DireccionEntidad direccionEntidad) {
        DireccionDTO direccionDTO = new DireccionDTO(direccionEntidad.getCodigoDireccion(), direccionEntidad.getCodigoPostal(),
                direccionEntidad.getColonia(), direccionEntidad.getCalle(), direccionEntidad.getNumExterior(),
                direccionEntidad.getIdCliente());
        return direccionDTO;
    }

    /**
     * Guarda una dirección en la base de datos.
     *
     * @param direccionDTO el objeto DireccionDTO a guardar
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public void guardar(DireccionDTO direccionDTO) throws NegocioException {
        try {
            DireccionEntidad direccionEntidad = convertirDireccionDTO_Entidad(direccionDTO);
            this.direccionDAO.guardar(direccionEntidad);
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Convierte un DTO de dirección a una entidad de dirección y edita los
     * datos en la base de datos.
     *
     * @param direccionDTO el objeto DireccionDTO a editar
     * @throws NegocioException si ocurre un error en el negocio
     */
    @Override
    public void editar(DireccionDTO direccionDTO) throws NegocioException {
        try {
            DireccionEntidad direccionEntidad = convertirDireccionDTO_Entidad(direccionDTO);
            direccionDAO.editar(direccionEntidad);
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Convierte un DTO de dirección a una entidad de dirección.
     *
     * @param direccionDTO el DTO de dirección a convertir
     * @return la entidad de dirección convertida
     */
    public DireccionEntidad convertirDireccionDTO_Entidad(DireccionDTO direccionDTO) {
        DireccionEntidad direccionEntidad = new DireccionEntidad(direccionDTO.getCodigoDir(), direccionDTO.getCodPos(), direccionDTO.getColonia(),
                direccionDTO.getCalle(), direccionDTO.getNumExt(), direccionDTO.getIdCliente());
        return direccionEntidad;
    }

}
