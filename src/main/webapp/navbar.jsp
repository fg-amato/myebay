<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
          </li>
          <c:if test = "${userInfo != null }">
          <li class="nav-item">
           <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/utente/home.jsp">Area Riservata</a>
          </li>
         </c:if>
          
          <c:if test = "${userInfo != null && userInfo.isAdmin() }">
          	<li class="nav-item dropdown">
            	<a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Funzionalitą admin</a>
            	<ul class="dropdown-menu" aria-labelledby="dropdown07">
              		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/PrepareSearchUtenteServlet">Ricerca utenti</a></li>
              		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/PrepareInsertUtenteServlet">Inserisci utente</a></li>
              		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/ExecuteListUtenteServlet">Lista utenti</a></li>
            	</ul>
             
          	</li> 
          	
          </c:if>   
        </ul>
      </div>
      <div class="col-md-3 text-end">
      	<c:if test = "${userInfo != null }">
        	<p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome })
     		<a href="${pageContext.request.contextPath}/ExecuteLogoutServlet">Logout</a></p>
      	</c:if>
      </div>
      <div class="col-md-3 text-end">
      	<c:if test = "${userInfo == null }">
        	<p class="navbar-text"> <a href="${pageContext.request.contextPath}/public/PrepareLoginServlet">Login</a></p>
      	</c:if>
      </div>
      
    </div>
  </nav>

  
  
</header>