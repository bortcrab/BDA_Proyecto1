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
            List<OperacionDTO> operacionesLista = this.operacionDTO(operacionesEntidadesLista);
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
    
    public List<OperacionDTO> operacionDTO(List<OperacionEntidad> operacionesEntidadesLista) {
        List<OperacionDTO> operacionesDTOLista = new ArrayList<>();
        for (OperacionEntidad operacionEntidad : operacionesEntidadesLista) {
            OperacionDTO operacionDTO = new OperacionDTO(operacionEntidad.getFolio(), operacionEntidad.getTipo(), String.valueOf(operacionEntidad.getMonto()), String.valueOf(operacionEntidad.fechaHoraEjec()));
            operacionesDTOLista.add(operacionDTO);
        }
        return operacionesDTOLista;
    }
}
