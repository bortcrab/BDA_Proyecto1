/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.DireccionDTO;
import entidades.DireccionEntidad;
import persistencia.IDireccionDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author jorge
 */
public class DireccionNegocio implements IDireccionNegocio {
    private IDireccionDAO direccionDAO;

    public DireccionNegocio(IDireccionDAO direccionDAO) {
        this.direccionDAO = direccionDAO;
    }
    
    @Override
    public DireccionDTO buscarDireccion(int idCliente) throws NegocioException {
        try {
            DireccionEntidad direccionEntidad = this.direccionDAO.buscarDireccion(idCliente);
            if (direccionEntidad == null) {
                throw new NegocioException("No se encontró el direccion con esa ID.");
            }
            DireccionDTO direccionDTO = convertirDireccionEntidad_DTO(direccionEntidad);
            return direccionDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }
    
    public DireccionDTO convertirDireccionEntidad_DTO(DireccionEntidad direccionEntidad) {
        DireccionDTO clienteDTO = new DireccionDTO(direccionEntidad.getCodigoDireccion(), direccionEntidad.getCodigoPostal(),
                direccionEntidad.getColonia(), direccionEntidad.getCalle(), direccionEntidad.getNumExterior(),
                direccionEntidad.getIdCliente());
        return clienteDTO;
    }

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
    
    public DireccionEntidad convertirDireccionDTO_Entidad(DireccionDTO direccionDTO) {
        DireccionEntidad direccionEntidad = new DireccionEntidad(direccionDTO.getCodigoDir(), direccionDTO.getCodPos(), direccionDTO.getColonia(),
                direccionDTO.getCalle(), direccionDTO.getNumExt(), direccionDTO.getIdCliente());
        return direccionEntidad;
    }

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
    
}
