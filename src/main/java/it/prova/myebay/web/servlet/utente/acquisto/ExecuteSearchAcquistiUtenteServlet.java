package it.prova.myebay.web.servlet.utente.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.InvalidUserException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/utente/acquisto/ExecuteSearchAcquistiUtenteServlet")
public class ExecuteSearchAcquistiUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descrizioneParam = request.getParameter("descrizione");
		String prezzoParam = request.getParameter("prezzo");
		String idInput = request.getParameter("idUtente");
		String dataParam = request.getParameter("data");

		if (!NumberUtils.isCreatable(idInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			return;
		}
		Acquisto example = UtilityForm.createAcquistoFromParamsForSearch(descrizioneParam, prezzoParam,
				dataParam);

		try {
			example.setUtenteAcquirente(new Utente(Long.parseLong(idInput)));
			request.setAttribute("acquisti_list_attribute",
					MyServiceFactory.getAcquistoServiceInstance().findByExample(example));
		} catch (InvalidUserException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			return;
		}
		request.getRequestDispatcher("listAcquisti.jsp").forward(request, response);
	}

}
