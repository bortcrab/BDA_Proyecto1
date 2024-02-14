/*
 * PresentacionException.java
 */
package presentacion;

/**
 * Excepción lanzada por los métodos implementados en la capa de presentación
 * cuando ocurre un error en la interfaz de usuario
 *
 * @author Usuario
 */
public class PresentacionException extends Exception {

    /**
     * Consttructor que establece el mensaje de error que explica el origen del
     * error que ocurrio al ejecutar una operación 
     * 
     * @param mensaje Mensaje de error
     */
    public PresentacionException(String mensaje) {
        super(mensaje);
    }
}
