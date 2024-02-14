/*
 * DireccionDTO.java
 */
package dtos;

/**
 * Objeto de transferencia para los datos de una dirección.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class DireccionDTO {

    private int codigoDir;
    private String codPos;
    private String colonia;
    private String calle;
    private String numExt;
    private int idCliente;

    /**
     * Constructor por defecto de la clase DireccionDTO.
     */
    public DireccionDTO() {
    }

    /**
     * Constructor de la clase DireccionDTO que inicializa los atributos con los
     * valores especificados.
     *
     * @param codigoDir el código de dirección.
     * @param codPos el código postal.
     * @param colonia la colonia.
     * @param calle la calle.
     * @param numExt el número exterior.
     * @param idCliente el ID del cliente asociado a la dirección.
     */
    public DireccionDTO(int codigoDir, String codPos, String colonia, String calle, String numExt, int idCliente) {
        this.codigoDir = codigoDir;
        this.codPos = codPos;
        this.colonia = colonia;
        this.calle = calle;
        this.numExt = numExt;
        this.idCliente = idCliente;
    }

    /**
     * Devuelve el código de dirección.
     *
     * @return el código de dirección.
     */
    public int getCodigoDir() {
        return codigoDir;
    }

    /**
     * Establece el código de dirección.
     *
     * @param codigoDir el código de dirección.
     */
    public void setCodigoDir(int codigoDir) {
        this.codigoDir = codigoDir;
    }

    /**
     * Devuelve el código postal.
     *
     * @return el código postal.
     */
    public String getCodPos() {
        return codPos;
    }

    /**
     * Establece el código postal.
     *
     * @param codPos el código postal.
     */
    public void setCodPos(String codPos) {
        this.codPos = codPos;
    }

    /**
     * Devuelve la colonia.
     *
     * @return la colonia.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece la colonia.
     *
     * @param colonia la colonia.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Devuelve la calle.
     *
     * @return la calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece la calle.
     *
     * @param calle la calle.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Devuelve el número exterior.
     *
     * @return el número exterior.
     */
    public String getNumExt() {
        return numExt;
    }

    /**
     * Establece el número exterior.
     *
     * @param numExt el número exterior.
     */
    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    /**
     * Devuelve el ID del cliente asociado a la dirección.
     *
     * @return el ID del cliente asociado a la dirección.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente asociado a la dirección.
     *
     * @param idCliente el ID del cliente asociado a la dirección.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
