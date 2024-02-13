package entidades;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class OperacionEntidad {
    private int folio;
    private float monto;
    private String tipo;
    private Timestamp fechaHoraEjec;
    private int numCuentaEmisora;
    private int numCuentaReceptora;
    private String estado;
    private Timestamp fechaHoraCobro;

    public OperacionEntidad() {
        
    }

    public OperacionEntidad(int folio, float monto, String tipo, Timestamp fechaHoraEjec, int numCuentaEmisora, int numCuentaReceptora, String estado, Timestamp fechaHoraCobro) {
        this.folio = folio;
        this.monto = monto;
        this.tipo = tipo;
        this.fechaHoraEjec = fechaHoraEjec;
        this.numCuentaEmisora = numCuentaEmisora;
        this.numCuentaReceptora = numCuentaReceptora;
        this.estado = estado;
        this.fechaHoraCobro = fechaHoraCobro;
    }

    public OperacionEntidad(float monto, String tipo, int numCuentaEmisora, int numCuentaReceptora) {
        this.monto = monto;
        this.tipo = tipo;
        this.numCuentaEmisora = numCuentaEmisora;
        this.numCuentaReceptora = numCuentaReceptora;
    }
    
    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Timestamp getFechaHoraEjec() {
        return fechaHoraEjec;
    }

    public void setFechaHoraEjec(Timestamp fechaHoraEjec) {
        this.fechaHoraEjec = fechaHoraEjec;
    }

    public int getNumCuentaEmisora() {
        return numCuentaEmisora;
    }

    public void setNumCuentaEmisora(int numCuentaEmisora) {
        this.numCuentaEmisora = numCuentaEmisora;
    }

    public int getNumCuentaReceptora() {
        return numCuentaReceptora;
    }

    public void setNumCuentaReceptora(int numCuentaReceptora) {
        this.numCuentaReceptora = numCuentaReceptora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaHoraCobro() {
        return fechaHoraCobro;
    }

    public void setFechaHoraCobro(Timestamp fechaHoraCobro) {
        this.fechaHoraCobro = fechaHoraCobro;
    }
    
}
