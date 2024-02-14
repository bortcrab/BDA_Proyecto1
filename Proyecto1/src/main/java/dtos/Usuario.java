/*
 * Usuario.java
 */
package dtos;

/**
 * Objeto que acopla tanto los datos del usuario como los de su dirección.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class Usuario {

    private ClienteDTO cliente;
    private DireccionDTO direccion;

    /**
     * Constructor vacío de la clase Usuario.
     */
    public Usuario() {

    }

    /**
     * Constructor de la clase Usuario que inicializa los atributos cliente y
     * direccion.
     *
     * @param cliente El objeto ClienteDTO que representa la información del
     * cliente.
     * @param direccion El objeto DireccionDTO que representa la dirección del
     * usuario.
     */
    public Usuario(ClienteDTO cliente, DireccionDTO direccion) {
        this.cliente = cliente;
        this.direccion = direccion;
    }

    /**
     * Obtiene el objeto ClienteDTO que representa la información del cliente
     * asociado al usuario.
     *
     * @return El objeto ClienteDTO que representa la información del cliente
     * asociado al usuario.
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Establece el objeto ClienteDTO que representa la información del cliente
     * asociado al usuario.
     *
     * @param cliente El nuevo objeto ClienteDTO que representa la información
     * del cliente asociado al usuario.
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el objeto DireccionDTO que representa la dirección del usuario.
     *
     * @return El objeto DireccionDTO que representa la dirección del usuario.
     */
    public DireccionDTO getDireccion() {
        return direccion;
    }

    /**
     * Establece el objeto DireccionDTO que representa la dirección del usuario.
     *
     * @param direccion El nuevo objeto DireccionDTO que representa la dirección
     * del usuario.
     */
    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

}
