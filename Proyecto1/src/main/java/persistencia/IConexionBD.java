/*
 * IConexionBD.java
 */
package persistencia;

import java.sql.Connection;

/**
 * Interfaz que defino los métodos a implementar en la clase que establece la
 * conexión con la base de datos de MySQL
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public interface IConexionBD {

    /**
     * Metodo que crea la conexión con la base de datos de MySQL
     *
     * @return Objeto de conexión para comunicarse con la base de datos
     * @throws PersistenciaException Si ocurre un error al iniciar la conexión
     */
    public Connection crearConexion() throws PersistenciaException;
}
