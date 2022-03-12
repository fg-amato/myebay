package it.prova.myebay.service.annuncio;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.dao.annuncio.AnnuncioDAO;
import it.prova.myebay.dao.categoria.CategoriaDAO;
import it.prova.myebay.dao.utente.UtenteDAO;
import it.prova.myebay.exceptions.InvalidUserException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AnnuncioServiceImpl implements AnnuncioService {

	private AnnuncioDAO annuncioDAO;
	private CategoriaDAO categoriaDAO;
	private UtenteDAO utenteDAO;

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;
	}

	@Override
	public List<Annuncio> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Annuncio caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findOne(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Annuncio annuncioInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			annuncioDAO.update(annuncioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Annuncio annuncioInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			annuncioDAO.insert(annuncioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Annuncio annuncioInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			annuncioDAO.delete(annuncioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Annuncio caricaSingoloElementoEager(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			Optional<Annuncio> result = annuncioDAO.findByIdFetchingUtente(id);
			return result.isPresent() ? result.get() : null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Annuncio> listAllAnnunciAperti() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findAllOpened();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Annuncio> findByExampleConUtente(Annuncio example) throws Exception {

		if (example.getUtenteInserimento() == null || example.getUtenteInserimento().getId() == null) {
			throw new InvalidUserException("L'utente inserito non è stato trovato");
		}
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findByExampleConUtente(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiornaCategorieAnnuncio(Annuncio annuncioInstance, String[] categoriaInputInputParam)
			throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			annuncioDAO.setEntityManager(entityManager);
			categoriaDAO.setEntityManager(entityManager);

			Annuncio annuncioDaAggiornare = annuncioDAO.findByIdFetchingUtente(annuncioInstance.getId()).get();

			annuncioDaAggiornare.setPrezzo(annuncioInstance.getPrezzo());
			annuncioDaAggiornare.setTestoAnnuncio(annuncioDaAggiornare.getTestoAnnuncio());

			entityManager.merge(annuncioDaAggiornare);

			if (annuncioDaAggiornare.getCategorie() != null) {
				for (Categoria item : annuncioDaAggiornare.getCategorie()) {
					item = categoriaDAO.findByIdFetchingAnnunci(item.getId());
					entityManager.merge(item);
					annuncioDaAggiornare.removeFromCategorie(item);
				}
			}

			if (categoriaInputInputParam != null) {
				for (String item : categoriaInputInputParam) {
					if (NumberUtils.isCreatable(item)) {
						Categoria temp = categoriaDAO.findByIdFetchingAnnunci(Long.parseLong(item));
						entityManager.merge(temp);
						annuncioDaAggiornare.addToCategorie(temp);
					}
				}
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovoConCategorie(Annuncio annuncioInstance, String[] categoriaInputInputParam, Long id)
			throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			annuncioDAO.setEntityManager(entityManager);
			categoriaDAO.setEntityManager(entityManager);
			utenteDAO.setEntityManager(entityManager);

			Utente utenteInserimento = utenteDAO.findOne(id).get();

			if (utenteInserimento == null) {
				throw new InvalidUserException("Utente che vuole aggiungere annuncio non valido");
			}
			annuncioInstance.setAperto(true);
			annuncioInstance.setUtenteInserimento(utenteInserimento);
			if (categoriaInputInputParam != null) {
				for (String itemId : categoriaInputInputParam) {
					if (NumberUtils.isCreatable(itemId)) {
						annuncioInstance.addToCategorie(categoriaDAO.findOne(Long.parseLong(itemId)).get());
					}
				}
			}

			annuncioDAO.insert(annuncioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}
}
