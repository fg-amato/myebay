package it.prova.myebay.service.annuncio;

import java.util.List;

import it.prova.myebay.dao.annuncio.AnnuncioDAO;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {
	public List<Annuncio> listAll() throws Exception;

	public Annuncio caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Annuncio annuncioInstance) throws Exception;

	public void inserisciNuovo(Annuncio annuncioInstance) throws Exception;

	public void rimuovi(Annuncio annuncioInstance) throws Exception;
	
	public List<Annuncio> findByExample(Annuncio example) throws Exception;

	// per injection
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);
}
