package beans;

import java.util.Date;

public class OrdenCliente {
    private int id;
    private double total;
    private Date fecha;
    private int numeroConfirmacion;

    public OrdenCliente(int id, double total, Date fecha, int numeroConfirmacion) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.numeroConfirmacion = numeroConfirmacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroConfirmacion() {
        return numeroConfirmacion;
    }

    public void setNumeroConfirmacion(int numeroConfirmacion) {
        this.numeroConfirmacion = numeroConfirmacion;
    }
    
    
    
}
