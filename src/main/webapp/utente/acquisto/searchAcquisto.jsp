<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../../header.jsp" />
		<!-- Custom styles per le features di bootstrap 'Columns with icons' -->
	   <link href="../../assets/css/features.css" rel="stylesheet">
	   
	   <title>Cerca Acquisti</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   		
	   
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				
				<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
			    
			    
			    <div class='card'>
				<div class='card-header'>
					<h3>Ricerca tra i tuoi acquisti</h3>
				</div>
				<div class='card-body'>
					<form method="post" action="${pageContext.request.contextPath}/utente/acquisto/ExecuteSearchAcquistiUtenteServlet"
						class="row g-3">


						<div class="col-md-6">
							<label for="descrizione" class="form-label">Descrizione</label> <input
								type="text" name="descrizione" id="descrizione" class="form-control"
								placeholder="Inserire descrizione">
						</div>

						<div class="col-md-6">
							<label for="prezzo" class="form-label">A partire da: (euro)</label> <input
								type="number" name="prezzo" id="prezzo" class="form-control"
								placeholder="Inserire il prezzo">
						</div>
						
						<div class="col-md-4">
									<label for="data" class="form-label">A partire da: (data)</label>
									<input class="form-control"  name="data" id="data" type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa"> 
						</div>
						
						<div class="col-12">
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-primary">Ricerca</button>
							<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
						</div>

						<input type ="hidden" name= "idUtente" value = "${userInfo.id}">
					</form>



					<!-- end card-body -->
				</div>
				<!-- end card -->
			</div>
			    
			  </div>
			  
		
			  
			</main>
			<br>
			<!-- Footer -->
			<jsp:include page="../../footer.jsp" />
	  </body>
</html>