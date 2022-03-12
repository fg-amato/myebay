package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class PrepareEditAnnuncioUtenteServlet
 */
@WebServlet("/utente/PrepareEditAnnuncioUtenteServlet")
public class PrepareEditAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idAnnuncioParam = request.getParameter("idAnnuncio");

		if (!NumberUtils.isCreatable(idAnnuncioParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.Qui");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			Annuncio toBeUpdated = MyServiceFactory.getAnnuncioServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(idAnnuncioParam));
			request.setAttribute("update_annuncio_attr", toBeUpdated);
			request.setAttribute("categorie_list_attribute", UtilityForm.buildCheckedCategoriesFromCategoriesAlreadyInUtente(
					MyServiceFactory.getCategoriaServiceInstance().listAll(), toBeUpdated.getCategorie()));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("editAnnuncio.jsp").forward(request, response);
	}
}
