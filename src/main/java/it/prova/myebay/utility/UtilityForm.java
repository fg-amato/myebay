package it.prova.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;

public class UtilityForm {

	public static Annuncio createAnnuncioFromParamsForSearch(String testoAnnuncio, String prezzo,
			String[] categorieId) {

		Annuncio result = new Annuncio();

		result.setTestoAnnuncio(testoAnnuncio);

		if (StringUtils.isNotBlank(prezzo) && NumberUtils.isCreatable(prezzo)) {
			Integer prezzoToInt = Integer.parseInt(prezzo);
			result.setPrezzo(prezzoToInt > 0 ? prezzoToInt : null);
		}

		if (categorieId != null) {
			for (String categorieIdItem : categorieId) {
				if (NumberUtils.isCreatable(categorieIdItem)) {
					result.getCategorie().add(new Categoria(Long.parseLong(categorieIdItem)));
				}
			}
		}

		return result;
	}

	public static Utente createUtenteFromParamsForSearch(String usernameInputParam, String nomeInputParam,
			String cognomeInputParam, String dataCreazioneParam, String[] ruoliInputParam) {

		Utente result = new Utente();
		result.setNome(StringUtils.isBlank(nomeInputParam) ? null : nomeInputParam);
		result.setCognome(StringUtils.isBlank(cognomeInputParam) ? null : cognomeInputParam);
		result.setUsername(StringUtils.isBlank(usernameInputParam) ? null : usernameInputParam);
		result.setDateCreated(parseDateFromString(dataCreazioneParam));

		if (ruoliInputParam != null) {
			for (String ruoloItem : ruoliInputParam) {
				if (NumberUtils.isCreatable(ruoloItem)) {
					result.getRuoli().add(new Ruolo(Long.parseLong(ruoloItem)));
				}
			}
		}

		return result;
	}

	public static Utente createUtenteFromParams(String nome, String cognome, String username, String password) {
		Utente result = new Utente();
		result.setUsername(StringUtils.isBlank(username) ? null : username);
		result.setPassword(StringUtils.isBlank(password) ? null : password);
		result.setNome(StringUtils.isBlank(nome) ? null : nome);
		result.setCognome(StringUtils.isBlank(cognome) ? null : cognome);

		return result;
	}

	public static Utente createUtenteFromParamsForEdit(String nome, String cognome, String username, String stato) {
		Utente result = new Utente();
		result.setUsername(StringUtils.isBlank(username) ? null : username);
		result.setStato(StringUtils.isBlank(stato) ? null : StatoUtente.valueOf(stato));
		result.setNome(StringUtils.isBlank(nome) ? null : nome);
		result.setCognome(StringUtils.isBlank(cognome) ? null : cognome);

		return result;
	}

	public static boolean validateUtenteBeanForEdit(Utente utenteToBeValidated) {
		  // prima controlliamo che non siano vuoti i parametri
		  if (StringUtils.isBlank(utenteToBeValidated.getNome()) || StringUtils.isBlank(utenteToBeValidated.getCognome())
		    || StringUtils.isBlank(utenteToBeValidated.getUsername()) || StringUtils.isBlank(
		      utenteToBeValidated.getStato() != null ? utenteToBeValidated.getStato().toString() : "")) {
		   return false;
		  }
		  return true;
		 }
	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		if (StringUtils.isBlank(utenteToBeValidated.getNome()) || StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername())
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())) {
			return false;
		}
		return true;
	}

	//
	public static Date parseDateFromString(String dataStringParam) {
		if (StringUtils.isBlank(dataStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Map<Ruolo, Boolean> buildCheckedRolesFromRolesAlreadyInUtente(List<Ruolo> listaTotaleRuoli,
			Set<Ruolo> listaRuoliPossedutiDaUtente) {
		Map<Ruolo, Boolean> result = new TreeMap<>();

		// converto array di ruoli in List di Long
		List<Long> ruoliConvertitiInIds = new ArrayList<>();
		for (Ruolo ruoloDiUtenteItem : listaRuoliPossedutiDaUtente != null ? listaRuoliPossedutiDaUtente
				: new ArrayList<Ruolo>()) {
			ruoliConvertitiInIds.add(ruoloDiUtenteItem.getId());
		}
		for (Ruolo ruoloItem : listaTotaleRuoli) {
			result.put(ruoloItem, ruoliConvertitiInIds.contains(ruoloItem.getId()));
		}

		return result;
	}

	public static Map<Ruolo, Boolean> buildCheckedRolesForPages(List<Ruolo> listaTotaleRuoli,
			String[] ruoliFromParams) {
		Map<Ruolo, Boolean> result = new TreeMap<>();

		// converto array di string in List di Long
		List<Long> ruoliIdConvertiti = new ArrayList<>();
		for (String stringItem : ruoliFromParams != null ? ruoliFromParams : new String[] {}) {
			ruoliIdConvertiti.add(Long.valueOf(stringItem));
		}

		for (Ruolo ruoloItem : listaTotaleRuoli) {
			result.put(ruoloItem, ruoliIdConvertiti.contains(ruoloItem.getId()));
		}

		return result;
	}
//	public static boolean validateRegistaBean(Regista registaToBeValidated) {
//		// prima controlliamo che non siano vuoti i parametri
//		if (StringUtils.isBlank(registaToBeValidated.getNome())
//				|| StringUtils.isBlank(registaToBeValidated.getCognome())
//				|| StringUtils.isBlank(registaToBeValidated.getNickName()) || registaToBeValidated.getSesso() == null
//				|| registaToBeValidated.getDataDiNascita() == null) {
//			return false;
//		}
//		return true;
//	}
//
//	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
//		if (StringUtils.isBlank(utenteToBeValidated.getNome()) || StringUtils.isBlank(utenteToBeValidated.getCognome())
//				|| StringUtils.isBlank(utenteToBeValidated.getUsername())
//				|| StringUtils.isBlank(utenteToBeValidated.getPassword())) {
//			return false;
//		}
//		return true;
//	}
//
//	public static Film createFilmFromParams(String titoloInputParam, String genereInputParam,
//			String minutiDurataInputParam, String dataPubblicazioneStringParam, String registaIdStringParam) {
//
//		Film result = new Film(titoloInputParam, genereInputParam);
//		if (NumberUtils.isCreatable(minutiDurataInputParam)) {
//			result.setMinutiDurata(Integer.parseInt(minutiDurataInputParam));
//		}
//		result.setDataPubblicazione(parseDateArrivoFromString(dataPubblicazioneStringParam));
//		if (NumberUtils.isCreatable(registaIdStringParam)) {
//			result.setRegista(new Regista(Long.parseLong(registaIdStringParam)));
//		}
//		return result;
//	}
//
//	public static boolean validateFilmBean(Film filmToBeValidated) {
//		// prima controlliamo che non siano vuoti i parametri
//		if (StringUtils.isBlank(filmToBeValidated.getTitolo()) || StringUtils.isBlank(filmToBeValidated.getGenere())
//				|| filmToBeValidated.getMinutiDurata() == null || filmToBeValidated.getMinutiDurata() < 1
//				|| filmToBeValidated.getRegista() == null || filmToBeValidated.getRegista().getId() == null
//				|| filmToBeValidated.getRegista().getId() < 1) {
//			return false;
//		}
//		return true;
//	}

//

//
//
//		// result.setDateCreated(parseDateArrivoFromString(dataCreazioneStringParam));
//		// result.setRuoli(ruoliInputParam != null && !ruoliInputParam.isEmpty() ?
//		// ruoliInputParam : null);
//		return result;
//	}
}
