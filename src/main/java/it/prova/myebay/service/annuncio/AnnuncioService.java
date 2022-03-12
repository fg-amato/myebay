package it.prova.myebay.service.annuncio;

import java.util.List;

import it.prova.myebay.dao.annuncio.AnnuncioDAO;
import it.prova.myebay.dao.categoria.CategoriaDAO;
import it.prova.myebay.dao.utente.UtenteDAO;
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

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);
	
	public void setUtenteDAO(UtenteDAO utenteDAO);

	public Annuncio caricaSingoloElementoEager(Long id) throws Exception;

	public List<Annuncio> listAllAnnunciAperti() throws Exception;

	List<Annuncio> findByExampleConUtente(Annuncio example) throws Exception;

	public void aggiornaCategorieAnnuncio(Annuncio annuncioInstance, String[] categoriaInputInputParam)throws Exception;

	public void inserisciNuovoConCategorie(Annuncio annuncioInstance, String[] categoriaInputInputParam, Long idUtente) throws Exception;
}
