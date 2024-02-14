/*
 * ConexionBD.java
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se utiliza para crear la conexión con la base de datos de MySQL
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class ConexionBD implements IConexionBD {

    private final String SERVER = "localhost";
    private final String BASE_DATOS = "proyecto1";
    private final String URL = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS;
    private final String USUARIO = "root";
    private final String CONTRASENIA = "root";
    private Logger logger = Logger.getLogger(ConexionBD.class.getName());

    /**
     * Metodo que crea la conexión con la base de datos de MySQL
     *
     * @return Objeto de conexión para comunicarse con la base de datos
     * @throws PersistenciaException Si ocurre un error al iniciar la conexión
     */
    @Override
    public Connection crearConexion() throws PersistenciaException {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
            logger.log(Level.INFO, "La conexión con la base de datos fue exitosa.");
            return conexion;
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error al conectar con la base de datos. Revise que las credenciales sean las correctas.", sqle);
            throw new PersistenciaException("Hubo un error al conectar con la base de datos.");
        }
    }
}
