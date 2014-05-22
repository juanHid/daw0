<%--
    Document   : categoria
    Created on : 12-mar-2014, 16:48:46
    Author     : Administrador
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

  
                
                

            <div id="categoryRightColumn">
                <p id="categoryTitle">[ categoría selecionada ]</p>
                
                
                <c:forEach var="producto" items="${categoriaSeleccionada.listaProductos}">
                   <tr>
                        <td class="lightBlue">
                            <img src= "${initParam.productImagePath}/${producto.imagen}" width="140px" height="80px" alt="product image">
                        </td>
                        <td class="lightBlue">
                            ${producto.nombre}  
                            <br>
                            <span class="smallText"> ${producto.descripcion}</span>
                        </td>
                        <td class="lightBlue"> ${producto.precio} &euro; </td>
                        <td class="lightBlue">
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
    

        