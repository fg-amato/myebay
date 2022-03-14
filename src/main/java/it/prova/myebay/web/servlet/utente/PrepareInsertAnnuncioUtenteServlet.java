package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class PrepareInsertAnnuncioUtenteServlet
 */
@WebServlet("/utente/annuncio/PrepareInsertAnnuncioUtenteServlet")
public class PrepareInsertAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("categorie_list_attribute",
					UtilityForm.buildCheckedCategoriesFromCategoriesAlreadyInUtente(
							MyServiceFactory.getCategoriaServiceInstance().listAll(), null));
			request.getRequestDispatcher("insertAnnuncio.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}

	}

}
