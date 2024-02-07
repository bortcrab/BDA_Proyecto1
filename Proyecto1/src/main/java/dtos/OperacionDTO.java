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
    
    public OperacionDTO() {
        
    }

    public OperacionDTO(int folio, String tipo, String monto, String fechaHora) {
        this.folio = folio;
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
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

    
}
