/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrito;

import java.util.ArrayList;
import managers.LoggerManager;

/**
 *
 * @author FO-Mañana
 */
public class CarritoCompra {
    
   private ArrayList<ProductoCarrito> listaCarrito;
   private double totalCarrito;
   private int numProductos;
   final private double GASTOS=6.50;

    public CarritoCompra() {
        listaCarrito=new ArrayList<ProductoCarrito>();
    } 

    public double getGASTOS() {
        return GASTOS;
    }
   
    
    
   public int getNumProductos(){
       int num=0;
       for(int i=0;i<listaCarrito.size();i++){
           num+=listaCarrito.get(i).getCantidad();          
       }
       return num;
      
   }
    
    
    
    public ArrayList<ProductoCarrito> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(ArrayList<ProductoCarrito> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public double getTotalCarrito() {
        totalCarrito=0;
        for (int i=0;i<listaCarrito.size();i++){
            totalCarrito+=(listaCarrito.get(i).getProducto().getPrecio())*(listaCarrito.get(i).getCantidad());
            
        }
        
        
        return totalCarrito;
    }

    public void setTotalCarrito(double totalCarrito) {
        this.totalCarrito = totalCarrito;
    }

    public void agregarProductoCarrito(ProductoCarrito productoCarrito,int cantidad){
       
        boolean existe=false;
    
            for(int i=0;i<listaCarrito.size();i++){                  
                   if(listaCarrito.get(i).getProducto().getId()==productoCarrito.getProducto().getId()){
                       listaCarrito.get(i).agregarCantidad(cantidad);
                      //  LoggerManager.getLog().info("1 " + listaCarrito.get(i).getProducto());
                      //   LoggerManager.getLog().info("2 " + productoCarrito);
                       existe=true;
                   }
            }        
           
            if(existe==false){
                 listaCarrito.add(productoCarrito); 
            }
     }

    public void actualizarCarrito(int prodructIdInt, int cantidadInt) {
       
            for(int i=0;i<listaCarrito.size();i++){                  
                   if(listaCarrito.get(i).getProducto().getId()==prodructIdInt){
                       listaCarrito.get(i).setCantidad(cantidadInt);
                   }
            }  
        
        
    }
 
   
   
   
    
}
