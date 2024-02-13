package negocio;

import dtos.ClienteDTO;
import persistencia.PersistenciaException;

public interface IClienteNegocio {
    public ClienteDTO buscarCliente(ClienteDTO clienteDTO) throws NegocioException;

    public ClienteDTO guardar(ClienteDTO clienteDTO) throws NegocioException;

    public void editar(ClienteDTO clienteDTO) throws NegocioException;
    
    public String obtenerContrasenia(int idCliente) throws NegocioException;
}
