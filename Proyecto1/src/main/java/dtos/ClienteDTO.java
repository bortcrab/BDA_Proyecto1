/*
 * ClienteDTO.java
 */
package dtos;

/**
 * Objeto de transferencia para los datos de un cliente
 * 
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    
}
