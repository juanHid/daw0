
<%@page errorPage="../error.jsp" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/daw.css">
        <title>DAW - Comercio Eletronico</title>
    </head>
    <body>
        <div id="main">
            <div id="header">
                <div id="widgetBar">

                    <div class="headerWidget">
                        <%-- visualizar el enlance de la realizar pedido
                            * si el carrito existe y no esta vacio
                            * si el servlet path no esta en /pedido 
                            * si la vista pedida no es pedido y carrito
                            * si el servlet path no esta en /carrito
                            
                        --%>
                        <a href="checkout" class="bubble">
                        realizar pedido &#x279f;
                         </a>
                        
                    </div>  

                    <div class="headerWidget" id="viewCart">
                         ${carrito.numProductos}
                        <img src="${initParam.images}/cart.gif" alt="carrito" id="carrito">
                        <%-- si el numeroElementos no existe o es 0, sino imprimir su valor --%>
                        <span class="horizontalMargin">
                       
                        </span>
                        <a href="viewCart" class="bubble">
                          ver carrito 
                      </a>
                    </div>

                </div>

                <a href="index.jsp">
                    <img src="${initParam.images}/logo.jpg" id="logo" alt="logo">
                </a>

                <img src="${initParam.images}/europa.png" id="logoText" alt="ejemplo DAW">
                <img src="${initParam.images}/stucom.png" id="logoText2" alt="ejemplo DAW">
            </div>