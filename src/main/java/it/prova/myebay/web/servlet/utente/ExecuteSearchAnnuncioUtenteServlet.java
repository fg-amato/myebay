package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.InvalidUserException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/utente/annuncio/ExecuteSearchAnnuncioUtenteServlet")
public class ExecuteSearchAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoAnnuncioParam = request.getParameter("testoannuncio");
		String prezzoParam = request.getParameter("prezzo");
		String idInput = request.getParameter("idUtente");
		String[] categorieParam = request.getParameterValues("categoriaInput");

		if(!NumberUtils.isCreatable(idInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			return;
		}
		Annuncio example = UtilityForm.createAnnuncioFromParamsForSearch(testoAnnuncioParam, prezzoParam,
				categorieParam);
		
		try {
			example.setUtenteInserimento(new Utente(Long.parseLong(idInput)));
			request.setAttribute("annunci_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByExampleConUtente(example));
		} catch(InvalidUserException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			return;
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			return;
		}
		request.getRequestDispatcher("listAnnunci.jsp").forward(request, response);
	}

}
