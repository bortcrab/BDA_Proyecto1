/*
 * ClienteDTO.java
 */
package dtos;

/**
 * Objeto de transferencia para los datos de un cliente.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class ClienteDTO {

    private int id;
    private String nombres;
    private String apellidoP;
    private String apellidoM;
    private String fechaNac;
    private String correo;
    private String contra;

    /**
     * Constructor vacio por defecto
     */
    public ClienteDTO() {

    }

    /**
     * Constructor que inicializa los atributos de la clase
     *
     * @param id Id del cliente
     * @param nombres Nombre del cliente
     * @param apellidoP Apellido Paterno del cliente
     * @param apellidoM Apellido Materno del cliente
     * @param fechaNac Fecha de nacimiento del cliente
     * @param correo Correo del cliente
     * @param contra Contraseña del cliente
     */
    public ClienteDTO(int id, String nombres, String apellidoP, String apellidoM, String fechaNac, String correo, String contra) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.contra = contra;
    }

    /**
     * Constructor que inicializa solo el correo y la contraseña del cliente
     *
     * @param correo Correo del cliente
     * @param contra Contraseña del cliente
     */
    public ClienteDTO(String correo, String contra) {
        this.correo = correo;
        this.contra = contra;
    }

    /**
     * Devuelve el ID del usuario.
     *
     * @return el ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id el ID del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombres el nombre del usuario.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Devuelve el primer apellido del usuario.
     *
     * @return el primer apellido del usuario.
     */
    public String getApellidoP() {
        return apellidoP;
    }

    /**
     * Establece el primer apellido del usuario.
     *
     * @param apellidoP el primer apellido del usuario.
     */
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    /**
     * Devuelve el segundo apellido del usuario.
     *
     * @return el segundo apellido del usuario.
     */
    public String getApellidoM() {
        return apellidoM;
    }

    /**
     * Establece el segundo apellido del usuario.
     *
     * @param apellidoM el segundo apellido del usuario.
     */
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     *
     * @return el correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo el correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getContra() {
        return contra;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contra la contraseña del usuario.
     */
    public void setContra(String contra) {
        this.contra = contra;
    }

    /**
     * Devuelve la fecha de nacimiento del usuario.
     *
     * @return la fecha de nacimiento del usuario.
     */
    public String getFechaNac() {
        return fechaNac;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     *
     * @param fechaNac la fecha de nacimiento del usuario.
     */
    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

}
