/*
 * CuentaDAO.java
 */
package persistencia;

import entidades.CuentaEntidad;
import enumeradores.AccionCatalogoEnumerador;
import static enumeradores.AccionCatalogoEnumerador.*;
import java.sql.CallableStatement;
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
 * Clase que implementa las funciones para leer y escribir información de una
 * cuenta en la base de datos
 *
 * @author bortc
 */
public class CuentaDAO implements ICuentaDAO {

    private IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(CuentaDAO.class.getName());

    /**
     * Constructor que establece la conexión utilizada para comunicarse con la
     * base de datos
     *
     * @param conexionBD Conexión a utilizar para comunicarse con la base de
     * datos
     */
    public CuentaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Obtiene las cuentas que estan a nombre del cliente con el id dado por el
     * parametro
     *
     * @param idCliente id del cliente del cual se desea obtener sus cuentas
     * @return Una lista con las cuentas del cliente cuyo id coincide con el del
     * parametro
     * @throws PersistenciaException Si ocurre un error al obtener la
     * información de las cuentas de la base de datos
     */
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

            logger.log(Level.INFO, "Se obtuvo la lista de cuentas.");
            return listaCuentasEntidad;
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener las cuentas.");
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Realiza la consulta que busca una cuenta en la base de datos dado un
     * número de cuenta
     *
     * @param numCuenta Número de la cuenta a buscar en la base de datos
     * @return Cuenta obtenida en la consulta a la base de datos
     * @throws PersistenciaException Cuando ocurre un error al realizar la
     * consulta
     */
    @Override
    public CuentaEntidad buscarCuenta(int numCuenta) throws PersistenciaException {
        CuentaEntidad cuentaEntidad = new CuentaEntidad();
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT * FROM Cuentas WHERE numCuenta = " + numCuenta + " AND estaEliminado = 0;";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                cuentaEntidad = convertirResultado(resultado);
            } else {
                throw new PersistenciaException("No se encontró la cuenta.");
            }
            logger.log(Level.INFO, "Se obtuvieron los datos de la cuenta: " + cuentaEntidad.getIdCliente());
            return cuentaEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos de la cuenta.");
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    @Override
    public CuentaEntidad buscarCuenta(int folio, String contrasenia) throws PersistenciaException {
        CuentaEntidad cuentaEntidad = new CuentaEntidad();
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT c.* FROM Cuentas c "
                    + "INNER JOIN Operaciones o ON c.numCuenta = o.numCuentaEmisora "
                    + "WHERE o.folio = " + folio + " AND c.estaEliminado = 0;";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                cuentaEntidad = convertirResultado(resultado);
            } else {
                throw new PersistenciaException("No se encontró la cuenta.");
            }
            logger.log(Level.INFO, "Se obtuvieron los datos del cliente: " + cuentaEntidad.getIdCliente());
            return cuentaEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos del cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Convierte el resultado de una operación realizada en la base de datos a
     * un objeto de CuentaEntidad
     *
     * @param resultado Resultado de una operación de la base de datos
     * @return Un objeto de CuentaEntidad con la información obtenidad de la
     * base de datos
     * @throws SQLException Si ocurre un error al obtener la información del
     * resultado
     */
    private CuentaEntidad convertirResultado(ResultSet resultado) throws SQLException {
        CuentaEntidad cuentaEntidad = new CuentaEntidad();
        cuentaEntidad.setNumCuenta(resultado.getInt("numCuenta"));
        cuentaEntidad.setSaldo(resultado.getFloat("saldo"));
        cuentaEntidad.setFechaApertura(resultado.getDate("fechaApertura"));
        cuentaEntidad.setEstaEliminado(resultado.getBoolean("estaEliminado"));
        cuentaEntidad.setIdCliente(resultado.getInt("idCliente"));
        return cuentaEntidad;
    }

