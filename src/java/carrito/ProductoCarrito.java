/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrito;

import beans.Producto;

/**
 *
 * @author FO-Mañana
 */
public class ProductoCarrito {
    private Producto producto;
    private int cantidad=1;

    public ProductoCarrito(Producto producto) {
        this.producto=producto;
        
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarCantidad(int i) {
        cantidad+=i;
    }

    
    
    
    
    
}
