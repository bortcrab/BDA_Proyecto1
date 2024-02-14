/*
 * ClienteDAO.java
 */
package persistencia;

import entidades.ClienteEntidad;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase que implementa los metodos para leer y escribir la información de los
 * clientes en la base de datos
 *
 * @author jorge
 */
public class ClienteDAO implements IClienteDAO {

    private IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(ClienteDAO.class.getName());

    /**
     * Constructor que establece la conexión utilizada para comunicarse con la
     * base de datos
     *
     * @param conexionBD Conexión a utilizar para comunicarse con la base de
     * datos
     */
    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Realiza la consulta que busca un cliente en la base de datos dado un
     * objeto de ClienteEntidad
     *
     * @param clienteEntidad Objeto que representa el cliente que se desea
     * buscar en la base de datos
     * @return Cliente obtenido en la consulta a la base de datos
     * @throws PersistenciaException Cuando ocurre un error al realizar la
     * consulta
     */
    @Override
    public ClienteEntidad buscarCliente(ClienteEntidad clienteEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT * FROM Clientes WHERE correo = '" + clienteEntidad.getCorreo() + "' AND contrasenia = AES_ENCRYPT('" + clienteEntidad.getContrasenia() + "', 'pipucatepipucate');";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                clienteEntidad = convertirResultado(resultado);
            } else {
                throw new SQLException();
            }
            logger.log(Level.INFO, "Se obtuvieron los datos del cliente: " + clienteEntidad.getIdCliente());
            return clienteEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos del cliente.");
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } catch (Exception ex) {
            throw new PersistenciaException(ex.getMessage());
        }
    }
    
    

    /**
     * Convierte el resultado de una operación realizada en la base de datos a
     * un objeto de ClienteEntidad
     *
     * @param resultado Resultado de una operación de la base de datos
     * @return Un objeto de ClienteEntidad con la información obtenidad de la
     * base de datos
     * @throws SQLException Si ocurre un error al obtener la información del
     * resultado
     */
    private ClienteEntidad convertirResultado(ResultSet resultado) throws SQLException {
        ClienteEntidad clienteEntidad = new ClienteEntidad();
        clienteEntidad.setIdCliente(resultado.getInt("idCliente"));
        clienteEntidad.setNombres(resultado.getString("nombres"));
        clienteEntidad.setApellidoPaterno(resultado.getString("apellidoPaterno"));
        clienteEntidad.setApellidoMaterno(resultado.getString("apellidoMaterno"));
        clienteEntidad.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
        clienteEntidad.setCorreo(resultado.getString("correo"));
        clienteEntidad.setContrasenia(resultado.getString("contrasenia"));
        return clienteEntidad;
    }

    /**
     * Guarda en la base de datos la información de un cliente nuevo
     *
     * @param clienteEntidad Cliente con la información a guardar en la base de
     * datos
     * @return Cliente con la información guardada
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    @Override
    public ClienteEntidad guardar(ClienteEntidad clienteEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Insertar el cliente
                insertarCliente(clienteEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se agregó un cliente a la tabla.");
                return clienteEntidad;
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al insertar el cliente en la tabla.");
                throw new PersistenciaException("Ocurrió un error al agregar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al conectarse con la base de datos.");
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Metodo auxiliar que contiene la operación de inserción utilizada para
     * guardar un cliente en la base de datos
     *
     * @param clienteEntidad Cliente con la información a guardar en la base de
     * datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @throws SQLException Si ocurre un error al hacer la operación
     */
    private void insertarCliente(ClienteEntidad clienteEntidad, Connection conexion) throws SQLException {
        String insertCliente = "INSERT INTO Clientes (nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, correo, contrasenia) VALUES (?, ?, ?, ?, ?, AES_ENCRYPT(?, A'pipucatepipucate'));";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, clienteEntidad.getNombres());
            preparedStatement.setString(2, clienteEntidad.getApellidoPaterno());
            preparedStatement.setString(3, clienteEntidad.getApellidoMaterno());
            preparedStatement.setDate(4, clienteEntidad.getFechaNacimiento());
            preparedStatement.setString(5, clienteEntidad.getCorreo());
            preparedStatement.setString(6, clienteEntidad.getContrasenia());
            // Ejecutar la inserción
            preparedStatement.executeUpdate();
            // Obtener las claves generadas
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()) {
                clienteEntidad.setIdCliente(resultado.getInt(1));
            }
        }
    }

    /**
     * Edita la información de un cliente existente en la base de datos
     *
     * @param clienteEntidad Objeto con la información a ingresar a la base de
     * datos de un cliente existente
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    @Override
    public void editar(ClienteEntidad clienteEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Actualizar el cliente
                actualizarCliente(clienteEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se editó un cliente exitosamente.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al actualizar el cliente.");
                throw new PersistenciaException("Ocurrió un error al editar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al conectarse con la base de datos.", sqle);
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Metodo auxiliar que contiene la operación de actualización utilizada para
     * editar la información de un cliente en la base de datos
     *
     * @param clienteEntidad Cliente con la información a guardar en la base de
     * datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @throws SQLException Si ocurre un error al hacer la operación
     */
    private void actualizarCliente(ClienteEntidad clienteEntidad, Connection conexion) throws SQLException {
        String updateCliente = "UPDATE Clientes set nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, fechaNacimiento = ?, correo = ?, contrasenia = sha2(?, 512) WHERE idCliente = ?;";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(updateCliente)) {
            preparedStatement.setString(1, clienteEntidad.getNombres());
            preparedStatement.setString(2, clienteEntidad.getApellidoPaterno());
            preparedStatement.setString(3, clienteEntidad.getApellidoMaterno());
            preparedStatement.setDate(4, clienteEntidad.getFechaNacimiento());
            preparedStatement.setString(5, clienteEntidad.getCorreo());
            preparedStatement.setString(6, clienteEntidad.getContrasenia());
            preparedStatement.setInt(7, clienteEntidad.getIdCliente());
            // Ejecutar la actualización
            preparedStatement.executeUpdate();
        }
    }
    
    @Override
    public String obtenerContrasenia(int idCliente) throws PersistenciaException {
        String contrasenia = "";
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT contrasenia FROM Clientes WHERE idCliente = " + idCliente + ";";
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
}
