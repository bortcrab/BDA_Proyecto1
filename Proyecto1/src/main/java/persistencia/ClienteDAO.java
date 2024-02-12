/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.ClienteEntidad;
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
public class ClienteDAO implements IClienteDAO {

    public IConexionBD conexionBD;
    private Logger logger = Logger.getLogger(ClienteDAO.class.getName());

    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public ClienteEntidad buscarCliente(ClienteEntidad clienteEntidad) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String codigoSQL = "SELECT * FROM Clientes WHERE correo = " + clienteEntidad.getCorreo() + " AND contrasenia = sha2(" + clienteEntidad.getContrasenia() + ", 512);";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            if (resultado.next()) {
                clienteEntidad = convertirResultado(resultado);
            }
            logger.log(Level.INFO, "Se obtuvieron los datos del cliente: " + clienteEntidad.getIdCliente());
            return clienteEntidad;
        } catch (SQLException sqle) {
            // Hacer uso de Logger
            logger.log(Level.SEVERE, "Ocurrió un error al obtener los datos del cliente.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    public ClienteEntidad convertirResultado(ResultSet resultado) throws SQLException {
        ClienteEntidad clienteEntidad = new ClienteEntidad();
        clienteEntidad.setIdCliente(resultado.getInt("idCliente"));
        clienteEntidad.setNombres(resultado.getString("nombres"));
        clienteEntidad.setApellidoPaterno(resultado.getString("apellidoPaterno"));
        clienteEntidad.setApellidoMaterno(resultado.getString("apellidoMaterno"));
        clienteEntidad.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
        return clienteEntidad;
    }

    @Override
    public ClienteEntidad guardar(ClienteEntidad cliente) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            try {
                // Desactivar el autocommit
                conexion.setAutoCommit(false);
                // Insertar el cliente
                insertarCliente(cliente, conexion);
                // Confirmar la transacción
                conexion.commit();
                logger.log(Level.INFO, "Se agregó un cliente a la tabla.");
                return cliente;
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

    private void insertarCliente(ClienteEntidad cliente, Connection conexion) throws SQLException {
        String insertCliente = "INSERT INTO Clientes (nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, correo, contrasenia) VALUES (?, ?, ?, ?, ?, sha2(?, 512));";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidoPaterno());
            preparedStatement.setString(3, cliente.getApellidoMaterno());
            preparedStatement.setDate(4, cliente.getFechaNacimiento());
            preparedStatement.setString(5, cliente.getCorreo());
            preparedStatement.setString(6, cliente.getContrasenia());
            // Ejecutar la inserción
            preparedStatement.executeUpdate();
            // Obtener las claves generadas
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()) {
                cliente.setIdCliente(resultado.getInt(1));
            }
        }
    }

    @Override
    public void editar(ClienteEntidad cliente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
