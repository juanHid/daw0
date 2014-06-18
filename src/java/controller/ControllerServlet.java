package controller;

import beans.Categoria;
import beans.Cliente;
import beans.OrdenCliente;
import beans.Producto;
import carrito.CarritoCompra;
import carrito.ProductoCarrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.DatabaseManager;

import managers.LoggerManager;

public class ControllerServlet extends HttpServlet {

    ArrayList<Categoria> listaCategorias;
    ArrayList<Producto> listaProductos;
    //declaro un carrito global
    CarritoCompra carrito;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        String prefix = getServletContext().getRealPath("/");
        LoggerManager.prefix = prefix;

        /* Antigua lista que contenia todos los objetos desde un principio
         añadidos manualmente
         listaCategorias = new ArrayList<Categoria>();
         createCategoriasBeans();
         */
        /*Ahora lo que haremos sera llamar a la base de datos para obtener todas las
         categorias solamente; despues cuando se llame a una categoria consultaremos 
         la base de datos otra vez para obtener todos los productos de esa categoria.
    
         */
        //abrimos conexion
        DatabaseManager.openConnection();

        listaCategorias = new ArrayList<Categoria>();
        carrito = new CarritoCompra();

        //llamamos a una funcion que busque en base de datos todas las categorias
        listaCategorias = buscaCategorias();
        //lo guardamos en session
        getServletContext().setAttribute("listaCategorias", listaCategorias);

        //Cerrar conexion
        DatabaseManager.closeConnection();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Miramos qwuien nos llama para hacer una cosa u otra
        String userPath = request.getServletPath();
        String url = null;
        String categoriaId;
        int categoriaIdInt;
     //   request.getSession().setAttribute("catId", categoriaIdInt);
        
        Categoria catTemp;
        //   ArrayList<Producto> listaProductos=new ArrayList<Producto>();
        // Lo declaramos en el servlet y lo inicializamos aqui para poder 
        // acceder a la lista desde todo el servlet (doGet y doPost)

        listaProductos = new ArrayList<Producto>();
        if (userPath.equals("/category")) {
            //Muestra pagina de productos por categoria

            //Pagina a la que nos dirigiremos despues 
            url = "/WEB-INF/view/category.jsp";
            //obtenemos el valor de categoriaId que nos viene en la url
            // y lo pasamos a int
            categoriaId = request.getParameter("categoriaId");
            categoriaIdInt = Integer.parseInt(categoriaId);
            // Obtenemos la categoria que corresponde a ese id
            catTemp = buscaCategoria(categoriaIdInt);

            //Esto lo debemos almacenar en session para visualizarlo en el jsp
            request.getSession().setAttribute("catId", categoriaIdInt);
            request.getSession().setAttribute("categoriaSeleccionada", catTemp);

            DatabaseManager.openConnection();

            listaProductos = buscaProductos(categoriaIdInt);
            DatabaseManager.closeConnection();

            request.getSession().setAttribute("nuevaListaProductos", listaProductos);
            //Para probar la pagina de error:
            //creamos un objeto categoria en session
            // request.getSession().setAttribute("categoria", url);
            //despues en el archivo al que nos dirijimos , llamamos
            //al objeto categoria con una propiedad inexistente para que salte error
            //${categoria.nombre}
        } else if (userPath.equals("/viewCart")) {
            //muestra contenido del carrito
            url = "/WEB-INF/view/cart.jsp";
            
            if(request.getSession().getAttribute("catId")==null){
                 request.getSession().setAttribute("catId", 1);
                
            }
           
            request.getSession().setAttribute("carrito", carrito);
            

           //  LoggerManager.getLog().info("Hay que limpiar carrito");
            //Quiero limpiar carrito?
            String limpiar = request.getParameter("limpiar");

            if (limpiar != null && limpiar.equals("true")) {
                carrito = new CarritoCompra();
                request.getSession().removeAttribute("carrito");

            }

        } else if (userPath.equals("/checkout")) {
            //muestra pagina datos personales para el pedido 
            url = "/WEB-INF/view/checkout.jsp";
            
            //comprobar si carrito  esta vacio
            
            if(carrito.getTotalCarrito()==0){
               url = "/index.jsp"; 
            }
  
            
        } else {
            //nada
        }

        //Por si hay q imprimir
        // PrintWriter out = response.getWriter();
        // out.println(url);   
        //Log de control
        LoggerManager.getLog().info(url);

        //Pasamos en el request la url por si da error saber qué pagina
        request.setAttribute("view", url);
        //Redireccionamos a la pagina correspondiente para mostrar los datos 
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Miramos qwuien nos llama para hacer una cosa u otra
        String userPath = request.getServletPath();
        String url = null;

