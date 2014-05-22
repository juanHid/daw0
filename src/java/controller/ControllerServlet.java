
package controller;

import beans.Categoria;
import beans.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.LoggerManager;



public class ControllerServlet extends HttpServlet {

    ArrayList<Categoria> listaCategorias;
    
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        String prefix = getServletContext().getRealPath("/");
        LoggerManager.prefix = prefix;
        listaCategorias = new ArrayList<Categoria>();
        createCategoriasBeans();
        
        //lo guardamos en la aplicación
        getServletContext().setAttribute("listaCategorias", listaCategorias);
     
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
        String userPath= request.getServletPath();
        String url=null;
        String categoriaId;
        int categoriaIdInt;
        Categoria catTemp;
       
        if(userPath.equals("/category")){
           //Muestra pagina de productos por categoria
            
           //Pagina a la que nos dirigiremos despues 
           url="/WEB-INF/view/category.jsp";
           //obtenemos el valor de categoriaId que nos viene en la url
           // y lo pasamos a int
           categoriaId=request.getParameter("categoriaId");
           categoriaIdInt=Integer.parseInt(categoriaId);
           // Obtenemos la categoria que corresponde a ese id
           catTemp=buscaCategoria(categoriaIdInt);
         
           
           //Esto lo debemos almacenar en session para visualizarlo en el jsp
           request.getSession().setAttribute("catId", categoriaIdInt);
           request.getSession().setAttribute("categoriaSeleccionada", catTemp);
 
           
           //Para probar la pagina de error:
           //creamos un objeto categoria en session
          // request.getSession().setAttribute("categoria", url);
          //despues en el archivo al que nos dirijimos , llamamos
          //al objeto categoria con una propiedad inexistente para que salte error
          //${categoria.nombre}
 
           
        }else if (userPath.equals("/viewCart")){
             //muestra contenido del carrito
              url="/WEB-INF/view/cart.jsp";
              
        }else if (userPath.equals("/checkout")){
            //muestra pagina datos personales para el pedido
             url="/WEB-INF/view/checkout.jsp";
        }else {
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
        String userPath= request.getServletPath();
        String url=null;
       
        if(userPath.equals("/addToCart")){
            //agrega producto al carrito
           url="/WEB-INF/view/category.jsp";
            
        }else if (userPath.equals("/updateCart")){
             //aumenta la cantidad del producto en el carrito
              url="/WEB-INF/view/cart.jsp";
              
        }else if (userPath.equals("/purchase")){
            //resumen del pedido
             url="/WEB-INF/view/confirmation.jsp";
        }else {
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
        
        ArrayList<Producto> listaEuropa=new ArrayList<Producto>();
        listaEuropa.add(barcelona); 
        listaEuropa.add(london);
        listaEuropa.add(paris);
        listaEuropa.add(amsterdam);
        
        ArrayList<Producto> listaAmerica=new ArrayList<Producto>();
        listaAmerica.add(newyork); 
        listaAmerica.add(acapulco);
        listaAmerica.add(machupichu);
        listaAmerica.add(colorado);
        
        ArrayList<Producto> listaAfrica=new ArrayList<Producto>();
        listaAfrica.add(zimbawe); 
        listaAfrica.add(marruecos);
        listaAfrica.add(tunez);
        listaAfrica.add(egipto);
        
        ArrayList<Producto> listaAsia=new ArrayList<Producto>();
        listaAsia.add(bangkok); 
        listaAsia.add(hochiminh);
        listaAsia.add(pekin);
        listaAsia.add(tokyo);
        
        Categoria europa=new Categoria(1,"Europa","europa.jpg",listaEuropa);
        Categoria america=new Categoria(2,"America","america.jpg",listaAmerica);
        Categoria africa=new Categoria(3,"Africa","africa.png",listaAfrica);
        Categoria asia=new Categoria(4,"Asia","asia.png",listaAsia);
        
        listaCategorias.add(europa);
        listaCategorias.add(america);
        listaCategorias.add(africa);
        listaCategorias.add(asia);
        
    }

    private Categoria buscaCategoria(int categoriaIdInt) {
        Categoria catTemp=null;
        
        for(int i=0;i<listaCategorias.size();i++){
            
            if(listaCategorias.get(i).getId()==categoriaIdInt){
                catTemp=listaCategorias.get(i);
                 return catTemp;
            }      
        }
 
        return catTemp;    
    }

}
