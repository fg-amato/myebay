package it.prova.myebay.dao.acquisto;

import java.util.List;

import it.prova.myebay.dao.IBaseDAO;
import it.prova.myebay.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto> {
	public List<Acquisto> findByExample(Acquisto input) throws Exception;
}
