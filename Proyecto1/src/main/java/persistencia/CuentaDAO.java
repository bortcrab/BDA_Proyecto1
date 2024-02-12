/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.CuentaEntidad;
import enumeradores.AccionCatalogoEnumerador;
import static enumeradores.AccionCatalogoEnumerador.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bortc
 */
public class CuentaDAO implements ICuentaDAO {

    public IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(CuentaDAO.class.getName());

    public CuentaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<CuentaEntidad> obtenerCuentas(int idCliente) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            List<CuentaEntidad> listaCuentasEntidad = new ArrayList<>();
            String codigoSQL = "SELECT * FROM Cuentas WHERE idCliente = " + idCliente + " AND estaEliminado = 0;";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
                CuentaEntidad cuentaEntidad = new CuentaEntidad();
                cuentaEntidad.setNumCuenta(resultado.getInt("numCuenta"));
                cuentaEntidad.setSaldo(resultado.getFloat("saldo"));
                cuentaEntidad.setFechaApertura(resultado.getDate("fechaApertura"));
                cuentaEntidad.setEstaEliminado(resultado.getBoolean("estaEliminado"));
                cuentaEntidad.setIdCliente(resultado.getInt("idCliente"));
                listaCuentasEntidad.add(cuentaEntidad);
            }

            logger.log(Level.INFO, "Se obtuvo la lista de clientes.");
            return listaCuentasEntidad;
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los clientes.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    @Override
    public CuentaEntidad buscarCuenta(int numCuenta) throws PersistenciaException {
        CuentaEntidad cuentaEntidad = new CuentaEntidad();
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT * FROM Cuentas WHERE numCuenta = " + numCuenta + " AND estaEliminado = 0;";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                cuentaEntidad = convertirResultado(resultado);
            }
            logger.log(Level.INFO, "Se obtuvieron los datos del cliente: " + cuentaEntidad.getIdCliente());
            return cuentaEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos del cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    public CuentaEntidad convertirResultado(ResultSet resultado) throws SQLException {
        CuentaEntidad cuentaEntidad = new CuentaEntidad();
        cuentaEntidad.setNumCuenta(resultado.getInt("numCuenta"));
        cuentaEntidad.setSaldo(resultado.getFloat("saldo"));
        cuentaEntidad.setFechaApertura(resultado.getDate("fechaApertura"));
        cuentaEntidad.setEstaEliminado(resultado.getBoolean("estaEliminado"));
        cuentaEntidad.setIdCliente(resultado.getInt("idCliente"));
        return cuentaEntidad;
    }
    
    @Override
    public CuentaEntidad guardar(CuentaEntidad cuentaEntidad) throws PersistenciaException {
        try (Connection conexion = conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Insertar el cliente
                insertarCuenta(cuentaEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se agregó un cliente a la tabla.");
                return cuentaEntidad;
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al insertar el cliente en la tabla.");
                throw new PersistenciaException("Ocurrió un error al agregar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al agregar el cliente.");
            throw new PersistenciaException("Ocurrió un error al agregar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    private void insertarCuenta(CuentaEntidad cuentaEntidad, Connection conexion) throws SQLException {
        String insertCuenta = "INSERT INTO Cuentas (saldo, fechaApertura, estaEliminado, idCliente) VALUES (?, CURRENT_DATE(), ?, ?);";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(insertCuenta, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, cuentaEntidad.getSaldo());
            preparedStatement.setBoolean(2, false);
            preparedStatement.setInt(3, cuentaEntidad.getIdCliente());
            // Ejecutar la inserción
            preparedStatement.executeUpdate();
            // Obtener las claves generadas
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()) {
                cuentaEntidad.setNumCuenta(resultado.getInt(1));
            }
        }
    }

    @Override
    public void editar(CuentaEntidad cuentaEntidad, AccionCatalogoEnumerador accion) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Actualizar el cliente
                editarCuenta(cuentaEntidad, conexion, accion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se eliminó un cliente de la tabla.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                throw new PersistenciaException("Ocurrió un error al eliminar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
           // hacer uso de Logger
            throw new PersistenciaException("Ocurrió un error al registrar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    private void editarCuenta(CuentaEntidad cuentaEntidad, Connection conexion, AccionCatalogoEnumerador accion) throws SQLException {
        String updateCuenta;
        if (accion == DEPOSITO) {
            updateCuenta = "UPDATE Cuentas set saldo = saldo + ? WHERE numCuenta = ?;";
        } else {
            updateCuenta = "UPDATE Cuentas set saldo = saldo - ? WHERE numCuenta = ?;";
        }
        try (PreparedStatement preparedStatement = conexion.prepareStatement(updateCuenta)) {
            preparedStatement.setFloat(1, cuentaEntidad.getSaldo());
            preparedStatement.setInt(2, cuentaEntidad.getNumCuenta());
            // Ejecutar la actualización
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void borrar(CuentaEntidad cuentaEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Actualizar el cliente
                borrarCuenta(cuentaEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se eliminó un cliente de la tabla.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                throw new PersistenciaException("Ocurrió un error al eliminar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
           // hacer uso de Logger
            throw new PersistenciaException("Ocurrió un error al registrar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    private void borrarCuenta(CuentaEntidad cuentaEntidad, Connection conexion) throws SQLException {
        String deleteCuenta = "UPDATE Cuentas set estaEliminado = ? WHERE numCuenta = ?;";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(deleteCuenta)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, cuentaEntidad.getNumCuenta());
            // Ejecutar la actualización
            preparedStatement.executeUpdate();
        }
    }
}
