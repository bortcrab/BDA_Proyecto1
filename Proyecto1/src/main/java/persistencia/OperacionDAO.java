/*
 * OperacionDAO
 */
package persistencia;

import entidades.OperacionEntidad;
import java.security.Key;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * clase que implementa las funciones de lectura y escritura a la información de
 * una operación en la base de datos
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class OperacionDAO implements IOperacionDAO {

    private IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(DireccionDAO.class.getName());

    /**
     * Constructor que establece la conexión utilizada para comunicarse con la
     * base de datos
     *
     * @param conexionBD Conexión a utilizar para comunicarse con la base de
     * datos
     */
    public OperacionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    /**
     * Obtiene las operaciones realizadas por el cliente con el id dado por el
     * parametro
     *
     * @param idCliente id del cliente del cual se desea obtener sus operaciones
     * @return Una lista con las operaciones del cliente cuyo id coincide con el
     * del parametro
     * @throws PersistenciaException Si ocurre un error al obtener la
     * información de las operaciones de la base de datos
     */
    @Override
    public List<OperacionEntidad> buscarOperacionesTabla(int idCliente, String filtroTipo, Date inicio, Date fin, int limite, int offset) throws PersistenciaException {
        try {
            String periodo = seleccionPeriodo(inicio, fin);
            String select = seleccionTipo(filtroTipo);
            List<OperacionEntidad> operacionesLista = null;
            try (Connection conexion = this.conexionBD.crearConexion()) {
                String codigoSQL = select + " WHERE cl.idCliente = " + idCliente + periodo
                        + " ORDER BY o.folio LIMIT " + limite + " OFFSET " + offset + ";";
                Statement comandoSQL = conexion.createStatement();
                ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
                while (resultado.next()) {
                    if (operacionesLista == null) {
                        operacionesLista = new ArrayList<>();
                    }
                    OperacionEntidad operacionEntidad = new OperacionEntidad();
                    operacionEntidad.setFolio(resultado.getInt("o.folio"));
                    operacionEntidad.setMonto(resultado.getFloat("o.monto"));
                    operacionEntidad.setTipo(resultado.getString("o.tipo"));
                    operacionEntidad.setFechaHoraEjec(resultado.getTimestamp("o.fechaHoraEjec"));
                    operacionEntidad.setNumCuentaEmisora(resultado.getInt("o.numCuentaEmisora"));
                    if (operacionEntidad.getTipo().equals("Transferencia")) {
                        operacionEntidad.setNumCuentaReceptora(resultado.getInt("t.numCuentaReceptora"));
                    } else {
                        operacionEntidad.setNumCuentaReceptora(0);
                    }
                    if (operacionEntidad.getTipo().equals("Retiro sin cuenta")) {
                        operacionEntidad.setEstado(resultado.getString("rsc.estado"));
                        operacionEntidad.setFechaHoraCobro(resultado.getTimestamp("rsc.fechaHoraCobro"));
                    } else {
                        operacionEntidad.setEstado(null);
                        operacionEntidad.setFechaHoraCobro(null);
                    }
                    operacionesLista.add(operacionEntidad);
                }
            }
            logger.log(Level.INFO, "Se obtuvo la lista de clientes.");
            return operacionesLista;
        } catch (SQLException sqle) {
            // hacer uso de Logger
            //logger.log(Level.SEVERE, "Ocurrió un error al obtener los clientes.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    private String seleccionPeriodo(Date inicio, Date fin) throws PersistenciaException {
        String periodo = "";
        if (inicio == null || fin == null) {
            return periodo;
        } else if ((inicio.getTime() > fin.getTime())) {
            throw new PersistenciaException("Favor de seleccionar fechas válidas.");
        }
        periodo = " AND o.fechaHoraEjec BETWEEN '" + inicio.toString() + "' AND '" + fin.toString() + "'";
        return periodo;
    }

    public String seleccionTipo(String filtroTipo) throws PersistenciaException {
        String codigoSQL = "";
        switch (filtroTipo) {
            case "Todos":
                codigoSQL = "SELECT o.folio, o.monto, o.tipo, o.fechaHoraEjec, "
                        + "o.numCuentaEmisora, t.numCuentaReceptora, rsc.estado, "
                        + "rsc.fechaHoraCobro FROM Operaciones o "
                        + "LEFT JOIN Transferencias t ON o.folio = t.folio "
                        + "LEFT JOIN Retiros r ON o.folio = r.folio "
                        + "LEFT JOIN RetirosSinCuenta rsc ON o.folio = rsc.folio "
                        + "LEFT JOIN Depositos d ON o.folio = d.folio "
                        + "INNER JOIN Cuentas cu on o.numCuentaEmisora = cu.numCuenta "
                        + "INNER JOIN Clientes cl on cu.idCliente = cl.idCliente";
                break;
            case "Transferencia":
                codigoSQL = "SELECT o.folio, o.monto, o.tipo, o.fechaHoraEjec, "
                        + "o.numCuentaEmisora, t.numCuentaReceptora FROM Operaciones o "
                        + "INNER JOIN Transferencias t ON o.folio = t.folio "
                        + "INNER JOIN Cuentas cu on o.numCuentaEmisora = cu.numCuenta "
                        + "INNER JOIN Clientes cl on cu.idCliente = cl.idCliente";
                break;
            case "Retiro":
                codigoSQL = "SELECT o.folio, o.monto, o.tipo, o.fechaHoraEjec, "
                        + "o.numCuentaEmisora FROM Operaciones o "
                        + "INNER JOIN Retiros r ON o.folio = r.folio "
                        + "INNER JOIN Cuentas cu on o.numCuentaEmisora = cu.numCuenta "
                        + "INNER JOIN Clientes cl on cu.idCliente = cl.idCliente";
                break;
            case "Retiro sin cuenta":
                codigoSQL = "SELECT o.folio, o.monto, o.tipo, o.fechaHoraEjec, "
                        + "o.numCuentaEmisora, rsc.estado, rsc.fechaHoraCobro FROM Operaciones o "
                        + "INNER JOIN RetirosSinCuenta rsc ON o.folio = rsc.folio "
                        + "INNER JOIN Cuentas cu on o.numCuentaEmisora = cu.numCuenta "
                        + "INNER JOIN Clientes cl on cu.idCliente = cl.idCliente";
                break;
            case "Depósito":
                codigoSQL = "SELECT o.folio, o.monto, o.tipo, o.fechaHoraEjec, "
                        + "o.numCuentaEmisora FROM Operaciones o "
                        + "INNER JOIN Depositos d ON o.folio = d.folio "
                        + "INNER JOIN Cuentas cu on o.numCuentaEmisora = cu.numCuenta "
                        + "INNER JOIN Clientes cl on cu.idCliente = cl.idCliente";
                break;
            default:
                throw new PersistenciaException("El tipo de operación no existe.");
        }
        return codigoSQL;
    }
    
    /**
     * Guarda en la base de datos la información de una operacion nueva
     *
     * @param operacionEntidad Operacion con la información a guardar en la base
     * de datos
     * @return Operación con la información guardada
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    @Override
    public OperacionEntidad guardar(OperacionEntidad operacionEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Insertar el cliente
                insertarOperacion(operacionEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                //logger.log(Level.INFO, "Se agregó un cliente a la tabla.");
                return operacionEntidad;
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al insertar la operación en la tabla.");
                throw new PersistenciaException("Ocurrió un error al agregar la operación, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al agregar la operación.");
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Metodo auxiliar que contiene la operación de inserción utilizada para
     * guardar una operación en la base de datos
     *
     * @param operacionEntidad operación con la información a guardar en la base
     * de datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @throws SQLException Si ocurre un error al hacer la operación
     */
    private void insertarOperacion(OperacionEntidad operacionEntidad, Connection conexion) throws SQLException {
        String insertOperacion = "INSERT INTO Operaciones (monto, tipo, numCuentaEmisora) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(insertOperacion, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, operacionEntidad.getMonto());
            preparedStatement.setString(2, operacionEntidad.getTipo());
            preparedStatement.setInt(3, operacionEntidad.getNumCuentaEmisora());
            // Ejecutar la inserción
            preparedStatement.executeUpdate();
            // Obtener las claves generadas
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()) {
                operacionEntidad.setFolio(resultado.getInt(1));
            }
        }
        if (operacionEntidad.getTipo().equals("Transferencia")) {
            insertOperacion = "INSERT INTO Transferencias (folio, numCuentaReceptora) VALUES (?, ?);";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertOperacion)) {
                preparedStatement.setInt(1, operacionEntidad.getFolio());
                preparedStatement.setInt(2, operacionEntidad.getNumCuentaReceptora());
                //Ejecutar la inserción
                preparedStatement.executeUpdate();
            }
        } else if (operacionEntidad.getTipo().equals("Retiro")) {
            insertOperacion = "INSERT INTO Retiros (folio) VALUES (?);";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertOperacion)) {
                preparedStatement.setInt(1, operacionEntidad.getFolio());
                // Ejecutar la inserción
                preparedStatement.executeUpdate();
            }
        } else if (operacionEntidad.getTipo().equals("Retiro sin cuenta")) {
            insertOperacion = "INSERT INTO RetirosSinCuenta (folio, estado) VALUES (?, ?);";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertOperacion)) {
                preparedStatement.setInt(1, operacionEntidad.getFolio());
                preparedStatement.setString(2, "Pendiente");
                // Ejecutar la inserción
                preparedStatement.executeUpdate();
            }
        } else if (operacionEntidad.getTipo().equals("Depósito")) {
            insertOperacion = "INSERT INTO Depositos (folio) VALUES (?);";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertOperacion)) {
                preparedStatement.setInt(1, operacionEntidad.getFolio());
                // Ejecutar la inserción
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public String obtenerContrasenia(int folio) throws PersistenciaException {
        String contrasenia = "";
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT contrasenia FROM RetirosSinCuenta WHERE folio = " + folio + ";";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                // Clave secreta AES
                String clave = "pipucatepipucate";

                // Convertir clave de String a byte array
                byte[] keyBytes = clave.getBytes("UTF-8");

                // Crear instancia de SecretKeySpec para la clave
                Key secretKey = new SecretKeySpec(keyBytes, "AES");

                // Crear instancia de Cipher para realizar la desencriptación
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);

                byte[] encryptedBytes = resultado.getBytes("contrasenia");

                // Desencriptar el campo VARBINARY
                byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

                // Convertir el resultado desencriptado a String o realizar el procesamiento necesario
                contrasenia = new String(decryptedBytes, "UTF-8");
            }
            //logger.log(Level.INFO, "Se obtuvieron los datos del cliente: " + clienteEntidad.getIdCliente());
            return contrasenia;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            //logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos del cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } catch (Exception ex) {
            Logger.getLogger(OperacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public OperacionEntidad obtenerOperacion(int folio) throws PersistenciaException {
        OperacionEntidad operacionEntidad = new OperacionEntidad();
        try (Connection conexion = conexionBD.crearConexion()) {
            String codigoSQL = "SELECT * FROM Operaciones WHERE folio = " + folio + ";";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                operacionEntidad = new OperacionEntidad(
                        resultado.getFloat("monto"),
                        resultado.getString("tipo"),
                        resultado.getInt("numCuentaEmisora"),
                        0);
            }
            return operacionEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            //logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos del cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    @Override
    public void actualizarRetiroSinCuenta(int folio) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Actualizar el cliente
                actualizarRetiroSC(folio, conexion);
                // Confirmar la transacción
                conexion.commit();
                //logger.log(Level.INFO, "Se editó un cliente exitosamente.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                //logger.log(Level.SEVERE, "Ocurrió un error al actualizar el cliente.");
                throw new PersistenciaException("Ocurrió un error al editar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            //logger.log(Level.SEVERE, "Ocurrió un error al actualizar el cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al registrar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    private void actualizarRetiroSC(int folio, Connection conexion) throws SQLException {
        String updateCliente = "UPDATE RetirosSinCuenta set estado = 'Cobrado', fechaHoraCobro = now() WHERE folio = ? AND estado = 'Pendiente';";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(updateCliente)) {
            preparedStatement.setInt(1, folio);
            // Ejecutar la actualización
            preparedStatement.executeUpdate();
        }
    }

}
