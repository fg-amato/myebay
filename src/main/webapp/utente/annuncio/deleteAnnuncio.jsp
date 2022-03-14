<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../../header.jsp" />
	   
	   <title>Rimuovi Annuncio</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  
			  
			  	<div class="alert alert-danger alert-dismissible fade delete ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade delete d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade delete d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Rimuovi annuncio</h5> 
				    </div>
				    <div class='card-body'>
		
		
		
							<form method="post" action="${pageContext.request.contextPath }/utente/annuncio/ExecuteDeleteAnnuncioUtenteServlet" class="row g-3" novalidate="novalidate">
							
								<div class='card-body'>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo annuncio:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.prezzo}</dd>
					    	</dl>
					    	
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data pubblicazione:</dt>
							   <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_annuncio_attr.datePost}" /></dd>
					    	</dl>
					    	
					    	<!-- info Categorie -->
					    	<p>
							  <a class="btn btn-outline-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Info Categorie
							  </a>
							</p>
							<div class="collapse" id="collapseExample">
							  <div class="card card-body">
							  	<dl class= "row">
					    		<dt class = "col-sm-3 text-right"> Categorie:</dt>
					    		<c:forEach items="${delete_annuncio_attr.categorie}" var="categoria">
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
							
									<input type ="hidden" name= "idAnnuncio" value = "${delete_annuncio_attr.id}">
								
								
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-danger">Rimuovi</button>
		 							<a href="${pageContext.request.contextPath }/utente/home.jsp" class='btn btn-outline-secondary' style='width:80px'>
					            		<i class='fa fa-chevron-left'></i> Home
					        		</a>
							  </div>
		
						</form>
  
				   		
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../../footer.jsp" />
	  </body>
</html>