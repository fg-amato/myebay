<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Annuncio</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza annuncio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo Annuncio:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Pubblicazione:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_annuncio_attr.datePost}" /></dd>
					    	</dl>
					    	
							<br>
							<!--info Categorie -->
							 <p>
                   				<a class="btn btn-outline-primary btn-sm" data-bs-toggle="collapse" href="#collapseExampleCategoria" role="button" aria-expanded="false" aria-controls="collapseExample">
									Categorie
                   				</a>
                  			</p>
                 			<div class="collapse" id="collapseExampleCategoria">
                   				<div class="card card-body">
                     				<dl class= "row">
                     					<dt class = "col-sm-3 text-right"> Categorie:</dt>
                     					<c:forEach items="${show_annuncio_attr.categorie}" var="categoria">
                       						<dd class="row-sm-9">${ categoria.codice }-${categoria.descrizione }</dd>
                       						<br>
                     					</c:forEach>
                   					</dl>
                     
                   				</div>
                 			<!-- end info Categorie -->
                 			</div>
					    	
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					   	 	
					        <a href="${pageContext.request.contextPath }/utente/home.jsp" class='btn btn-outline-secondary' style='width:100px'>
					            <i class='fa fa-chevron-left'></i> Home
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>