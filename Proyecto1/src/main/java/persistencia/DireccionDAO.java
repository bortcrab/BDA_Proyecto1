/*
 * DirecciónDAO.java
 */
package persistencia;

import entidades.DireccionEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa las funciones de lectura y escritura de la información
 * de una dirección en la base de datos
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class DireccionDAO implements IDireccionDAO {

    private IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(DireccionDAO.class.getName());

    /**
     * Constructor que establece la conexión utilizada para comunicarse con la
     * base de datos
     *
     * @param conexionBD Conexión a utilizar para comunicarse con la base de
     * datos
     */
    public DireccionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Realiza la consulta que busca una dirección en la base de datos dado un
     * id de cliente
     *
     * @param idCliente id del cliente que tiene la dirección que se busca
     * @return Dirección obtenida en la consulta a la base de datos
     * @throws PersistenciaException Cuando ocurre un error al realizar la
     * consulta
     */
    @Override
    public DireccionEntidad buscarDireccion(int idCliente) throws PersistenciaException {
        DireccionEntidad direccionEntidad = new DireccionEntidad();
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT * FROM Direcciones WHERE idCliente = " + idCliente + ";";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                direccionEntidad = convertirResultado(resultado);
            }
            logger.log(Level.INFO, "Se obtuvieron los datos de la dirección del cliente: " + direccionEntidad.getIdCliente());
            return direccionEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos de la dirección del cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Convierte el resultado de una operación realizada en la base de datos a
     * un objeto de DireccionEntidad
     *
     * @param resultado Resultado de una operación de la base de datos
     * @return Un objeto de DireccionEntidad con la información obtenidad de la
     * base de datos
     * @throws SQLException Si ocurre un error al obtener la información del
     * resultado
     */
    private DireccionEntidad convertirResultado(ResultSet resultado) throws SQLException {
        DireccionEntidad direccionEntidad = new DireccionEntidad();
        direccionEntidad.setCodigoDireccion(resultado.getInt("codigoDireccion"));
        direccionEntidad.setCodigoPostal(resultado.getString("codigoPostal"));
        direccionEntidad.setColonia(resultado.getString("colonia"));
        direccionEntidad.setCalle(resultado.getString("calle"));
        direccionEntidad.setNumExterior(resultado.getString("numExterior"));
        direccionEntidad.setIdCliente(resultado.getInt("idCliente"));
        return direccionEntidad;
    }

    /**
     * Guarda en la base de datos la información de una dirección nueva
     *
     * @param direccionEntidad Dirección con la información a guardar en la base
     * de datos
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    @Override
    public void guardar(DireccionEntidad direccionEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Insertar el cliente
                insertarDireccion(direccionEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se agregó una dirección a la tabla.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al insertar la dirección en la tabla.");
                throw new PersistenciaException("Ocurrió un error al agregar la dirección, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al agregar la dirección.");
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Metodo auxiliar que contiene la operación de inserción utilizada para
     * guardar una dirección en la base de datos
     *
     * @param direccionEntidad Dirección con la información a guardar en la base de
     * datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @throws SQLException Si ocurre un error al hacer la operación
     */
    private void insertarDireccion(DireccionEntidad direccion, Connection conexion) throws SQLException {
        String insertDireccion = "INSERT INTO Direcciones (codigoPostal, colonia, calle, numExterior, idCliente) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(insertDireccion, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, direccion.getCodigoPostal());
            preparedStatement.setString(2, direccion.getColonia());
            preparedStatement.setString(3, direccion.getCalle());
            preparedStatement.setString(4, direccion.getNumExterior());
            preparedStatement.setInt(5, direccion.getIdCliente());
            // Ejecutar la inserción
            preparedStatement.executeUpdate();
            // Obtener las claves generadas
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()) {
                direccion.setIdCliente(resultado.getInt(1));
            }
        }
    }

    /**
     * Edita la información de una cuenta existente en la base de datos
     *
     * @param direccionEntidad Direccion con la información a ingresar a la base
     * de datos de una dirección existente
     * @throws PersistenciaException Si ocurre un error al escribir la
     * información en la base de datos
     */
    @Override
    public void editar(DireccionEntidad direccionEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Actualizar el cliente
                actualizarDireccion(direccionEntidad, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se editó una dirección exitosamente.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al actualizar la dirección.");
                throw new PersistenciaException("Ocurrió un error al editar la dirección, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al actualizar la dirección.", sqle);
            throw new PersistenciaException("Ocurrió un error al conectarse con la base de datos, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    /**
     * Metodo auxiliar que contiene la operación de inserción utilizada para
     * actualizar una dirección en la base de datos
     *
     * @param direccionEntidad Dirección con la información a guardar en la base de
     * datos
     * @param conexion Conexión a la base de datos donde se lleva a cabo la
     * operación
     * @throws SQLException Si ocurre un error al hacer la operación
     */
    private void actualizarDireccion(DireccionEntidad direccionEntidad, Connection conexion) throws SQLException {
        String updateDireccion = "UPDATE Direcciones set codigoPostal= ?, colonia = ?, calle = ?, numExterior = ? WHERE codigoDireccion = ?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(updateDireccion)) {
            preparedStatement.setString(1, direccionEntidad.getCodigoPostal());
            preparedStatement.setString(2, direccionEntidad.getColonia());
            preparedStatement.setString(3, direccionEntidad.getCalle());
            preparedStatement.setString(4, direccionEntidad.getNumExterior());
            preparedStatement.setInt(5, direccionEntidad.getCodigoDireccion());
            // Ejecutar la actualización
            preparedStatement.executeUpdate();
        }
    }
}
