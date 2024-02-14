/*
 * NegocioException.java 
 */
package negocio;

/**
 * Excepción lanzada por los métodos implementados en la capa de negocio
 * cuando ocurre un error en las operaciones de la información en objetos de 
 * negocio
 * 
 * @author Usuario
 */
public class NegocioException extends Exception {

    /**
     * Consttructor que establece el mensaje de error que explica el origen del
     * error que ocurrio al ejecutar una operación 
     * 
     * @param mensaje Mensaje de error
     */
    public NegocioException(String mensaje) {
        super(mensaje);
    }
}