        if (userPath.equals("/addToCart")) {
            //agrega producto al carrito
            url = "/WEB-INF/view/category.jsp";

            //recojo valor del campo hidden productoId en category.jsp
            String productoId = request.getParameter("productoId");

            // lo paso a int
            int productoIdInt = Integer.parseInt(productoId);

           // LoggerManager.getLog().info("obtenido int " +productoIdInt);
            //Creo un objeto productoCarrito con el producto seleccionado
            ProductoCarrito productoCarrito = new ProductoCarrito(obtenerProducto(productoIdInt));
            carrito.agregarProductoCarrito(productoCarrito, 1);

            //lo añado a sesion
            request.getSession().setAttribute("carrito", carrito);
            LoggerManager.getLog().info("carrito " + carrito.getListaCarrito().size());

           // LoggerManager.getLog().info("añadido " +producto.getNombre()); 
        } else if (userPath.equals("/updateCart")) {
            //aumenta la cantidad del producto en el carrito
            url = "/WEB-INF/view/cart.jsp";

            //Obtengo valores del formulario de actualizacion del cart
            String cantidad = request.getParameter("quantity");
            String productId = request.getParameter("productId");
            int cantidadInt = Integer.parseInt(cantidad);
            int prodructIdInt = Integer.parseInt(productId);

            if(cantidadInt==0){
                carrito.quitarProducto(prodructIdInt);
                
            }else{
            
                // LoggerManager.getLog().info("cantidadInt " +cantidadInt);
                // LoggerManager.getLog().info("prodructIdInt " +prodructIdInt);
                //Llamo al metodo para cambiar la cantidad de productos del carrito
                carrito.actualizarCarrito(prodructIdInt, cantidadInt);
                //Actualizo el total
                carrito.getTotalCarrito();
            }
        } else if (userPath.equals("/purchase")) {
            //resumen del pedido
            url = "/WEB-INF/view/confirmation.jsp";
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String poblacion = request.getParameter("poblacion");
            String tarjeta = request.getParameter("tarjeta");

            if(  nombre!=null && !nombre.equals("") && email!=null && !email.equals("") &&
               telefono!=null && !telefono.equals("") && direccion!=null && !direccion.equals("") &&
               poblacion!=null && !poblacion.equals("") && tarjeta!=null && !tarjeta.equals("")){

            
            //Llamo a un metodo donde gestiono la orden del cliente y 
            //obtengo su id
            
            Date now = new Date();
            DateFormat df =  DateFormat.getDateInstance();
            String fechaHoy =  df.format(now);
            
            if(carrito.getTotalCarrito()!=0 ){
            
            
                String ordenId = gestionaOrden(nombre, email, telefono, direccion, poblacion, tarjeta, carrito);

                Cliente cliente= new Cliente(nombre, email, telefono, direccion, poblacion, tarjeta);
                OrdenCliente orden= new OrdenCliente(ordenId,fechaHoy,carrito.getTotalCarrito(), carrito.getGASTOS(),carrito.getListaCarrito());

                request.getSession().setAttribute("cliente", cliente);
                request.getSession().setAttribute("orden", orden); 

                //limpiar carrito
                carrito = new CarritoCompra();
                request.getSession().removeAttribute("carrito");
            
            }
            
            
            
            }else{
                
                
                url = "/WEB-INF/view/checkout.jsp";
            }
            
        } else {
            //nada
        }

