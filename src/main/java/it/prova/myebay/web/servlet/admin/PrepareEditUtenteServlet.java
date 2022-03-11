package it.prova.myebay.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/admin/PrepareEditUtenteServlet")
public class PrepareEditUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteListUtenteServlet").forward(request, response);
			return;
		}
		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			Utente toBeUpdated = MyServiceFactory.getUtenteServiceInstance()
					.caricaSingoloElementoConRuoli(Long.parseLong(idUtenteParam));
			request.setAttribute("update_utente_attr", toBeUpdated);
			request.setAttribute("ruoli_list_attribute", UtilityForm.buildCheckedRolesFromRolesAlreadyInUtente(
					MyServiceFactory.getRuoloServiceInstance().listAll(), toBeUpdated.getRuoli()));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteListUtenteServlet").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

}
