/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import static com.mysql.cj.conf.PropertyKey.logger;
import entidades.ClienteEntidad;
import entidades.OperacionEntidad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Usuario
 */
public class OperacionDAO implements IOperacionDAO {
    public IConexionBD conexionBD;
    
    public OperacionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public List<OperacionEntidad> buscarOperacionesTabla(int idCliente) throws PersistenciaException {
        try {
            List<OperacionEntidad> operacionesLista = null;
            try (Connection conexion = this.conexionBD.crearConexion()) {
                String codigoSQL = "SELECT o.folio, o.tipo, o.monto, o.fechaHoraEjec,\n" +
"o.numCuentaEmisora, t.numCuentaReceptora,\n" +
"rsc.contrasenia, rsc.fechaHoraCobro  FROM Operaciones o\n" +
"LEFT JOIN Transferencias t ON o.folio = t.folio\n" +
"LEFT JOIN Retiros r ON o.folio = r.folio\n" +
"LEFT JOIN RetirosSinCuenta rsc ON o.folio = rsc.folio\n" +
"LEFT JOIN Depositos d ON o.folio = d.folio\n" +
"INNER JOIN Cuentas cu on o.numCuentaEmisora = numCuenta\n" +
"INNER JOIN Clientes cl on cu.idCliente = cl.idCliente\n" +
"WHERE cl.idCliente = " + idCliente + ";";
                Statement comandoSQL = conexion.createStatement();
                ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
                while (resultado.next()) {
                    if (operacionesLista == null) {
                        operacionesLista = new ArrayList<>();
                    }
                    OperacionEntidad operacionEntidad = new OperacionEntidad();
                    operacionEntidad.setFolio(resultado.getInt("o.folio"));
                    operacionesLista.add(operacionEntidad);
                }
            }
            //logger.log(Level.INFO, "Se obtuvo la lista de clientes.");
            return operacionesLista;
        } catch (SQLException sqle) {
            // hacer uso de Logger
            //logger.log(Level.SEVERE, "Ocurrió un error al obtener los clientes.", sqle);
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

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
                //logger.log(Level.SEVERE, "Ocurrió un error al insertar el cliente en la tabla.");
                throw new PersistenciaException("Ocurrió un error al agregar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
            }
        } catch (SQLException sqle) {
            // hacer uso de Logger
            //logger.log(Level.SEVERE, "Ocurrió un error al agregar el cliente.");
            throw new PersistenciaException("Ocurrió un error al agregar el cliente, inténtelo de nuevo, y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

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
        if (operacionEntidad.getTipo().equals("Depósito")) {
            insertOperacion = "INSERT INTO Depositos (folio) VALUES (?);";
        } else {
            insertOperacion = "INSERT INTO Retiros (folio) VALUES (?);";
        }
        try (PreparedStatement preparedStatement = conexion.prepareStatement(insertOperacion)) {
            preparedStatement.setInt(1, operacionEntidad.getFolio());
            // Ejecutar la inserción
            preparedStatement.executeUpdate();
        }
    }
}
