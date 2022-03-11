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

@WebServlet("/admin/ExecuteEditUtenteServlet")
public class ExecuteEditUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");
		String statoInputParam = request.getParameter("stato");
		String[] ruoliInputParam = request.getParameterValues("ruoloInput");
		String idInputParam = request.getParameter("idUtente");
		
		if(!NumberUtils.isCreatable(idInputParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("../utente/home.jsp").forward(request, response);
			return;
		}
		
		Utente utenteInstance = UtilityForm.createUtenteFromParamsForEdit(nomeInputParam, cognomeInputParam,
				usernameInputParam, statoInputParam);
		try {
			if (!UtilityForm.validateUtenteBeanForEdit(utenteInstance)) {
				request.setAttribute("update_utente_attr", utenteInstance);
				request.setAttribute("ruoli_list_attribute", UtilityForm.buildCheckedRolesForPages(
						MyServiceFactory.getRuoloServiceInstance().listAll(), ruoliInputParam));
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("edit.jsp").forward(request, response);
				return;
			}
			utenteInstance.setId(Long.parseLong(idInputParam));
			MyServiceFactory.getUtenteServiceInstance().aggiungiRuoliAUtente(utenteInstance, ruoliInputParam);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("../utente/home.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
