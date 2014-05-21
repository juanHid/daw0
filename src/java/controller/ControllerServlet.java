/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.LoggerManager;

/**
 *
 * @author Administrador
 */
public class ControllerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        String prefix = getServletContext().getRealPath("/");
        LoggerManager.prefix = prefix;
    
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
       
        if(userPath.equals("/category")){
            //muestra pagina de productos por categoria
           url="/WEB-INF/view/category.jsp";
           
           //Para probar la pagina de error:
           //creamos un objeto categoria en session
           request.getSession().setAttribute("categoria", url);
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
        
        
        //hacer lo q sea
        
        //Por si hay q imprimir
        // PrintWriter out = response.getWriter();
        // out.println(url);   
        
        
        
        
        //Redireccionamos a la pagina correspondiente para mostrar los datos
       
        LoggerManager.getLog().info(url);
        
        request.setAttribute("view", url);
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

}
