/*
 * PersistenciaException.java
 */
package persistencia;

/**
 * Excepción lanzada por los métodos implementados en la capa de persistencia
 * cuando ocurre un error en el uso de la base de datos
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class PersistenciaException extends Exception {

    /**
     * Consttructor que establece el mensaje de error que explica el origen del
     * error que ocurrio al ejecutar una operación 
     * 
     * @param mensaje Mensaje de error
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }
}
