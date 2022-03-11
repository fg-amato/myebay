<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Regista</title>
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
				        <h5>Modifica utente</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="${pageContext.request.contextPath }/admin/ExecuteEditUtenteServlet" class="row g-3" novalidate="novalidate">
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome:<span class="text-danger">*</span></label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome"  
										value="${update_utente_attr.nome}" required>
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome:<span class="text-danger">*</span></label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome"  
										value="${update_utente_attr.cognome}" required>
								</div>
							
								<div class="col-md-6">
									<label for="username" class="form-label">Username:<span class="text-danger">*</span></label>
									<input type="text" class="form-control" name="username" id="username" placeholder="Inserire username" 
									value="${update_utente_attr.username}" required>
								</div>
								
								<div class="col-md-3">
									<label for="stato" class="form-label">Stato utente:<span class="text-danger">*</span></label>
								    <select class="form-select" id="stato" name="stato" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="ATTIVO" ${update_utente_attr.stato == 'ATTIVO'?'selected':''} >ATTIVO</option>
								      	<option value="CREATO" ${update_utente_attr.stato == 'CREATO'?'selected':''} >CREATO</option>
								      	<option value="DISABILITATO" ${update_utente_attr.stato == 'DISABILITATO'?'selected':''} >DISABILITATO</option>
								    </select>
								</div>
								
								
									<c:forEach items="${ruoli_list_attribute}" var="ruoloEntry">
										<div class="form-check">
											<input class="form-check-input" name="ruoloInput" type="checkbox" value="${ruoloEntry.key.id}" id="ruoloInput-${ruoloEntry.key.id}" ${ruoloEntry.value?'checked':'' }>
											<label class="form-check-label" for="ruoloInput-${ruoloEntry.key.id}" >
												${ruoloEntry.key.codice}
											</label>
										</div>
									</c:forEach>
								
							<div class='card-footer'>
							
									<input type ="hidden" name= "idUtente" value = "${update_utente_attr.id}">
								
								
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-primary">Conferma modifica</button>
		 							<a href="${pageContext.request.contextPath }/admin/ExecuteListUtenteServlet" class='btn btn-outline-secondary' style='width:80px'>
					            		<i class='fa fa-chevron-left'></i> Back
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