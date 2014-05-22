
    <div id="indexLeftColumn">
        <div id="welcomeText">
            <p>[ Bienvenid@s a Viajes Transcontinental ]</p>
            <p>[ Una tienda virtual para la práctica de JSP ]</p>
            
            
        </div>
    </div>

    <div id="indexRightColumn">
      
     <!-- Creo los bloques de categorias partiendo de las categorias que hay
      en listaCategorias  -->

        <c:forEach  var="categoria" items="${listaCategorias}"  >
            
                <div class="categoryBox">
                 <a href="category?categoriaId=${categoria.id}"><br/>
                   <span class="categoryLabel"></span>
                   <span class="categoryLabelText">${categoria.nombre}</span>
                   
                 <%-- initParam se refiere a la ruta de la aplicacion
                  categoryImagePath es el alias que le hemos dado a images/category en el web.xml
                  Utilizamos ambos para construir la ruta de nuestras imagenes  --%>
                   
                   <img src="${initParam.categoryImagePath}/${categoria.imagen}" alt="${categoria.nombre}" width="210px" height="174px"/>
                   
                 </a>
             </div>                  
        </c:forEach>
  
    </div>
    




