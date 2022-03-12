package it.prova.myebay.dao.annuncio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Annuncio;

public class AnnuncioDAOImpl implements AnnuncioDAO {

	private EntityManager entityManager;

	@Override
	public List<Annuncio> list() throws Exception {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Annuncio", Annuncio.class).getResultList();
	}

	@Override
	public Optional<Annuncio> findOne(Long id) throws Exception {
		Annuncio result = entityManager.find(Annuncio.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Annuncio annuncioInstance) throws Exception {
		if (annuncioInstance == null) {
			throw new Exception("Problema valore in annuncioInstance");
		}
		annuncioInstance = entityManager.merge(annuncioInstance);
	}

	@Override
	public void insert(Annuncio annuncioInstance) throws Exception {
		if (annuncioInstance == null) {
			throw new Exception("Problema valore in annuncioInstance");
		}

		entityManager.persist(annuncioInstance);
	}

	@Override
	public void delete(Annuncio annuncioInstance) throws Exception {
		if (annuncioInstance == null) {
			throw new Exception("Problema valore in annuncioInstance");
		}
		entityManager.remove(entityManager.merge(annuncioInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select a from Annuncio a left join a.categorie c where a.aperto = true ");

		if (StringUtils.isNotEmpty(example.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + example.getTestoAnnuncio() + "%");
		}
		if (example.getPrezzo() != null && example.getPrezzo() >= 0) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", example.getPrezzo());
		}

		if (example.getCategorie() != null && !example.getCategorie().isEmpty()) {
			whereClauses.add("c in :categorie ");
			paramaterMap.put("categorie", example.getCategorie());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public Optional<Annuncio> findByIdFetchingUtente(Long id) throws Exception {
		return entityManager.createQuery(
				"from Annuncio a left join fetch a.utenteInserimento left join fetch a.categorie where a.id = :idAnnuncio ",
				Annuncio.class).setParameter("idAnnuncio", id).getResultList().stream().findFirst();
	}

	@Override
	public List<Annuncio> findAllOpened() throws Exception {
		return entityManager.createQuery("from Annuncio a where a.aperto = true", Annuncio.class).getResultList();
	}

	@Override
	public List<Annuncio> findByExampleConUtente(Annuncio example) throws Exception {
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select a from Annuncio a left join a.categorie c where a.id = a.id ");
		
		whereClauses.add(" a.utenteInserimento.id = :idUtenteInserimento ");
		paramaterMap.put("idUtenteInserimento", example.getUtenteInserimento().getId());
		
		if (StringUtils.isNotEmpty(example.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + example.getTestoAnnuncio() + "%");
		}
		if (example.getPrezzo() != null && example.getPrezzo() >= 0) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", example.getPrezzo());
		}

		if (example.getCategorie() != null && !example.getCategorie().isEmpty()) {
			whereClauses.add("c in :categorie ");
			paramaterMap.put("categorie", example.getCategorie());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
}
