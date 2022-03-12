package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.InvalidAnnouncementException;
import it.prova.myebay.exceptions.InvalidCreditException;
import it.prova.myebay.exceptions.InvalidUserException;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/utente/annuncio/ExecuteAcquistoUtenteServlet")
public class ExecuteAcquistoUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idAnnuncioParam = request.getParameter("idAnnuncio");
		String idUtenteAcquirenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteAcquirenteParam) || !NumberUtils.isCreatable(idAnnuncioParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("../home.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getAnnuncioServiceInstance().acquisto(Long.parseLong(idUtenteAcquirenteParam),
					Long.parseLong(idAnnuncioParam));
		} catch (InvalidUserException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage",
					"Attenzione si è verificato un errore nella validazione dell'utente acquirente.");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			return;
		} catch (InvalidAnnouncementException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage",
					"Attenzione si è verificato un errore nella selezione dell'annuncio da acquistare.");
			response.sendRedirect(request.getContextPath());
			return;
		} catch (InvalidCreditException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione credito non sufficiente.");
			response.sendRedirect(request.getContextPath());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore nell'acquisto.");
			request.getRequestDispatcher("../home.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("../../ExecuteListAnnuncioServlet?operationResult=SUCCESS");
	}

}
