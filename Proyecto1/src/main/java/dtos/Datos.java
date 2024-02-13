/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import negocio.IClienteNegocio;
import negocio.ICuentaNegocio;
import negocio.IDireccionNegocio;
import negocio.IOperacionNegocio;
import utilerias.Validadores;

/**
 *
 * @author jorge
 */
public class Datos {
    private IClienteNegocio clienteNegocio;
    private IDireccionNegocio direccionNegocio;
    private ICuentaNegocio cuentaNegocio;
    private IOperacionNegocio operacionNegocio;
    private Validadores validadores;

    public Datos(IClienteNegocio clienteNegocio, IDireccionNegocio direccionNegocio, ICuentaNegocio cuentaNegocio, IOperacionNegocio operacionNegocio, Validadores validadores) {
        this.clienteNegocio = clienteNegocio;
        this.direccionNegocio = direccionNegocio;
        this.cuentaNegocio = cuentaNegocio;
        this.operacionNegocio = operacionNegocio;
        this.validadores = validadores;
    }

    public IClienteNegocio getClienteNegocio() {
        return clienteNegocio;
    }

    public void setClienteNegocio(IClienteNegocio clienteNegocio) {
        this.clienteNegocio = clienteNegocio;
    }

    public IDireccionNegocio getDireccionNegocio() {
        return direccionNegocio;
    }

    public void setDireccionNegocio(IDireccionNegocio direccionNegocio) {
        this.direccionNegocio = direccionNegocio;
    }

    public ICuentaNegocio getCuentaNegocio() {
        return cuentaNegocio;
    }

    public void setCuentaNegocio(ICuentaNegocio cuentaNegocio) {
        this.cuentaNegocio = cuentaNegocio;
    }

    public IOperacionNegocio getOperacionNegocio() {
        return operacionNegocio;
    }

    public void setOperacionNegocio(IOperacionNegocio operacionNegocio) {
        this.operacionNegocio = operacionNegocio;
    }
}