    /**
     * Guarda en la base de datos la información de una cuenta nueva
     *
     * @param cuentaEntidad Cuenta con la información a guardar en la base de
     * datos
     * @return Cuenta con la información guardada
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
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
                logger.log(Level.INFO, "Se agregó una cuenta a la tabla.");
                return cuentaEntidad;
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al insertar la cuenta en la tabla.");
                throw new PersistenciaException("Ocurrió un error al agregar la cuenta, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al agregar la cuenta.");
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Metodo auxiliar que contiene la operación de inserción utilizada para
     * guardar una cuenta en la base de datos
     *
     * @param cuentaEntidad Cuenta con la información a guardar en la base de
     * datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @throws SQLException Si ocurre un error al hacer la operación
     */
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

    /**
     * Edita el saldo de una cuenta existente en la base de datos
     *
     * @param cuentaEntidad Cuenta con la información a ingresar a la base de
     * datos de un cliente existente
     * @param accion Tipo de edición a realizar, deposito o retiro
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
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
                logger.log(Level.INFO, "Se editó una cuenta de la tabla.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al editar la cuenta en la tabla.");
                throw new PersistenciaException("Ocurrió un error al realizar el " + ((accion == DEPOSITO) ? "deposito" : "retiro")
                        + ", inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al realizar la actualización de la cuenta en la tabla.");
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Metodo auxiliar que contiene la operación de actualización utilizada para
     * editar el saldo de una cuenta en la base de datos
     *
     * @param cuentaEntidad Cuenta con la información a guardar en la base de
     * datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @param accion Tipo de edición a realizar, deposito o retiro
     * @throws SQLException Si ocurre un error al hacer la operación
     */
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
        } catch (SQLException sqle) {
            throw new SQLException(sqle.getMessage());
        }
    }

    /**
     * Elimina una cuenta asignando estatus de eliminada en la base de datos
     *
     * @param cuentaEntidad Cuenta a eliminar en la base de datos
     * @throws PersistenciaException Si ocurre un error al actualizar el estatus
     * de la cuenta en la base de datos
     */
    @Override
    public void eliminar(CuentaEntidad cuentaEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Actualizar el cliente
                eliminarCuenta(cuentaEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se eliminó una cuenta de la tabla.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al editar el estatus de la cuenta en la tabla.");
                throw new PersistenciaException("Ocurrió un error al eliminar la cuenta, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al realizar la actualización de la cuenta en la tabla.");
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Método auxiliar que contiene la operación de actualización utilizada para
     * cambiar el estatus de una cuenta en la base de datos
     *
     * @param cuentaEntidad Cuenta a eliminar en la base de datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @throws SQLException Si ocurre un error al hacer la operación
     */
    private void eliminarCuenta(CuentaEntidad cuentaEntidad, Connection conexion) throws SQLException {
        String deleteCuenta = "UPDATE Cuentas set estaEliminado = ? WHERE numCuenta = ?;";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(deleteCuenta)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, cuentaEntidad.getNumCuenta());
            // Ejecutar la actualización
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new SQLException(sqle.getMessage());
        }
    }

    @Override
    public void transferir(int numCuentaOrigen, int numCuentaDestino, float monto) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Actualizar el cliente
                realizarTransferencia(numCuentaOrigen, numCuentaDestino, monto, conexion);
                logger.log(Level.INFO, "Se eliminó un cliente de la tabla.");
            } catch (SQLException sqle) {
                // hacer uso de Logger
                throw new PersistenciaException(sqle.getMessage());
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            throw new PersistenciaException("Ocurrió un error al registrar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    private void realizarTransferencia(int numCuentaOrigen, int numCuentaDestino, float monto, Connection conexion) throws SQLException {
        //Preparar la llamada al stored procedure
        String spTransferencia = "{call spTransferencia(?, ?, ?)}";
        try (CallableStatement callableStatement = conexion.prepareCall(spTransferencia)) {
            //Configurar los parámetros del stored procedure
            callableStatement.setInt(1, numCuentaOrigen);
            callableStatement.setInt(2, numCuentaDestino);
            callableStatement.setFloat(3, monto);

            callableStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new SQLException(sqle.getMessage());
        }
    }
}
