package dao;

import java.util.List;

import dominio.Recurso;
import excecao.DataException;

public interface DaoRecurso extends Dao {
	
	public List<Recurso> getPorCategoria(int categoria) throws DataException;

}
