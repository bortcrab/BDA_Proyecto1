package negocio;

import dtos.ClienteDTO;
import entidades.ClienteEntidad;
import java.text.SimpleDateFormat;
import persistencia.IClienteDAO;
import persistencia.PersistenciaException;
import utilerias.Utilidades;
import java.sql.Date;

public class ClienteNegocio implements IClienteNegocio {

    private IClienteDAO clienteDAO;

    public ClienteNegocio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    @Override
    public ClienteDTO buscarCliente(ClienteDTO clienteDTO) throws NegocioException {
        try {
            ClienteEntidad clienteEntidad = convertirClienteDTO_Entidad(clienteDTO);
            clienteEntidad = this.clienteDAO.buscarCliente(clienteEntidad);
            clienteDTO = convertirClienteEntidad_DTO(clienteEntidad);
            if (clienteEntidad == null) {
                throw new NegocioException("No se encontró el cliente con esa ID.");
            }
            return clienteDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }
    
    public ClienteDTO convertirClienteEntidad_DTO(ClienteEntidad clienteEntidad) {
        ClienteDTO clienteDTO = new ClienteDTO(clienteEntidad.getIdCliente(), clienteEntidad.getNombres(), clienteEntidad.getApellidoPaterno(),
                clienteEntidad.getApellidoMaterno(), clienteEntidad.getFechaNacimiento().toString(),
                clienteEntidad.getCorreo(), clienteEntidad.getContrasenia());
        return clienteDTO;
    }
    
    @Override
    public ClienteDTO guardar(ClienteDTO clienteDTO) throws NegocioException {
        try {
            ClienteEntidad clienteEntidad = convertirClienteDTO_Entidad(clienteDTO);
            this.clienteDAO.guardar(clienteEntidad);
            clienteDTO = convertirClienteEntidad_DTO(clienteEntidad);
            return clienteDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }
    
    public ClienteEntidad convertirClienteDTO_Entidad(ClienteDTO clienteDTO) {
        ClienteEntidad clienteEntidad = new ClienteEntidad(clienteDTO.getNombres(),
                clienteDTO.getApellidoP(), clienteDTO.getApellidoM(), java.sql.Date.valueOf(clienteDTO.getFechaNac()),
                clienteDTO.getCorreo(), clienteDTO.getContra());
        return clienteEntidad;
    }
    
    @Override
    public void editar(ClienteDTO clienteDTO) throws NegocioException {
//        try {
//            this.clienteDAO.editar(clienteEntidad);
//        } catch (PersistenciaException pe) {
//            System.out.println(pe.getMessage());
//            throw new NegocioException(pe.getMessage());
//        }
    }
    
    private boolean esNumeroNegativo(int numero) {
        return numero < 1;
    }

    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }
}
