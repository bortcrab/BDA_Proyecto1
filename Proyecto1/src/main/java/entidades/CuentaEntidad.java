/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.Date;

/**
 *
 * @author bortc
 */
public class CuentaEntidad {
    int numCuenta;
    float saldo;
    Date fechaApertura;
    boolean estaEliminado;
    int idCliente;

    public CuentaEntidad() {
    }

    public CuentaEntidad(int numCuenta, float saldo, Date fechaApertura, boolean estaEliminado, int idCliente) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.estaEliminado = estaEliminado;
        this.idCliente = idCliente;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public boolean isEstaEliminado() {
        return estaEliminado;
    }

    public void setEstaEliminado(boolean estaEliminado) {
        this.estaEliminado = estaEliminado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
