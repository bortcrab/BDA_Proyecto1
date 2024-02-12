/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.OperacionDTO;
import entidades.OperacionEntidad;
import java.util.ArrayList;
import java.util.List;
import persistencia.IOperacionDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author Usuario
 */
public class OperacionNegocio implements IOperacionNegocio {

    private IOperacionDAO operacionDAO;

    public OperacionNegocio(IOperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO;
    }

    @Override
    public List<OperacionDTO> buscarOperacionesTabla() throws NegocioException {
        try {
            List<OperacionEntidad> operacionesEntidadesLista = this.operacionDAO.buscarOperacionesTabla();
            List<OperacionDTO> operacionesLista = convertirListaOperacionEntidad_DTO(operacionesEntidadesLista);
            if (operacionesLista == null) {
                throw new NegocioException("No ha realizado ninguna operación.");
            }

            return operacionesLista;
        } catch (PersistenciaException pe) {
            // hacer uso de Logger
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    public List<OperacionDTO> convertirListaOperacionEntidad_DTO(List<OperacionEntidad> operacionesEntidadesLista) {
        List<OperacionDTO> operacionesDTOLista = new ArrayList<>();
        for (OperacionEntidad operacionEntidad : operacionesEntidadesLista) {
            OperacionDTO operacionDTO = new OperacionDTO(operacionEntidad.getFolio(), String.valueOf(operacionEntidad.getMonto()), operacionEntidad.getTipo(), operacionEntidad.getFechaHoraEjec().toString(), operacionEntidad.getNumCuentaEmisora());
            operacionesDTOLista.add(operacionDTO);
        }
        return operacionesDTOLista;
    }
    
    public OperacionDTO convertirOperacionEntidad_DTO(OperacionEntidad operacionEntidad) {
        OperacionDTO operacionDTO = new OperacionDTO(operacionEntidad.getFolio(), String.valueOf(operacionEntidad.getMonto()),
                operacionEntidad.getTipo(), operacionEntidad.getFechaHoraEjec().toString(), operacionEntidad.getNumCuentaEmisora());
        return operacionDTO;
    }

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
    
    public OperacionEntidad convertirOperacionDTO_Entidad(OperacionDTO operacionDTO) {
        OperacionEntidad operacionEntidad = new OperacionEntidad(operacionDTO.getFolio(), Float.parseFloat(operacionDTO.getMonto()),
                operacionDTO.getTipo(), java.sql.Date.valueOf(operacionDTO.getFechaHora()), operacionDTO.getNumCuentaOrigen());
        return operacionEntidad;
    }
}