        //Redireccionamos a la pagina correspondiente para mostrar los datos
        request.setAttribute("view", url);
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void createCategoriasBeans() {

        /*     
         Producto barcelona = new Producto(1,"Barcelona Modernista","Descubre a Gaudí, toma el sol en las preciosas playas de la Barceloneta",1500,"barcelona.jpg",1);
         Producto amsterdam = new Producto(2,"Amsterdam en bicicleta","Descubre la ciudad pedaleando por sus calles y canales",2100,"amsterdam.jpg",1);
         Producto london = new Producto(3,"Londres al completo","Visita la ciudad navegando a través del río Támesis",2101,"london.jpg",1);
         Producto paris = new Producto(4,"Paris la nuit","Diviértete como un niño en EuroDisney",2202,"paris.jpg",1);
        
         Producto acapulco = new Producto(5,"Acapulco ven <br>y salta!","blablablablabla",1500,"acapulco.jpg",2);
         Producto newyork = new Producto(6,"New York muerde <br>la manzana!","blablablablabla",1500,"newyork.jpg",2);
         Producto machupichu = new Producto(7,"Machupicchu ancestral","blablablablabla",1500,"machupichu.jpg",2);
         Producto colorado = new Producto(8,"Colorado: <br>Gran Cañón","blablablablabla",1500,"colorado.jpg",2);
        
         Producto egipto = new Producto(9,"Barcelona modernista","blablablablabla",1500,"egipto.png",3);
         Producto marruecos = new Producto(10,"Barcelona modernista","blablablablabla",1500,"marruecos.jpg",3);
         Producto tunez = new Producto(11,"Barcelona modernista","blablablablabla",1500,"tunez.png",3);
         Producto zimbawe = new Producto(12,"Barcelona modernista","blablablablabla",1500,"zimbawe.png",3);
        
         Producto pekin = new Producto(13,"Barcelona modernista","blablablablabla",1500,"pekin.png",4);
         Producto bangkok = new Producto(14,"Barcelona modernista","blablablablabla",1500,"bangkok.png",4);
         Producto hochiminh = new Producto(15,"Barcelona modernista","blablablablabla",1500,"hochiminh.png",4);
         Producto tokyo = new Producto(16,"Barcelona modernista","blablablablabla",1500,"tokyo.png",4);
       
         ArrayList<Producto> listaEuropa = new ArrayList<Producto>();
         listaEuropa.add(barcelona);
         listaEuropa.add(london);
         listaEuropa.add(paris);
         listaEuropa.add(amsterdam);

         ArrayList<Producto> listaAmerica = new ArrayList<Producto>();
         listaAmerica.add(newyork);
         listaAmerica.add(acapulco);
         listaAmerica.add(machupichu);
         listaAmerica.add(colorado);

         ArrayList<Producto> listaAfrica = new ArrayList<Producto>();
         listaAfrica.add(zimbawe);
         listaAfrica.add(marruecos);
         listaAfrica.add(tunez);
         listaAfrica.add(egipto);

         ArrayList<Producto> listaAsia = new ArrayList<Producto>();
         listaAsia.add(bangkok);
         listaAsia.add(hochiminh);
         listaAsia.add(pekin);
         listaAsia.add(tokyo);

         Categoria europa = new Categoria(1, "Europa", "europa.jpg", listaEuropa);
         Categoria america = new Categoria(2, "America", "america.jpg", listaAmerica);
         Categoria africa = new Categoria(3, "Africa", "africa.png", listaAfrica);
         Categoria asia = new Categoria(4, "Asia", "asia.png", listaAsia);

         listaCategorias.add(europa);
         listaCategorias.add(america);
         listaCategorias.add(africa);
         listaCategorias.add(asia);
         */
    }

    private Categoria buscaCategoria(int categoriaIdInt) {
        Categoria catTemp = null;

        for (int i = 0; i < listaCategorias.size(); i++) {

            if (listaCategorias.get(i).getId() == categoriaIdInt) {
                catTemp = listaCategorias.get(i);
                return catTemp;
            }
        }

        return catTemp;
    }

    private ArrayList<Categoria> buscaCategorias() {
        ArrayList<Categoria> listaCateg = new ArrayList<Categoria>();

        //Creamos los objetos para ejecutar la query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //query
        String categoriaSql = "SELECT * FROM categoria";

        try {
            preparedStatement = DatabaseManager.conn.prepareStatement(categoriaSql);
            resultSet = preparedStatement.executeQuery();
            int id;
            String nombre;
            String imagen;

            while (resultSet.next()) {

                id = resultSet.getInt("id");
                nombre = resultSet.getString("nombre");
                imagen = resultSet.getString("imagen");

                Categoria categoria = new Categoria(id, nombre, imagen);

                listaCateg.add(categoria);
                /*    LoggerManager.getLog().info(id);
                 LoggerManager.getLog().info(nombre);
                 LoggerManager.getLog().info(imagen);*/
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException ex) {
            LoggerManager.getLog().error(ex.toString());

        } finally {

            return listaCateg;

        }

    }

    private ArrayList<Producto> buscaProductos(int categoriaIdInt) {
        ArrayList<Producto> listaProducto = new ArrayList<Producto>();
        int idCat = categoriaIdInt;
        //Creamos los objetos para ejecutar la query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //query
        String productoSql = "SELECT * FROM producto WHERE producto.categoria_id=" + idCat + " ORDER BY precio";

        try {
            preparedStatement = DatabaseManager.conn.prepareStatement(productoSql);
            resultSet = preparedStatement.executeQuery();

            int id;
            String nombre;
            double precio;
            String descripcion;
            String imagen;

            while (resultSet.next()) {

                id = resultSet.getInt("id");
                nombre = resultSet.getString("nombre");
                imagen = resultSet.getString("imagen");
                precio = resultSet.getDouble("precio");
                descripcion = resultSet.getString("descripcion");

                Producto producto = new Producto(id, nombre, descripcion, precio, imagen);

                listaProducto.add(producto);

                /*      
                 LoggerManager.getLog().info(id);
                 LoggerManager.getLog().info(nombre);
                 LoggerManager.getLog().info(imagen);*/
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException ex) {
            LoggerManager.getLog().error(ex.toString());

        } catch (Exception ex) {
            LoggerManager.getLog().error(ex.toString());

        } finally {

            return listaProducto;

        }

    }

    private Producto obtenerProducto(int productoIdInt) {
        Producto producto = null;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == productoIdInt) {

                return listaProductos.get(i);
            }

        }

        return producto;

    }

