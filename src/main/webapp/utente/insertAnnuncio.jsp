<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Inserisci Annuncio</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci annuncio</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="${pageContext.request.contextPath }/utente/ExecuteInsertAnnuncioUtenteServlet" class="row g-3" novalidate="novalidate">
							
								<div class="col-md-6">
									<label for="testoannuncio" class="form-label">Testo Annuncio:<span class="text-danger">*</span></label>
									<input type="text" name="testoannuncio" id="testoannuncio" class="form-control" placeholder="Inserire il testo annuncio"  
										value="${insert_annuncio_attr.testoAnnuncio}" required>
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo:<span class="text-danger">*</span></label>
									<input type="number" name="prezzo" id="prezzo" class="form-control" placeholder="Inserire il prezzo"  
										value="${insert_annuncio_attr.prezzo}" required>
								</div>
							
									<c:forEach items="${categorie_list_attribute}" var="categoriaEntry">
										<div class="form-check">
											<input class="form-check-input" name="categoriaInput" type="checkbox" value="${categoriaEntry.key.id}" id="categoriaInput-${categoriaEntry.key.id}" ${categoriaEntry.value?'checked':'' }>
											<label class="form-check-label" for="categoriaInput-${categoriaEntry.key.id}" >
												${categoriaEntry.key.codice}
											</label>
										</div>
									</c:forEach>
								
							<div class='card-footer'>
							
									<input type ="hidden" name= "idAnnuncio" value = "${insert_annuncio_attr.id}">
									<input type ="hidden" name= "idUtente" value = "${userInfo.id}">
								
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-primary">Inserisci</button>
		 							<a href="${pageContext.request.contextPath}/utente/home.jsp" class='btn btn-outline-secondary' style='width:80px'>
					            		<i class='fa fa-chevron-left'></i> Home
					        		</a>
					        		<input class="btn btn-outline-warning" type="reset" value="Ripristina">
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
			<jsp:include page="../footer.jsp" />
	  </body>
</html>