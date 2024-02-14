/*
 * CuentaDTO.java
 */
package dtos;

/**
 * Objeto de transferencia para los datos de una cuenta.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class CuentaDTO {

    int numCuenta;
    String saldo;
    String fechaApertura;
    int idCliente;

    /**
     * Constructor por defecto de la clase CuentaDTO.
     */
    public CuentaDTO() {
    }

    /**
     * Constructor de la clase CuentaDTO que inicializa los atributos con los
     * valores especificados.
     *
     * @param numCuenta el número de cuenta.
     * @param saldo el saldo de la cuenta.
     * @param fechaApertura la fecha de apertura de la cuenta.
     * @param idCliente el ID del cliente asociado a la cuenta.
     */
    public CuentaDTO(int numCuenta, String saldo, String fechaApertura, int idCliente) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.idCliente = idCliente;
    }

    /**
     * Devuelve el número de cuenta.
     *
     * @return el número de cuenta.
     */
    public int getNumCuenta() {
        return numCuenta;
    }

    /**
     * Establece el número de cuenta.
     *
     * @param numCuenta el número de cuenta.
     */
    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * Devuelve el saldo de la cuenta.
     *
     * @return el saldo de la cuenta.
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo de la cuenta.
     *
     * @param saldo el saldo de la cuenta.
     */
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    /**
     * Devuelve la fecha de apertura de la cuenta.
     *
     * @return la fecha de apertura de la cuenta.
     */
    public String getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Establece la fecha de apertura de la cuenta.
     *
     * @param fechaApertura la fecha de apertura de la cuenta.
     */
    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Devuelve el ID del cliente asociado a la cuenta.
     *
     * @return el ID del cliente asociado a la cuenta.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente asociado a la cuenta.
     *
     * @param idCliente el ID del cliente asociado a la cuenta.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Devuelve una representación en cadena de la cuenta, que consiste en el
     * número de cuenta precedido por el símbolo '#'.
     *
     * @return una representación en cadena de la cuenta.
     */
    @Override
    public String toString() {
        return "#" + this.numCuenta;
    }
}
