/*
 * ClienteEntidad.java
 */
package entidades;

import java.sql.Date;

/**
 * Objeto que representa la entidad Cliente.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class ClienteEntidad {

    private int idCliente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private String correo;
    private String contrasenia;

    /**
     * Constructor vacío de la clase ClienteEntidad. Crea un objeto
     * ClienteEntidad con valores predeterminados para sus atributos.
     */
    public ClienteEntidad() {
    }

    /**
     * Constructor de la clase ClienteEntidad que inicializa todos los
     * atributos.
     *
     * @param idCliente El identificador único del cliente.
     * @param nombres Los nombres del cliente.
     * @param apellidoPaterno El apellido paterno del cliente.
     * @param apellidoMaterno El apellido materno del cliente.
     * @param fechaNacimiento La fecha de nacimiento del cliente.
     * @param correo El correo electrónico del cliente.
     * @param contrasenia La contraseña del cliente.
     */
    public ClienteEntidad(int idCliente, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String correo, String contrasenia) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el identificador único del cliente.
     *
     * @return El identificador único del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador único del cliente.
     *
     * @param idCliente El nuevo identificador único del cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene los nombres del cliente.
     *
     * @return Los nombres del cliente.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del cliente.
     *
     * @param nombres Los nuevos nombres del cliente.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido paterno del cliente.
     *
     * @return El apellido paterno del cliente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del cliente.
     *
     * @param apellidoPaterno El nuevo apellido paterno del cliente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del cliente.
     *
     * @return El apellido materno del cliente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del cliente.
     *
     * @param apellidoMaterno El nuevo apellido materno del cliente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     *
     * @return La fecha de nacimiento del cliente.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     *
     * @param fechaNacimiento La nueva fecha de nacimiento del cliente.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return El correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param correo El nuevo correo electrónico del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del cliente.
     *
     * @return La contraseña del cliente.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del cliente.
     *
     * @param contrasenia La nueva contraseña del cliente.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
