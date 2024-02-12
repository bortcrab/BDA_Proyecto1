/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author bortc
 */
public class CuentaDTO {
    int numCuenta;
    String saldo;
    String fechaApertura;
    int idCliente;

    public CuentaDTO() {
    }

    public CuentaDTO(int numCuenta, String saldo, String fechaApertura, int idCliente) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.idCliente = idCliente;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "#" + this.numCuenta;
    }
}
