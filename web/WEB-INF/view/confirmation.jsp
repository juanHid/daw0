<%--
    Document   : confirmar
    Created on : 12-mar-2014, 16:48:46
    Author     : Administrador
--%>





    <div id="singleColumn">

        <p id="confirmationText">
            <strong>Tu orden ha sido procesado y será entregado en un plazo de 24 horas.</strong>
            <br><br>
            Tu numero de pedido es:
            <strong>${orden.numeroConfirmacion}</strong>
            <br>
            Si tienes algunas preguntas en relación a tu pedido, <a href="#">contactanos</a>.
            <br><br>
            Gracias por comprar en nuestra tienda!

        </p>

        <div class="summaryColumn" >

            <table id="orderSummaryTable" class="detailsTable" >
                <tr class="header">
                    <th colspan="3">[ detalles de la compra ]</th>
                </tr>
                <tr class="tableHeading">
                    <td>producto</td>
                    <td>cuantidad</td>
                    <td>precio</td>
                </tr>
                
                <c:forEach var="productoPedido" items="${orden.productosPedidos}" varStatus="num">
                <tr class="lightBlue">
                    <td>
                       ${productoPedido.producto.nombre}
                    </td>
                    <td class="quantityColumn">
                        ${productoPedido.cantidad}
                    </td>
                    <td class="confirmationPriceColumn">
                        ${productoPedido.producto.precio*productoPedido.cantidad} &euro; 
                    </td>
                </tr>
                </c:forEach> 
                <!--
                <tr class="white">
                    <td>[nombre producto]</td>
                    <td class="quantityColumn">
                        [cuantidad]
                    </td>
                    <td class="confirmationPriceColumn">
                        &euro; [precio]
                    </td>
                </tr>


                <tr class="lightBlue">
                    <td colspan="3" style="padding: 0 20px"><hr>
                    </td>
                </tr>
                -->
                <tr class="lightBlue">
                    <td colspan="2" id="deliverySurchargeCellLeft">
                        <strong>Gastos de spedicion:</strong>
                    </td>
                    <td id="deliverySurchargeCellRight">
                        ${orden.gastos} &euro;
                    </td>
                </tr>

                <tr class="lightBlue">
                    <td colspan="2" id="totalCellLeft">
                        <strong>total:</strong>
                    </td>
                    <td id="totalCellRight">
                        ${orden.total} &euro; 
                    </td>
                </tr>

                <tr class="lightBlue">
                    <td colspan="3" style="padding: 0 20px"><hr>
                    </td>
                </tr>

                <tr class="lightBlue">
                    <td colspan="3" id="dateProcessedRow">
                        <strong>Fecha pedido:</strong>
                        ${orden.fecha}
                    </td>
                </tr>
            </table>

        </div>

        <div class="summaryColumn" >

            <table id="deliveryAddressTable" class="detailsTable">
                <tr class="header">
                    <th colspan="3">Detalles del envio</th>
                </tr>

                <tr>
                    <td colspan="3" class="lightBlue">
                        ${cliente.nombre}
                        <br>
                        ${cliente.direccion}
                        <br>
                        ${cliente.poblacion}
                        <br><br>
                        <hr><br>
                        <strong>email:</strong>  ${cliente.email}
                        <br>
                        <strong>telefono</strong> ${cliente.telefono}
                    </td>
                </tr>
            </table>

        </div>
    </div>
