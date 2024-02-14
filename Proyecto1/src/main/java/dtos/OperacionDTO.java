/*
 * OperacionDTO.java
 */
package dtos;

import java.sql.Timestamp;

/**
 * Objeto de transferencia para los datos de una operación.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class OperacionDTO {

    private int folio;
    private String monto;
    private String tipo;
    private String fechaHora;
    private int numCuentaOrigen;
    private int numCuentaDestino;
    private String estado;
    private Timestamp fechaHoraCobro;

    /**
     * Constructor vacío de la clase OperacionDTO.
     */
    public OperacionDTO() {

    }

    /**
     * Constructor de la clase OperacionDTO que inicializa todos los atributos.
     *
     * @param folio El folio de la operación.
     * @param monto El monto de la operación.
     * @param tipo El tipo de operación.
     * @param fechaHora La fecha y hora de la operación en formato String.
     * @param numCuentaOrigen El número de cuenta de origen de la operación.
     * @param numCuentaDestino El número de cuenta de destino de la operación.
     * @param estado El estado de la operación.
     * @param fechaHoraCobro La fecha y hora de cobro de la operación.
     */
    public OperacionDTO(int folio, String monto, String tipo, String fechaHora,
            int numCuentaOrigen, int numCuentaDestino, String estado, Timestamp fechaHoraCobro) {
        this.folio = folio;
        this.monto = monto;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.numCuentaOrigen = numCuentaOrigen;
        this.numCuentaDestino = numCuentaDestino;
        this.estado = estado;
        this.fechaHoraCobro = fechaHoraCobro;
    }

    /**
     * Constructor de la clase OperacionDTO que inicializa algunos atributos.
     *
     * @param folio El folio de la operación.
     * @param monto El monto de la operación.
     * @param tipo El tipo de operación.
     * @param numCuentaOrigen El número de cuenta de origen de la operación.
     */
    public OperacionDTO(int folio, String monto, String tipo, int numCuentaOrigen) {
        this.folio = folio;
        this.monto = monto;
        this.tipo = tipo;
        this.numCuentaOrigen = numCuentaOrigen;
    }

    /**
     * Obtiene el folio de la operación.
     *
     * @return El folio de la operación.
     */
    public int getFolio() {
        return folio;
    }

    /**
     * Establece el folio de la operación.
     *
     * @param folio El nuevo folio de la operación.
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * Obtiene el tipo de la operación.
     *
     * @return El tipo de la operación.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la operación.
     *
     * @param tipo El nuevo tipo de la operación.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el monto de la operación.
     *
     * @return El monto de la operación.
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la operación.
     *
     * @param monto El nuevo monto de la operación.
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la fecha y hora de la operación.
     *
     * @return La fecha y hora de la operación en formato String.
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la operación.
     *
     * @param fechaHora La nueva fecha y hora de la operación en formato String.
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el número de cuenta de origen de la operación.
     *
     * @return El número de cuenta de origen de la operación.
     */
    public int getNumCuentaOrigen() {
        return numCuentaOrigen;
    }

    /**
     * Establece el número de cuenta de origen de la operación.
     *
     * @param numCuentaOrigen El nuevo número de cuenta de origen de la
     * operación.
     */
    public void setNumCuentaOrigen(int numCuentaOrigen) {
        this.numCuentaOrigen = numCuentaOrigen;
    }

    /**
     * Obtiene el número de cuenta de destino de la operación.
     *
     * @return El número de cuenta de destino de la operación.
     */
    public int getNumCuentaDestino() {
        return numCuentaDestino;
    }

    /**
     * Establece el número de cuenta de destino de la operación.
     *
     * @param numCuentaDestino El nuevo número de cuenta de destino de la
     * operación.
     */
    public void setNumCuentaDestino(int numCuentaDestino) {
        this.numCuentaDestino = numCuentaDestino;
    }

    /**
     * Obtiene el estado de la operación.
     *
     * @return El estado de la operación.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la operación.
     *
     * @param estado El nuevo estado de la operación.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha y hora de cobro de la operación.
     *
     * @return La fecha y hora de cobro de la operación.
     */
    public Timestamp getFechaHoraCobro() {
        return fechaHoraCobro;
    }

    /**
     * Establece la fecha y hora de cobro de la operación.
     *
     * @param fechaHoraCobro La nueva fecha y hora de cobro de la operación.
     */
    public void setFechaHoraCobro(Timestamp fechaHoraCobro) {
        this.fechaHoraCobro = fechaHoraCobro;
    }

}
