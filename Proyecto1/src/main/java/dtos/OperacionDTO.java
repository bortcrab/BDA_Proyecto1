/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Usuario
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
    
    public OperacionDTO() {
        
    }

    public OperacionDTO(int folio, String monto, String tipo, String fechaHora, int numCuentaOrigen, int numCuentaDestino, String estado, Timestamp fechaHoraCobro) {
        this.folio = folio;
        this.monto = monto;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.numCuentaOrigen = numCuentaOrigen;
        this.numCuentaDestino = numCuentaDestino;
        this.estado = estado;
        this.fechaHoraCobro = fechaHoraCobro;
    }
    
    public OperacionDTO(int folio, String monto, String tipo, int numCuentaOrigen) {
        this.folio = folio;
        this.monto = monto;
        this.tipo = tipo;
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

    public int getNumCuentaDestino() {
        return numCuentaDestino;
    }

    public void setNumCuentaDestino(int numCuentaDestino) {
        this.numCuentaDestino = numCuentaDestino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaHoraCobro() {
        return fechaHoraCobro;
    }

    public void setFechaHoraCobro(Timestamp fechaHoraCobro) {
        this.fechaHoraCobro = fechaHoraCobro;
    }
    
}
