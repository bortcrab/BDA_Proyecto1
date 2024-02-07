/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import static com.mysql.cj.conf.PropertyKey.logger;
import entidades.OperacionEntidad;
import java.sql.Connection;
import java.sql.Date;
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
    public List<OperacionEntidad> buscarOperacionesTabla() throws PersistenciaException {
        try {
            List<OperacionEntidad> operacionesLista = null;
            try (Connection conexion = this.conexionBD.crearConexion()) {
                String codigoSQL = "SELECT folio, tipo, monto, fechaHoraEjec FROM Operaciones;";
                Statement comandoSQL = conexion.createStatement();
                ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
                while (resultado.next()) {
                    if (operacionesLista == null) {
                        operacionesLista = new ArrayList<>();
                    }
                    OperacionEntidad operacionEntidad = this.operacionEntidad(resultado);
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
    
    private OperacionEntidad operacionEntidad(ResultSet resultado) throws SQLException {
        int folio = resultado.getInt("folio");
        String tipo = resultado.getString("tipo");
        float monto = resultado.getFloat("monto");
        Date fechaHora = resultado.getDate("fechaHoraEjec");
        return new OperacionEntidad(folio, tipo, monto, fechaHora);
    }
    
}
