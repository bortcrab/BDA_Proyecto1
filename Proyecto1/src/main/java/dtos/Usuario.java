/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author jorge
 */
public class Usuario {
    private ClienteDTO cliente;
    private DireccionDTO direccion;
    
    public Usuario() {
        
    }

    public Usuario(ClienteDTO cliente, DireccionDTO direccion) {
        this.cliente = cliente;
        this.direccion = direccion;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }
}
