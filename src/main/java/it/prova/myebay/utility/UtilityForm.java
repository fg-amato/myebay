package it.prova.myebay.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public class UtilityForm {

	public static Annuncio createAnnuncioFromParamsForSearch(String testoAnnuncio, String prezzo,
			String[] categorieId) {

		Annuncio result = new Annuncio();

		result.setTestoAnnuncio(testoAnnuncio);

		if (StringUtils.isNotBlank(prezzo) && NumberUtils.isCreatable(prezzo)) {
			Integer prezzoToInt = Integer.parseInt(prezzo);
			result.setPrezzo(prezzoToInt > 0 ? prezzoToInt : null);
		}

		if (categorieId !=null) {
			for (String categorieIdItem : categorieId) {
				if (NumberUtils.isCreatable(categorieIdItem)) {
					result.getCategorie().add(new Categoria(Long.parseLong(categorieIdItem)));
				}
			}
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
//	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
//		if (StringUtils.isBlank(dataDiNascitaStringParam))
//			return null;
//
//		try {
//			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
//		} catch (ParseException e) {
//			return null;
//		}
//	}
//
//	public static Map<Ruolo, Boolean> buildCheckedRolesFromRolesAlreadyInUtente(List<Ruolo> listaTotaleRuoli,
//			Set<Ruolo> listaRuoliPossedutiDaUtente) {
//		Map<Ruolo, Boolean> result = new TreeMap<>();
//
//		// converto array di ruoli in List di Long
//		List<Long> ruoliConvertitiInIds = new ArrayList<>();
//		for (Ruolo ruoloDiUtenteItem : listaRuoliPossedutiDaUtente != null ? listaRuoliPossedutiDaUtente
//				: new ArrayList<Ruolo>()) {
//			ruoliConvertitiInIds.add(ruoloDiUtenteItem.getId());
//		}
//		System.out.println("RUOLO DENTRO IL METODO: " + ruoliConvertitiInIds);
//		for (Ruolo ruoloItem : listaTotaleRuoli) {
//			result.put(ruoloItem, ruoliConvertitiInIds.contains(ruoloItem.getId()));
//		}
//
//		return result;
//	}
//
//	public static Utente createUtenteFromParams(String nomeInputParam, String cognomeInputParam,
//			String usernameInputParam, String dataCreazioneStringParam, String passwordInputParam,
//			String[] ruoliInputParam) {
//		Set<Ruolo> ruoliUtente = new HashSet<Ruolo>();
//		Utente result = new Utente();
//		result.setUsername(StringUtils.isBlank(usernameInputParam) ? null : usernameInputParam);
//		result.setPassword(StringUtils.isBlank(passwordInputParam) ? null : passwordInputParam);
//		result.setNome(StringUtils.isBlank(nomeInputParam) ? null : nomeInputParam);
//		result.setCognome(StringUtils.isBlank(cognomeInputParam) ? null : cognomeInputParam);
//
//		if (ruoliInputParam != null) {
//			for (String ruolo : ruoliInputParam) {
//				if (NumberUtils.isCreatable(ruolo)) {
//					Ruolo ruoloDaInserire = new Ruolo();
//					ruoloDaInserire.setId(Long.parseLong(ruolo));
//					ruoliUtente.add(ruoloDaInserire);
//				}
//			}
//
//			result.setRuoli(ruoliUtente);
//		}
//
//		// result.setDateCreated(parseDateArrivoFromString(dataCreazioneStringParam));
//		// result.setRuoli(ruoliInputParam != null && !ruoliInputParam.isEmpty() ?
//		// ruoliInputParam : null);
//		return result;
//	}
}
