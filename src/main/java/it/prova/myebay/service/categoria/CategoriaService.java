package it.prova.myebay.service.categoria;

import java.util.List;

import it.prova.myebay.dao.annuncio.AnnuncioDAO;
import it.prova.myebay.dao.categoria.CategoriaDAO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public interface CategoriaService {
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Categoria categoriaInstance) throws Exception;

	public Categoria caricaCategoriaConArticoli(Long id) throws Exception;

	// per injection
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	public void aggiungiAnnuncioACategoria(Categoria categoriaInstance, Annuncio annuncioInstance) throws Exception;

	void rimuoviAnnuncioDaCategoria(Categoria categoriaInstance, Annuncio annuncioInstance) throws Exception;

	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);
}
