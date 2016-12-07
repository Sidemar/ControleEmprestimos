package dao;

import java.util.List;

import dominio.Emprestimo;
import excecao.DataException;

public interface DaoHistorico extends Dao {
	
	public List<Emprestimo> getHistoricoCliente(Long codigo) throws DataException;
	
	public Integer findCategoriaAltaByCliente(long codigoCliente) throws DataException;

}
