/*
 * ClienteNegocio.java
 */
package negocio;

import dtos.ClienteDTO;
import entidades.ClienteEntidad;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IClienteDAO;
import persistencia.PersistenciaException;

/**
 * La clase ClienteNegocio implementa la lógica de negocio relacionada con las
 * operaciones de clientes. Utiliza una instancia de IClienteDAO para
 * interactuar con la capa de persistencia.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class ClienteNegocio implements IClienteNegocio {

    private IClienteDAO clienteDAO;
    private Logger logger = Logger.getLogger(ClienteNegocio.class.getName());

    /**
     * Constructor de la clase ClienteNegocio.
     *
     * @param clienteDAO la instancia de IClienteDAO a utilizar para acceder a
     * los datos de cliente.
     */
    public ClienteNegocio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     * Busca un cliente en la capa de persistencia basado en la información
     * proporcionada en el objeto ClienteDTO.
     *
     * @param clienteDTO el objeto ClienteDTO con la información del cliente a
     * buscar.
     * @return el objeto ClienteDTO correspondiente al cliente encontrado.
     * @throws NegocioException si ocurre un error durante la búsqueda del
     * cliente.
     */
    @Override
    public ClienteDTO buscarCliente(ClienteDTO clienteDTO) throws NegocioException {
        try {
            // Crear una entidad de cliente y asignar los valores del DTO
            ClienteEntidad clienteEntidad = new ClienteEntidad();
            clienteEntidad.setCorreo(clienteDTO.getCorreo());
            clienteEntidad.setContrasenia(clienteDTO.getContra());

            // Buscar el cliente utilizando el clienteDAO
            clienteEntidad = clienteDAO.buscarCliente(clienteEntidad);

            // Si no se encontró el cliente, lanzar una excepción
            if (clienteEntidad == null) {
                logger.log(Level.SEVERE, "No se encontró el cliente.");
                throw new NegocioException("El correo o la contraseña son erróneos.");
            }

            // Convertir la entidad de cliente encontrada a un DTO y devolverlo
            clienteDTO = convertirClienteEntidad_DTO(clienteEntidad);
            return clienteDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Convierte un objeto ClienteEntidad a un objeto ClienteDTO.
     *
     * @param clienteEntidad el objeto ClienteEntidad a convertir.
     * @return el objeto ClienteDTO resultante de la conversión.
     */
    public ClienteDTO convertirClienteEntidad_DTO(ClienteEntidad clienteEntidad) {
        ClienteDTO clienteDTO = new ClienteDTO(clienteEntidad.getIdCliente(), clienteEntidad.getNombres(), clienteEntidad.getApellidoPaterno(),
                clienteEntidad.getApellidoMaterno(), clienteEntidad.getFechaNacimiento().toString(),
                clienteEntidad.getCorreo(), clienteEntidad.getContrasenia());
        return clienteDTO;
    }

    /**
     * Guarda un nuevo cliente en la capa de persistencia.
     *
     * @param clienteDTO el objeto ClienteDTO con la información del cliente a
     * guardar.
     * @return el objeto ClienteDTO correspondiente al cliente guardado.
     * @throws NegocioException si ocurre un error durante el proceso de
     * guardado.
     */
    @Override
    public ClienteDTO guardar(ClienteDTO clienteDTO) throws NegocioException {
        try {
            // Convertir el objeto ClienteDTO a ClienteEntidad
            ClienteEntidad clienteEntidad = convertirClienteDTO_Entidad(clienteDTO);

            // Guardar el cliente utilizando el clienteDAO
            clienteDAO.guardar(clienteEntidad);

            // Convertir la entidad de cliente guardada a un DTO y devolverlo
            clienteDTO = convertirClienteEntidad_DTO(clienteEntidad);
            return clienteDTO;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Convierte un objeto ClienteDTO a un objeto ClienteEntidad.
     *
     * @param clienteDTO el objeto ClienteDTO a convertir.
     * @return el objeto ClienteEntidad resultante de la conversión.
     */
    public ClienteEntidad convertirClienteDTO_Entidad(ClienteDTO clienteDTO) {
        ClienteEntidad clienteEntidad = new ClienteEntidad(clienteDTO.getId(), clienteDTO.getNombres(),
                clienteDTO.getApellidoP(), clienteDTO.getApellidoM(), java.sql.Date.valueOf(clienteDTO.getFechaNac()),
                clienteDTO.getCorreo(), clienteDTO.getContra());
        return clienteEntidad;
    }

    /**
     * Edita la información de un cliente existente en la capa de persistencia.
     *
     * @param clienteDTO el objeto ClienteDTO con la información actualizada del
     * cliente.
     * @throws NegocioException si ocurre un error durante el proceso de
     * edición.
     */
    @Override
    public void editar(ClienteDTO clienteDTO) throws NegocioException {
        try {
            // Convertir el objeto ClienteDTO a ClienteEntidad
            ClienteEntidad clienteEntidad = convertirClienteDTO_Entidad(clienteDTO);

            // Editar el cliente utilizando el clienteDAO
            clienteDAO.editar(clienteEntidad);
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Obtiene la contraseña de un cliente dado su ID.
     *
     * @param idCliente el ID del cliente.
     * @return la contraseña del cliente.
     * @throws NegocioException si ocurre un error durante la obtención de la
     * contraseña.
     */
    @Override
    public String obtenerContrasenia(int idCliente) throws NegocioException {
        try {
            // Obtener la contraseña utilizando el clienteDAO
            String contrasenia = clienteDAO.obtenerContrasenia(idCliente);
            return contrasenia;
        } catch (PersistenciaException pe) {
            System.out.println(pe.getMessage());
            throw new NegocioException(pe.getMessage());
        }
    }
}
