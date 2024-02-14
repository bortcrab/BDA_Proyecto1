/*
 * CuentaEntidad.java
 */
package entidades;

import java.sql.Date;

/**
 * Objeto que representa la entidad Cuenta.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class CuentaEntidad {

    int numCuenta;
    float saldo;
    Date fechaApertura;
    boolean estaEliminado;
    int idCliente;

    /**
     * Constructor vacío de la clase CuentaEntidad. Crea un objeto CuentaEntidad
     * con valores predeterminados para sus atributos.
     */
    public CuentaEntidad() {
    }

    /**
     * Constructor de la clase CuentaEntidad que inicializa todos los atributos.
     *
     * @param numCuenta El número de cuenta único.
     * @param saldo El saldo actual de la cuenta.
     * @param fechaApertura La fecha de apertura de la cuenta.
     * @param estaEliminado Indica si la cuenta está eliminada o no.
     * @param idCliente El identificador único del cliente asociado a la cuenta.
     */
    public CuentaEntidad(int numCuenta, float saldo, Date fechaApertura, boolean estaEliminado, int idCliente) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.estaEliminado = estaEliminado;
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el número de cuenta único.
     *
     * @return El número de cuenta único.
     */
    public int getNumCuenta() {
        return numCuenta;
    }

    /**
     * Establece el número de cuenta único.
     *
     * @param numCuenta El nuevo número de cuenta único.
     */
    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     *
     * @return El saldo actual de la cuenta.
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo actual de la cuenta.
     *
     * @param saldo El nuevo saldo actual de la cuenta.
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtiene la fecha de apertura de la cuenta.
     *
     * @return La fecha de apertura de la cuenta.
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Establece la fecha de apertura de la cuenta.
     *
     * @param fechaApertura La nueva fecha de apertura de la cuenta.
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Verifica si la cuenta está marcada como eliminada.
     *
     * @return true si la cuenta está eliminada, false si no.
     */
    public boolean isEstaEliminado() {
        return estaEliminado;
    }

    /**
     * Establece el estado de eliminación de la cuenta.
     *
     * @param estaEliminado Indica si la cuenta está eliminada o no.
     */
    public void setEstaEliminado(boolean estaEliminado) {
        this.estaEliminado = estaEliminado;
    }

    /**
     * Obtiene el identificador único del cliente asociado a la cuenta.
     *
     * @return El identificador único del cliente asociado a la cuenta.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador único del cliente asociado a la cuenta.
     *
     * @param idCliente El nuevo identificador único del cliente asociado a la
     * cuenta.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
