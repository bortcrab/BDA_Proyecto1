/*
 * CuentaNegocio.java
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
 * La clase CuentaNegocio implementa la lógica de negocio relacionada con las
 * operaciones de cuentas. Utiliza una instancia de ICuentaDAO para interactuar
 * con la capa de persistencia.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class CuentaNegocio implements ICuentaNegocio {

    private ICuentaDAO cuentaDAO;

    /**
     * Constructor de la clase CuentaNegocio.
     *
     * @param cuentaDAO el objeto que implementa la interfaz ICuentaDAO
     */
    public CuentaNegocio(ICuentaDAO cuentaDAO) {
        this.cuentaDAO = cuentaDAO;
    }

    /**
     * Obtiene una lista de cuentas asociadas a un cliente dado su ID.
     *
     * @param idCliente el ID del cliente
     * @return una lista de objetos CuentaDTO
     * @throws NegocioException si ocurre un error en el negocio
     */
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

    /**
     * Convierte una entidad de cuenta a un DTO de cuenta.
     *
     * @param cuentaEntidad la entidad de cuenta a convertir
     * @return un objeto CuentaDTO
     */
    public CuentaDTO convertirCuentaEntidad_DTO(CuentaEntidad cuentaEntidad) {
        CuentaDTO cuentaDTO = new CuentaDTO(cuentaEntidad.getNumCuenta(), String.valueOf(cuentaEntidad.getSaldo()),
                cuentaEntidad.getFechaApertura().toString(), cuentaEntidad.getIdCliente());
        return cuentaDTO;
    }

    /**
     * Busca una cuenta por su número de cuenta.
     *
     * @param numCuenta el número de cuenta
     * @return un objeto CuentaDTO
     * @throws NegocioException si ocurre un error en el negocio
     */
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

    /**
     * Busca una cuenta por su folio y contraseña.
     *
     * @param folio el folio de la cuenta
     * @param contrasenia la contraseña de la cuenta
     * @return un objeto CuentaDTO
     * @throws NegocioException si ocurre un error en el negocio
     */
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

    /**
     * Guarda una cuenta en la base de datos.
     *
     * @param cuentaDTO el objeto CuentaDTO a guardar
     * @return el objeto CuentaDTO guardado
     * @throws NegocioException si ocurre un error en el negocio
     */
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

    /**
     * Convierte un DTO de cuenta a una entidad de cuenta.
     *
     * @param cuentaDTO el DTO de cuenta a convertir
     * @return la entidad de cuenta convertida
     */
    public CuentaEntidad convertirCuentaDTO_Entidad(CuentaDTO cuentaDTO) {
        CuentaEntidad cuentaEntidad = new CuentaEntidad(cuentaDTO.getNumCuenta(), Float.parseFloat(cuentaDTO.getSaldo()),
                new Date(0), false, cuentaDTO.getIdCliente());
        return cuentaEntidad;
    }

    /**
     * Método para depositar o retirar un monto de una cuenta.
     *
     * @param cuentaDTO Objeto que contiene todos los datos de la cuenta.
     * @param accion Indica la acción a realizar (retiro o depósito)
     * @throws NegocioException si ocurre un error durante la actualización de
     * la cuenta.
     */
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

    /**
     * Cancela una cuenta.
     *
     * @param cuentaDTO Objeto que contiene la información de la cuenta a
     * borrar.
     * @throws NegocioException si ocurre un error durante la cancelación de la
     * cuenta.
     */
    @Override
    public void borrar(CuentaDTO cuentaDTO) throws NegocioException {
        try {
            CuentaEntidad cuentaEntidad = convertirCuentaDTO_Entidad(cuentaDTO);
            cuentaDAO.eliminar(cuentaEntidad);
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Transfiere de una cuenta de origen a una de destino.
     *
     * @param numCuentaOrigen Número de la cuenta origen.
     * @param numCuentaDestino Número de la cuenta destino.
     * @param monto Monto de la transferencia.
     * @throws NegocioException si ocurre un error durante la transferencia.
     */
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

        if (cuentaOrigenDTO.getNumCuenta()== cuentaDestinoDTO.getNumCuenta()) {
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
