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
    public int buscarPorId(int id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        DireccionEntidad direccionEntidad = new DireccionEntidad(direccionDTO.getCodPos(), direccionDTO.getColonia(),
                direccionDTO.getCalle(), direccionDTO.getNumExt(), direccionDTO.getIdCliente());
        return direccionEntidad;
    }

    @Override
    public void editar(DireccionDTO t) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