    private String gestionaOrden(String nombre, String email, String telefono, String direccion, String poblacion, String tarjeta, CarritoCompra carrito) {
        
        double total = carrito.getTotalCarrito()+carrito.getGASTOS();       
        DatabaseManager.openConnection();
        PreparedStatement preparedStatement = null;
        
        String numOrden = generaNumRef();
        
        String clienteSql = "INSERT INTO cliente (nombre,email,telefono,direccion,poblacion,tarjeta)"
                + " VALUES ('" + nombre + "','" + email + "','" + telefono + "','" + direccion + "','" + poblacion + "','" + tarjeta + "')";

          LoggerManager.getLog().info(clienteSql);

       

        String ordenClienteSql = "INSERT INTO orden_cliente (total,numero_confirmacion,cliente_id) "
                + "VALUES ('" + total + "','" + numOrden + "',CLIENTE_ID)";

          LoggerManager.getLog().info(ordenClienteSql);

        String clienteProductoSql = "INSERT INTO orden_cliente_has_producto (orden_cliente_id,producto_id,cantidad)"
                + " VALUES (?, ?, ?)";

       
       // ResultSet resultSet = null;
        try {

            DatabaseManager.conn.setAutoCommit(false);

            int clientId = DatabaseManager.executeUpdate(clienteSql);
            LoggerManager.getLog().info("valor de cliente id " + clientId);

            ordenClienteSql = ordenClienteSql.replaceAll("CLIENTE_ID", String.valueOf(clientId));
            LoggerManager.getLog().info("valor2aSQL " + ordenClienteSql);

            int ordenClienteId = DatabaseManager.executeUpdate(ordenClienteSql);
            LoggerManager.getLog().info("valor de orden cliente " + ordenClienteId);

            preparedStatement = DatabaseManager.conn.prepareStatement(clienteProductoSql);

            for (int i = 0; i < carrito.getListaCarrito().size(); i++) {
                int id = carrito.getListaCarrito().get(i).getProducto().getId();
                int cantidad = carrito.getListaCarrito().get(i).getCantidad();
              
                // sustituyo por los valores de la lista del carrito
                preparedStatement.setInt(1, ordenClienteId);
                preparedStatement.setInt(2, id);
                preparedStatement.setInt(3, cantidad);
                //ejecutar la query
                preparedStatement.executeUpdate();

                LoggerManager.getLog().info("valor bucle " + i + " sql " + preparedStatement);
           

            }
            
           DatabaseManager.conn.commit();
            
        } catch (SQLException ex) {
            
            try {
                DatabaseManager.conn.rollback();
                LoggerManager.getLog().error("error en catch gestionaOrden, no se guardaron los datos "  + ex);
            } catch (SQLException ex1) {
                 LoggerManager.getLog().error("error en catch gestionaOrden "  + ex1);
            }
        }finally{
            try {
                DatabaseManager.conn.setAutoCommit(true);
                preparedStatement.close();
                DatabaseManager.closeConnection();
                
            } catch (SQLException ex) {
                LoggerManager.getLog().error("error en finally-catch gestionaOrden "  + ex);
            }
            
            
        }

       
        return numOrden;
    }

    private String generaNumRef() {
        String numero=null;
        boolean existe=false;
        
        
        do{
        
        for(int i=0; i<9;i++){
            
          int numTmp= (int)(Math.random()*10);
          if(i==0){
             numero=String.valueOf(numTmp);  
          }else{
          numero+=String.valueOf(numTmp);
          LoggerManager.getLog().info(numero);   
          }  
          
        }
        //comprobar que numero referencia no existe 
        
         existe=comprobarExiste(numero);
         LoggerManager.getLog().info(existe);
      
         
         
        }while(existe==true);
        
        //y lo devuelve arriba a la funcion gestionaOrden()
        return numero;
    }

    private boolean comprobarExiste(String numero) {
        boolean existeTmp=false;
        String comprobarSql="SELECT * FROM orden_cliente WHERE numero_confirmacion='"+numero+"'";
        LoggerManager.getLog().info(comprobarSql);
        
        PreparedStatement stm=null;
        ResultSet resultSet=null;
        
        try {
            stm =DatabaseManager.conn.prepareStatement(comprobarSql);
            resultSet=stm.executeQuery();
            while(resultSet.next()){
                
                existeTmp=true;
               
            }
            
            resultSet.close();
            stm.close();
            
            
            
        } catch (SQLException ex) {
            try {
                resultSet.close();
                stm.close();
            } catch (SQLException ex1) {
                LoggerManager.getLog().error(ex1);
            }
            
        }finally{
      
        return existeTmp;
        
        }
        
        
        
    }

}
