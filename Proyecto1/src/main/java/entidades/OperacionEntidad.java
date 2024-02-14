/*
 * OperacionEntidad.java
 */
package entidades;

import java.sql.Timestamp;

/**
 * Objeto que representa la entidad operación.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class OperacionEntidad {

    private int folio;
    private float monto;
    private String tipo;
    private Timestamp fechaHoraEjec;
    private int numCuentaEmisora;
    private int numCuentaReceptora;
    private String estado;
    private Timestamp fechaHoraCobro;

    /**
     * Constructor vacío de la clase OperacionEntidad. Crea un objeto
     * OperacionEntidad con valores predeterminados para sus atributos.
     */
    public OperacionEntidad() {
    }

    /**
     * Constructor de la clase OperacionEntidad que inicializa todos los
     * atributos.
     *
     * @param folio El folio único de la operación.
     * @param monto El monto de la operación.
     * @param tipo El tipo de operación (por ejemplo, "transferencia").
     * @param fechaHoraEjec La fecha y hora de ejecución de la operación.
     * @param numCuentaEmisora El número de cuenta emisora asociado a la
     * operación.
     * @param numCuentaReceptora El número de cuenta receptora asociado a la
     * operación.
     * @param estado El estado de la operación (por ejemplo, "pendiente").
     * @param fechaHoraCobro La fecha y hora de cobro de la operación.
     */
    public OperacionEntidad(int folio, float monto, String tipo, Timestamp fechaHoraEjec, int numCuentaEmisora, int numCuentaReceptora, String estado, Timestamp fechaHoraCobro) {
        this.folio = folio;
        this.monto = monto;
        this.tipo = tipo;
        this.fechaHoraEjec = fechaHoraEjec;
        this.numCuentaEmisora = numCuentaEmisora;
        this.numCuentaReceptora = numCuentaReceptora;
        this.estado = estado;
        this.fechaHoraCobro = fechaHoraCobro;
    }

    /**
     * Constructor de la clase OperacionEntidad que inicializa algunos
     * atributos.
     *
     * @param monto El monto de la operación.
     * @param tipo El tipo de operación (por ejemplo, "transferencia").
     * @param numCuentaEmisora El número de cuenta emisora asociado a la
     * operación.
     * @param numCuentaReceptora El número de cuenta receptora asociado a la
     * operación.
     */
    public OperacionEntidad(float monto, String tipo, int numCuentaEmisora, int numCuentaReceptora) {
        this.monto = monto;
        this.tipo = tipo;
        this.numCuentaEmisora = numCuentaEmisora;
        this.numCuentaReceptora = numCuentaReceptora;
    }

    // Métodos getters y setters
    /**
     * Obtiene el folio único de la operación.
     *
     * @return El folio único de la operación.
     */
    public int getFolio() {
        return folio;
    }

    /**
     * Establece el folio único de la operación.
     *
     * @param folio El nuevo folio único de la operación.
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * Obtiene el monto de la operación.
     *
     * @return El monto de la operación.
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la operación.
     *
     * @param monto El nuevo monto de la operación.
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el tipo de operación.
     *
     * @return El tipo de operación.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de operación.
     *
     * @param tipo El nuevo tipo de operación.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la fecha y hora de ejecución de la operación.
     *
     * @return La fecha y hora de ejecución de la operación.
     */
    public Timestamp getFechaHoraEjec() {
        return fechaHoraEjec;
    }

    /**
     * Establece la fecha y hora de ejecución de la operación.
     *
     * @param fechaHoraEjec La nueva fecha y hora de ejecución de la operación.
     */
    public void setFechaHoraEjec(Timestamp fechaHoraEjec) {
        this.fechaHoraEjec = fechaHoraEjec;
    }

    /**
     * Obtiene el número de cuenta emisora asociado a la operación.
     *
     * @return El número de cuenta emisora asociado a la operación.
     */
    public int getNumCuentaEmisora() {
        return numCuentaEmisora;
    }

    /**
     * Establece el número de cuenta emisora asociado a la operación.
     *
     * @param numCuentaEmisora El nuevo número de cuenta emisora asociado a la
     * operación.
     */
    public void setNumCuentaEmisora(int numCuentaEmisora) {
        this.numCuentaEmisora = numCuentaEmisora;
    }

    /**
     * Obtiene el número de cuenta receptora asociado a la operación.
     *
     * @return El número de cuenta receptora asociado a la operación.
     */
    public int getNumCuentaReceptora() {
        return numCuentaReceptora;
    }

    /**
     * Establece el número de cuenta receptora asociado a la operación.
     *
     * @param numCuentaReceptora El nuevo número de cuenta receptora asociado a
     * la operación.
     */
    public void setNumCuentaReceptora(int numCuentaReceptora) {
        this.numCuentaReceptora = numCuentaReceptora;
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
