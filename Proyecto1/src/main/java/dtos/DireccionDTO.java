/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class DireccionDTO {
    private int codigoDir;
    private String codPos;
    private String colonia;
    private String calle;
    private String numExt;
    private int idCliente;
    
    public DireccionDTO() {
        
    }

    public DireccionDTO(int codigoDir, String codPos, String colonia, String calle, String numExt, int idCliente) {
        this.codigoDir = codigoDir;
        this.codPos = codPos;
        this.colonia = colonia;
        this.calle = calle;
        this.numExt = numExt;
        this.idCliente = idCliente;
    }

    public int getCodigoDir() {
        return codigoDir;
    }

    public void setCodigoDireccion(int codigoDir) {
        this.codigoDir = codigoDir;
    }

    public String getCodPos() {
        return codPos;
    }

    public void setCodPos(String codPos) {
        this.codPos = codPos;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
