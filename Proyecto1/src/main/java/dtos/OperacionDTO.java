/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author Usuario
 */
public class OperacionDTO {
    private int folio;
    private String tipo;
    private String monto;
    private String fechaHora;
    private int numCuentaOrigen;
    
    public OperacionDTO() {
        
    }

    public OperacionDTO(int folio, String monto, String tipo, String fechaHora, int numCuentaOrigen) {
        this.folio = folio;
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.numCuentaOrigen = numCuentaOrigen;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getNumCuentaOrigen() {
        return numCuentaOrigen;
    }

    public void setNumCuentaOrigen(int numCuentaOrigen) {
        this.numCuentaOrigen = numCuentaOrigen;
    }
}
