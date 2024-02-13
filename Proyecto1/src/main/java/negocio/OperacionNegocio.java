/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 *
 * @author Usuario
 */
public class OperacionNegocio implements IOperacionNegocio {
    private IOperacionDAO operacionDAO;

    public OperacionNegocio(IOperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO;
    }

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
                throw new NegocioException("No ha realizado ninguna operación.");
            }
            List<OperacionDTO> operacionesLista = convertirListaOperacionEntidad_DTO(operacionesEntidadesLista);

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
            OperacionDTO operacionDTO = convertirOperacionesTabla(operacionEntidad);
            operacionesDTOLista.add(operacionDTO);
        }
        return operacionesDTOLista;
    }
    
    public OperacionDTO convertirOperacionesTabla(OperacionEntidad operacionEntidad) {
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
    
    public OperacionDTO convertirOperacionEntidad_DTO(OperacionEntidad operacionEntidad) {
        OperacionDTO operacionDTO = new OperacionDTO(
                operacionEntidad.getFolio(),
                String.valueOf(operacionEntidad.getMonto()),
                operacionEntidad.getTipo(),
                operacionEntidad.getNumCuentaEmisora());
        return operacionDTO;
    }
    
    public OperacionEntidad convertirOperacionDTO_Entidad(OperacionDTO operacionDTO) {
        OperacionEntidad operacionEntidad = new OperacionEntidad(
                Float.parseFloat(operacionDTO.getMonto()),
                operacionDTO.getTipo(),
                operacionDTO.getNumCuentaOrigen(),
                operacionDTO.getNumCuentaDestino());
        return operacionEntidad;
    }

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

    @Override
    public void actualizarRetiroSinCuenta(int folio) throws NegocioException {
        try {
            operacionDAO.actualizarRetiroSinCuenta(folio);
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }
    
    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }
}
