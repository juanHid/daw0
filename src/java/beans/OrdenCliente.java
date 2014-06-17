package beans;

import carrito.ProductoCarrito;
import java.util.ArrayList;
import java.util.Date;

public class OrdenCliente {
    private int id;
    private double total;
    private double gastos;
    private String fecha;
    private String numeroConfirmacion;
    private int clienteId;
    private ArrayList<ProductoCarrito> productosPedidos;

    public OrdenCliente(int id, double total, String fecha, String numeroConfirmacion, int clienteId) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.numeroConfirmacion = numeroConfirmacion;
        this.clienteId = clienteId;
    }
    
    public OrdenCliente(String numeroConfirmacion, String fecha,double total, double gastos, ArrayList<ProductoCarrito> productosPedidos) {
        this.total=total;
        this.gastos=gastos;
        this.fecha = fecha;
        this.numeroConfirmacion = numeroConfirmacion;
        this.productosPedidos = productosPedidos;
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

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumeroConfirmacion() {
        return numeroConfirmacion;
    }

    public void setNumeroConfirmacion(String numeroConfirmacion) {
        this.numeroConfirmacion = numeroConfirmacion;
    }



    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public ArrayList<ProductoCarrito> getProductosPedidos() {
        return productosPedidos;
    }

    public void setProductosPedidos(ArrayList<ProductoCarrito> productosPedidos) {
        this.productosPedidos = productosPedidos;
    }
    
    
    
}
