<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../../header.jsp" />

<title>Pagina dei Risultati</title>
</head>

<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }"
				role="alert">
				${successMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>



			<div class='card'>
				<div class='card-header'>
					<h5>Lista dei risultati</h5>
				</div>
				<div class='card-body'>
					
					<div class='table-responsive'>
						<table class='table table-striped '>
							<thead>
								<tr>
									<th>Descrizione</th>
									<th>Prezzo</th>
									<th>Data acquisto</th>
									<th>Azioni</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${acquisti_list_attribute }"
									var="acquistiItem">
									<tr>
										<td>${acquistiItem.descrizione }</td>
										<td>${acquistiItem.prezzo }</td>
										<td><fmt:formatDate pattern="dd/MM/yyyy"
												value="${acquistiItem.dateBuy}" /></td>
										<td><a class="btn  btn-sm btn-outline-secondary"
											href="${pageContext.request.contextPath }/utente/acquisto/ExecuteVisualizzaAcquistoUtenteServlet?idAcquisto=${acquistiItem.id }">Visualizza</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

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