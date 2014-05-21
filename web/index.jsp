<%-- 
    Document   : index
    Created on : 12-mar-2014, 16:48:46
    Author     : Administrador
--%>





  
<div id="indexLeftColumn">
        <div id="welcomeText">
            <p>[ mensaje de bienvenida ]</p>
            
            
        </div>
    </div>

    <div id="indexRightColumn">
        
       
        <c:forEach  var="categoria" items="${listaCategorias}"  >
            
                <div class="categoryBox">
                 <a href="category?categoriaId=${categoria.id}"><br/>
                   <span class="categoryLabel"></span>
                   <span class="categoryLabelText">${categoria.nombre}</span>
                   <img src="${initParam.categoryImagePath}/${categoria.imagen}" alt="${categoria.nombre}" width="210px" height="174px"/>
                   
                 </a>
             </div>
            
            
        </c:forEach>
        
        
    
            
            
            
  <!--         
            
        <div class="categoryBox">
            <a href="category">
                 <span class="categoryLabel"></span>
                <span class="categoryLabelText">CATEGORIA 2</span>
            </a>
        </div>
        <div class="categoryBox">
            <a href="category">
                 <span class="categoryLabel"></span>
                <span class="categoryLabelText">CATEGORIA 3</span>
            </a>
        </div>
        <div class="categoryBox">
            <a href="category">
                 <span class="categoryLabel"></span>
                <span class="categoryLabelText">CATEGORIA 4</span>
            </a>
        </div>
  
  -->
  
    </div>
    




