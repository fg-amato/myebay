package it.prova.myebay.service.ruolo;

import java.util.List;

import it.prova.myebay.dao.ruolo.RuoloDAO;
import it.prova.myebay.model.Ruolo;

public interface RuoloService {
	public List<Ruolo> listAll() throws Exception;

	public Ruolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ruolo ruoloInstance) throws Exception;

	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception;

	public void rimuovi(Ruolo ruoloInstance) throws Exception;

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception;

	// per injection
	public void setRuoloDAO(RuoloDAO ruoloDAO);
}
