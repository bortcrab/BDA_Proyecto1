/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.CuentaDTO;
import entidades.CuentaEntidad;
import enumeradores.AccionCatalogoEnumerador;
import static enumeradores.AccionCatalogoEnumerador.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ICuentaDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author bortc
 */
public class CuentaNegocio implements ICuentaNegocio {

    private ICuentaDAO cuentaDAO;

    public CuentaNegocio(ICuentaDAO cuentaDAO) {
        this.cuentaDAO = cuentaDAO;
    }

    @Override
    public List<CuentaDTO> obtenerCuentas(int idCliente) throws NegocioException {
        try {
            List<CuentaEntidad> listaCuentasEntidad = cuentaDAO.obtenerCuentas(idCliente);
            if (listaCuentasEntidad.isEmpty()) {
                throw new NegocioException("No tiene ninguna cuenta.");
            }
            List<CuentaDTO> listaCuentasDTO = new ArrayList<>();
            for (CuentaEntidad cuentaEntidad : listaCuentasEntidad) {
                listaCuentasDTO.add(convertirCuentaEntidad_DTO(cuentaEntidad));
            }
            
            return listaCuentasDTO;
        } catch (PersistenciaException pe) {
            // hacer uso de Logger
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    public CuentaDTO convertirCuentaEntidad_DTO(CuentaEntidad cuentaEntidad) {
        CuentaDTO cuentaDTO = new CuentaDTO(cuentaEntidad.getNumCuenta(), String.valueOf(cuentaEntidad.getSaldo()),
                cuentaEntidad.getFechaApertura().toString(), cuentaEntidad.getIdCliente());
        return cuentaDTO;
    }
    
    @Override
    public CuentaDTO buscarCuenta(int numCuenta) throws NegocioException {
        try {
            CuentaEntidad cuentaEntidad = cuentaDAO.buscarCuenta(numCuenta);
            CuentaDTO cuentaDTO = convertirCuentaEntidad_DTO(cuentaEntidad);
            return cuentaDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }
    
    @Override
    public CuentaDTO buscarCuenta(int folio, String contrasenia) throws NegocioException {
        try {
            CuentaEntidad cuentaEntidad = cuentaDAO.buscarCuenta(folio, contrasenia);
            CuentaDTO cuentaDTO = convertirCuentaEntidad_DTO(cuentaEntidad);
            return cuentaDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }
    
    @Override
    public CuentaDTO guardar(CuentaDTO cuentaDTO) throws NegocioException {
        try {
            CuentaEntidad cuentaEntidad = convertirCuentaDTO_Entidad(cuentaDTO);
            cuentaDAO.guardar(cuentaEntidad);
            cuentaDTO = convertirCuentaEntidad_DTO(cuentaEntidad);
            return cuentaDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    public CuentaEntidad convertirCuentaDTO_Entidad(CuentaDTO cuentaDTO) {
        CuentaEntidad cuentaEntidad = new CuentaEntidad(cuentaDTO.getNumCuenta(), Float.parseFloat(cuentaDTO.getSaldo()),
                new Date(0), false, cuentaDTO.getIdCliente());
        return cuentaEntidad;
    }
    
    @Override
    public void editar(CuentaDTO cuentaDTO, AccionCatalogoEnumerador accion) throws NegocioException {
        try {
            CuentaEntidad cuentaEntidad = convertirCuentaDTO_Entidad(cuentaDTO);
            if (accion == DEPOSITO) {
                cuentaDAO.editar(cuentaEntidad, DEPOSITO);
            } else {
                cuentaDAO.editar(cuentaEntidad, RETIRO);
            }
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    @Override
    public void borrar(CuentaDTO cuentaDTO) throws NegocioException {
        try {
            CuentaEntidad cuentaEntidad = convertirCuentaDTO_Entidad(cuentaDTO);
            cuentaDAO.borrar(cuentaEntidad);
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    @Override
    public void transferir(int numCuentaOrigen, int numCuentaDestino, float monto) throws NegocioException {
        CuentaDTO cuentaOrigenDTO;
        CuentaDTO cuentaDestinoDTO;
        try {
            cuentaOrigenDTO = buscarCuenta(numCuentaOrigen);
        } catch (NegocioException pe) {
            throw new NegocioException("La cuenta de origen no existe.");
        }
        
        try {
            cuentaDestinoDTO = buscarCuenta(numCuentaDestino);
        } catch (NegocioException pe) {
            throw new NegocioException("La cuenta de destino no existe.");
        }
        
        if (cuentaOrigenDTO.getIdCliente() == cuentaDestinoDTO.getIdCliente()) {
            throw new NegocioException("La cuenta de origen y destino no pueden ser las mismas.");
        }
        
        if (monto > Float.parseFloat(cuentaOrigenDTO.getSaldo())) {
            throw new NegocioException("No cuenta con los fondos suficientes para realizar la transferencia.");
        }
        
        try {
            cuentaDAO.transferir(numCuentaOrigen, numCuentaDestino, monto);
        } catch (PersistenciaException pe) {
            throw new NegocioException(pe.getMessage());
        }
    }
}
