package it.prova.myebay.service.acquisto;

import java.util.List;

import it.prova.myebay.dao.acquisto.AcquistoDAO;
import it.prova.myebay.model.Acquisto;

public interface AcquistoService {
	public List<Acquisto> listAll() throws Exception;

	public Acquisto caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Acquisto ruoloInstance) throws Exception;

	public void inserisciNuovo(Acquisto ruoloInstance) throws Exception;

	public void rimuovi(Acquisto ruoloInstance) throws Exception;

	// per injection
	public void setAcquistoDAO(AcquistoDAO acquistoDAO);

	public List<Acquisto> findByExample(Acquisto example) throws Exception;
}
