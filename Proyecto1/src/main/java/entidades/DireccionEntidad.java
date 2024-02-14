/*
 * DireccionEntidad.java
 */
package entidades;

/**
 * Objeto que representa la entidad Direccion.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class DireccionEntidad {

    private int codigoDireccion;
    private String codigoPostal;
    private String colonia;
    private String calle;
    private String numExterior;
    private int idCliente;

    /**
     * Constructor vacío de la clase DireccionEntidad. Crea un objeto
     * DireccionEntidad con valores predeterminados para sus atributos.
     */
    public DireccionEntidad() {
    }

    /**
     * Constructor de la clase DireccionEntidad que inicializa todos los
     * atributos.
     *
     * @param codigoDireccion El código único de la dirección.
     * @param codigoPostal El código postal de la dirección.
     * @param colonia La colonia de la dirección.
     * @param calle La calle de la dirección.
     * @param numExterior El número exterior de la dirección.
     * @param idCliente El identificador único del cliente asociado a la
     * dirección.
     */
    public DireccionEntidad(int codigoDireccion, String codigoPostal, String colonia, String calle, String numExterior, int idCliente) {
        this.codigoDireccion = codigoDireccion;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numExterior = numExterior;
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el código único de la dirección.
     *
     * @return El código único de la dirección.
     */
    public int getCodigoDireccion() {
        return codigoDireccion;
    }

    /**
     * Establece el código único de la dirección.
     *
     * @param codigoDireccion El nuevo código único de la dirección.
     */
    public void setCodigoDireccion(int codigoDireccion) {
        this.codigoDireccion = codigoDireccion;
    }

    /**
     * Obtiene el código postal de la dirección.
     *
     * @return El código postal de la dirección.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el código postal de la dirección.
     *
     * @param codigoPostal El nuevo código postal de la dirección.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Obtiene la colonia de la dirección.
     *
     * @return La colonia de la dirección.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece la colonia de la dirección.
     *
     * @param colonia La nueva colonia de la dirección.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene la calle de la dirección.
     *
     * @return La calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece la calle de la dirección.
     *
     * @param calle La nueva calle de la dirección.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el número exterior de la dirección.
     *
     * @return El número exterior de la dirección.
     */
    public String getNumExterior() {
        return numExterior;
    }

    /**
     * Establece el número exterior de la dirección.
     *
     * @param numExterior El nuevo número exterior de la dirección.
     */
    public void setNumExterior(String numExterior) {
        this.numExterior = numExterior;
    }

    /**
     * Obtiene el identificador único del cliente asociado a la dirección.
     *
     * @return El identificador único del cliente asociado a la dirección.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador único del cliente asociado a la dirección.
     *
     * @param idCliente El nuevo identificador único del cliente asociado a la
     * dirección.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
