/*
 * Datos.java
 */
package negocio;

/**
 * Objeto de transferencia que contiene instancias de negocios y validadores
 * para operaciones relacionadas con clientes, direcciones, cuentas y
 * operaciones. Este objeto permite agrupar los diferentes componentes
 * necesarios para llevar a cabo operaciones en el sistema.
 *
 * @author Juventino López García
 * @author Diego Valenzuela Parra
 */
public class Datos {

    private IClienteNegocio clienteNegocio;
    private IDireccionNegocio direccionNegocio;
    private ICuentaNegocio cuentaNegocio;
    private IOperacionNegocio operacionNegocio;

    /**
     * Constructor de la clase Datos que inicializa todas las instancias de
     * negocios y validadores.
     *
     * @param clienteNegocio el objeto de negocio para operaciones relacionadas
     * con clientes.
     * @param direccionNegocio el objeto de negocio para operaciones
     * relacionadas con direcciones.
     * @param cuentaNegocio el objeto de negocio para operaciones relacionadas
     * con cuentas.
     * @param operacionNegocio el objeto de negocio para operaciones generales.
     * @param validadores los validadores utilizados para verificar datos.
     */
    public Datos(IClienteNegocio clienteNegocio, IDireccionNegocio direccionNegocio, ICuentaNegocio cuentaNegocio, IOperacionNegocio operacionNegocio) {
        this.clienteNegocio = clienteNegocio;
        this.direccionNegocio = direccionNegocio;
        this.cuentaNegocio = cuentaNegocio;
        this.operacionNegocio = operacionNegocio;
    }

    /**
     * Obtiene el objeto de negocio para operaciones relacionadas con clientes.
     *
     * @return el objeto de negocio para operaciones relacionadas con clientes.
     */
    public IClienteNegocio getClienteNegocio() {
        return clienteNegocio;
    }

    /**
     * Establece el objeto de negocio para operaciones relacionadas con
     * clientes.
     *
     * @param clienteNegocio el objeto de negocio para operaciones relacionadas
     * con clientes.
     */
    public void setClienteNegocio(IClienteNegocio clienteNegocio) {
        this.clienteNegocio = clienteNegocio;
    }

    /**
     * Obtiene el objeto de negocio para operaciones relacionadas con
     * direcciones.
     *
     * @return el objeto de negocio para operaciones relacionadas con
     * direcciones.
     */
    public IDireccionNegocio getDireccionNegocio() {
        return direccionNegocio;
    }

    /**
     * Establece el objeto de negocio para operaciones relacionadas con
     * direcciones.
     *
     * @param direccionNegocio el objeto de negocio para operaciones
     * relacionadas con direcciones.
     */
    public void setDireccionNegocio(IDireccionNegocio direccionNegocio) {
        this.direccionNegocio = direccionNegocio;
    }

    /**
     * Obtiene el objeto de negocio para operaciones relacionadas con cuentas.
     *
     * @return el objeto de negocio para operaciones relacionadas con cuentas.
     */
    public ICuentaNegocio getCuentaNegocio() {
        return cuentaNegocio;
    }

    /**
     * Establece el objeto de negocio para operaciones relacionadas con cuentas.
     *
     * @param cuentaNegocio el objeto de negocio para operaciones relacionadas
     * con cuentas.
     */
    public void setCuentaNegocio(ICuentaNegocio cuentaNegocio) {
        this.cuentaNegocio = cuentaNegocio;
    }

    /**
     * Obtiene el objeto de negocio para operaciones generales.
     *
     * @return el objeto de negocio para operaciones generales.
     */
    public IOperacionNegocio getOperacionNegocio() {
        return operacionNegocio;
    }

    /**
     * Establece el objeto de negocio para operaciones generales.
     *
     * @param operacionNegocio el objeto de negocio para operaciones generales.
     */
    public void setOperacionNegocio(IOperacionNegocio operacionNegocio) {
        this.operacionNegocio = operacionNegocio;
    }
}
