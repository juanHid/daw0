
package beans;

import java.util.ArrayList;


public class Categoria {
    private int id;
    private String nombre;
    private String imagen;
    private ArrayList<Producto> listaProductos;

    public Categoria(int id, String nombre, String imagen, ArrayList listaProductos) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.listaProductos= listaProductos;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    
    
    
}
