package entidades;

import java.sql.Date;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class OperacionEntidad {
    private int folio;
    private float monto;
    private String tipo;
    private Date fechaHoraEjec;
    private int numCuentaEmisora;

    public OperacionEntidad() {
        
    }
    
    public OperacionEntidad(int folio, float monto, String tipo, Date fechaHoraEjec, int numCuentaEmisora) {
        this.folio = folio;
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHoraEjec = fechaHoraEjec;
        this.numCuentaEmisora = numCuentaEmisora;
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

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFechaHoraEjec() {
        return fechaHoraEjec;
    }

    public void setFechaHoraEjec(Date fechaHoraEjec) {
        this.fechaHoraEjec = fechaHoraEjec;
    }

    public int getNumCuentaEmisora() {
        return numCuentaEmisora;
    }

    public void setNumCuentaOrigen(int numCuentaEmisora) {
        this.numCuentaEmisora = numCuentaEmisora;
    }
    
}
