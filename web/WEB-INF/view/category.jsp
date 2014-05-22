<%--
    Document   : categoria
    Created on : 12-mar-2014, 16:48:46
    Author     : Administrador

1. En la lista de categorías buscamos cada categoría y si es igual
a la categoría seleccionada que pasamos por la URL entonces la destacamos en 
el HTML si no la mostramos standard.

2. En los producos recorremos la lista de productos de la categoría seleccionada
par mostrar los productos.
Al mismo tiempo comprobamos si el idex del producto es par o impar 
para asignarle un color diferente a la fila que se muestra. 

--%>
<div id="categoryLeftColumn">
     
<c:forEach var="categoria" items="${listaCategorias}">   
    <c:choose>
        <c:when test="${categoria.id == catId}">
            <%--mostramos el html de categoria seleccionada --%>
           <div class="categoryButton" id="selectedCategory">
                    <span class="categoryText">${categoria.nombre}</span>
                </div> 
        </c:when>
        <c:otherwise>
              <a href="category?categoriaId=${categoria.id}" class="categoryButton">
                    <span class="categoryText">${categoria.nombre}</span>
                </a>          
        </c:otherwise>
    </c:choose>    
</c:forEach>

</div>

  
                
               <table id="productTable">    

            <div id="categoryRightColumn">
                <p id="categoryTitle">[ categoría selecionada ]</p>
                
                
                <c:forEach var="producto" items="${categoriaSeleccionada.listaProductos}" varStatus="num">
                    
                    <c:choose>
                        <c:when test="${num.index %2==0}">
                             <c:set var="tipo" value="lightBlue" /> 
                        </c:when>
                        <c:otherwise>
                            <c:set var="tipo" value="white" /> 
                        </c:otherwise>
                        
                    </c:choose>
                    
                   
                      
                     
                    
                    <tr>
                        <td class="${tipo}">
                            <img src= "${initParam.productImagePath}/${producto.imagen}" width="140px" height="80px" alt="product image">
                        </td>
                        <td class="${tipo}">
                            ${producto.nombre}  
                            <br>
                            <span class="smallText"> ${producto.descripcion}</span>
                        </td>
                        <td class="${tipo}"> ${producto.precio} &euro; </td>
                        <td class="${tipo}">
                            <form action="addToCart" method="post">
                                <input type="submit" value="comprar">
                            </form>
                        </td>
                    </tr>  
                    
                    
                </c:forEach>
<%--
                <table id="productTable">
                    <tr>
                        <td class="lightBlue">
                            <img src="#" alt="product image">
                        </td>
                        <td class="lightBlue">
                            [ nombre producto  ]
                            <br>
                            <span class="smallText">[ descripción producto  ]</span>
                        </td>
                        <td class="lightBlue">[ precio ]</td>
                        <td class="lightBlue">
                            <form action="addToCart" method="post">
                                <input type="submit" value="comprar">
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td class="white">
                            <img src="#" alt="product image">
                        </td>
                        <td class="white">
                            [ nombre producto ]
                            <br>
                            <span class="smallText">[ descripción producto ]</span>
                        </td>
                        <td class="white">[ precio ]</td>
                        <td class="white">
                            <form action="addToCart" method="post">
                                <input type="submit" value="comprar">
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td class="lightBlue">
                            <img src="#" alt="product image">
                        </td>
                        <td class="lightBlue">
                            [ nombre producto ]
                            <br>
                            <span class="smallText">[ descripción producto ]</span>
                        </td>
                        <td class="lightBlue">[ precio ]</td>
                        <td class="lightBlue">
                            <form action="addToCart" method="post">
                                <input type="submit" value="comprar">
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td class="white">
                            <img src="#" alt="product image">
                        </td>
                        <td class="white">
                            [ nombre producto ]
                            <br>
                            <span class="smallText">[ descripción producto ]</span>
                        </td>
                        <td class="white">[ precio ]</td>
                        <td class="white">
                            <form action="addToCart" method="post">
                                <input type="submit" value="Comprar">
                            </form>
                        </td>

                    </tr>
--%> 
                </table>
            </div>
    

        