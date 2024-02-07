package entidades;

import java.sql.Date;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class OperacionEntidad {
    private int folio;
    private String tipo;
    private float monto;
    private Date fechaHoraEjec;

    public OperacionEntidad() {
        
    }
    
    public OperacionEntidad(int folio, String tipo, float monto, Date fechaHoraEjec) {
        this.folio = folio;
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHoraEjec = fechaHoraEjec;
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

    public Date fechaHoraEjec() {
        return fechaHoraEjec;
    }

    public void fechaHoraEjec(Date fechaHoraEjec) {
        this.fechaHoraEjec = fechaHoraEjec;
    }
    
    
}
