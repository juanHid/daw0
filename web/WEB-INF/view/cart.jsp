<%--
    Document   : carrito
    Created on : 12-mar-2014, 16:48:46
    Author     : Administrador
--%>






            <div id="centerColumn">

                <p>Tu carrito de la compra contiene ${carrito.numProductos} artículos.</p>

                 <div id="actionBar">
                    <%-- si esta definido el carrito i el numero de elemento  es >0--%>
                    <a href="viewCart?limpiar=true" class="bubble hMargin">limpiar carrito</a>
                     <%-- si el usuario ha selecionado una categoria sino me quedo en la pagina inicial--%>
                    <a href="category?categoriaId=${catId}" class="bubble hMargin">continuar la compra</a>
                     <%-- si esta definido el carrito i el numero de elemento es >0 procedo con el pedido--%>
                    <a href="checkout" class="bubble hMargin">proceder con el pedido</a>
                </div>

                <%-- si el carrito esta definiido y tengo elementos en el carrito  --%>  
                <h4 id="subtotal">[ subtotal: ${carrito.totalCarrito} &euro;]</h4>

                <table id="cartTable">

                    <tr class="header">
                        <th>producto</th>
                        <th>nombre</th>
                        <th>precio</th>
                        <th>cantidad</th>
                    </tr>

                    
        <c:forEach var="productoCarrito" items="${carrito.listaCarrito}" varStatus="num"> 
                    
                    
                          
                    
                    
                    <tr>
                        <td class="lightBlue">
                            <img src="${initParam.productImagePath}/${productoCarrito.producto.imagen}" width="140px" height="80px"  alt="${productoCarrito.producto.nombre}">
                        </td>
                        
                        <td class="lightBlue">${productoCarrito.producto.nombre}</td>
                        
                        <td class="lightBlue">
                            ${productoCarrito.producto.precio} &euro;  <%-- precio productos --%>
                            <br>
                            <span class="smallText">
                                [detalles precio unitad]
                               
                            </span>
                        </td>
                        
                        <td class="lightBlue">${productoCarrito.cantidad}

                            <form action="updateCart" method="post">
                                <input type="hidden"
                                       name="productId"
                                       value="${productoCarrito.producto.id}"> <%--id del producto --%>
                                <input type="text"
                                       maxlength="2"
                                       size="2"
                                        <%--cantidad de productos que tengo en el carrito --%>
                                       name="quantity">
                                <input type="submit"
                                       name="submit"
                                       value="actualizar">
                            </form>
                        </td>
                    </tr>
                    
            </c:forEach>         
                    

             <!--       
                    
                    
                     <tr>
                        <td class="white">
                            <img src="#" alt="product image">
                        </td>
                        
                        <td class="white">[ nombre producto ]</td>
                        
                        <td class="white">
                            [&euro; precio ] <%-- precio productos --%>
                            <br>
                            <span class="smallText">
                                [detalles precio unitad]
                                
                            </span>
                        </td>
                        
                        <td class="white">[ cantidad ]

                            <form action="updateCart" method="post">
                                <input type="hidden"
                                       name="productId"
                                       value="productoId"> <%--id del producto --%>
                                <input type="text"
                                       maxlength="2"
                                       size="2"
                                       value="1" <%--cantidad de productos que tengo en el carrito --%>
                                       name="quantity">
                                <input type="submit"
                                       name="submit"
                                       value="actualizar">
                            </form>
                        </td>
                    </tr>

                     <tr>
                        <td class="lightBlue">
                            <img src="#" alt="product image">
                        </td>
                        
                        <td class="lightBlue">[ nombre producto ]</td>
                        
                        <td class="lightBlue">
                            [&euro; precio ] <%-- precio productos --%>
                            <br>
                            <span class="smallText">
                                [detalles precio unitad]
                               
                            </span>
                        </td>
                        
                        <td class="lightBlue">[ cantidad ]

                            <form action="updateCart" method="post">
                                <input type="hidden"
                                       name="productId"
                                       value="productoId"> <%--id del producto --%>
                                <input type="text"
                                       maxlength="2"
                                       size="2"
                                       value="1" <%--cantidad de productos que tengo en el carrito --%>
                                       name="quantity">
                                <input type="submit"
                                       name="submit"
                                       value="actualizar">
                            </form>
                        </td>
                    </tr>
-->
                </table>

            </div>

