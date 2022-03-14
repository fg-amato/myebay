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


@WebServlet("/utente/annuncio/ExecuteEditAnnuncioUtenteServlet")
public class ExecuteEditAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String testoAnnuncioInputParam = request.getParameter("testoannuncio");
		String prezzoInputParam = request.getParameter("prezzo");
		String[] categoriaInputParam = request.getParameterValues("categoriaInput");
		String idInputParam = request.getParameter("idAnnuncio");
		
		
		if(!NumberUtils.isCreatable(idInputParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		
		Annuncio annuncioInstance = UtilityForm.createAnnuncioFromParamsForEdit(testoAnnuncioInputParam, prezzoInputParam);
		try {
			if (!UtilityForm.validateAnnuncioBean(annuncioInstance)) {
				request.setAttribute("update_annuncio_attr", annuncioInstance);
				request.setAttribute("categorie_list_attribute", UtilityForm.buildCheckedCategoriesForPages(
						MyServiceFactory.getCategoriaServiceInstance().listAll(), categoriaInputParam));
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("editAnnuncio.jsp").forward(request, response);
				return;
			}
			annuncioInstance.setId(Long.parseLong(idInputParam));
			MyServiceFactory.getAnnuncioServiceInstance().aggiornaCategorieAnnuncio(annuncioInstance, categoriaInputParam);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("PrepareSearchAnnuncioServlet");
	}
}
