package negocio;

import dtos.ClienteDTO;
import persistencia.PersistenciaException;

public interface IClienteNegocio {
    public int buscarPorId(int id) throws NegocioException;

    public ClienteDTO guardar(ClienteDTO clienteDTO) throws NegocioException;

    public void editar(ClienteDTO clienteDTO) throws NegocioException;
}
