/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.ClienteEntidad;
import entidades.DireccionEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class DireccionDAO implements IDireccionDAO {
    public IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(DireccionDAO.class.getName());
    
    public DireccionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
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
            //logger.log(Level.INFO, "Se obtuvieron los datos del cliente: " + clienteEntidad.getIdCliente());
            return direccionEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos del cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    public DireccionEntidad convertirResultado(ResultSet resultado) throws SQLException {
        DireccionEntidad direccionEntidad = new DireccionEntidad();
        direccionEntidad.setCodigoDireccion(resultado.getInt("codigoDireccion"));
        direccionEntidad.setCodigoPostal(resultado.getString("codigoPostal"));
        direccionEntidad.setColonia(resultado.getString("colonia"));
        direccionEntidad.setCalle(resultado.getString("calle"));
        direccionEntidad.setNumExterior(resultado.getString("numExterior"));
        direccionEntidad.setIdCliente(resultado.getInt("idCliente"));
        return direccionEntidad;
    }

    @Override
    public void guardar(DireccionEntidad direccion) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Insertar el cliente
                insertarDireccion(direccion, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se agregó un cliente a la tabla.");
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
                logger.log(Level.INFO, "Se editó un cliente exitosamente.");
            } catch (SQLException ex) {
                conexion.rollback();
                // hacer uso de Logger
                logger.log(Level.SEVERE, "Ocurrió un error al actualizar el cliente.");
                throw new PersistenciaException("Ocurrió un error al editar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al actualizar el cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al registrar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
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
