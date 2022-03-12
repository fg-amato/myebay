package it.prova.myebay.web.servlet.utente.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


@WebServlet("/utente/acquisto/PrepareSearchAcquistiUtenteServlet")
public class PrepareSearchAcquistiUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operationResult = request.getParameter("operationResult");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
			request.setAttribute("successMessage", "Acquisto effettuato con successo");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("ERROR"))
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_FOUND"))
			request.setAttribute("errorMessage", "Elemento non trovato.");
		
		request.getRequestDispatcher("searchAcquisto.jsp").forward(request, response);
	}


}
