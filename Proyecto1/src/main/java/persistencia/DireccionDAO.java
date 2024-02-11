/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
    public DireccionEntidad buscarPorId(int id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public void editar(DireccionEntidad t) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
