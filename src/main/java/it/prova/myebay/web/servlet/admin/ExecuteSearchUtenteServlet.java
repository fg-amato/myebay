package it.prova.myebay.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/admin/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usernameParam = request.getParameter("username");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String dateCreatedParam = request.getParameter("dateCreated");
		String[] ruoliParam = request.getParameterValues("ruoloInput");

		Utente example = UtilityForm.createUtenteFromParamsForSearch(usernameParam, nomeParam, cognomeParam,
				dateCreatedParam, ruoliParam);
		
		try {
			request.setAttribute("utenti_list_attribute",
					MyServiceFactory.getUtenteServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("../utente/home.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

}
