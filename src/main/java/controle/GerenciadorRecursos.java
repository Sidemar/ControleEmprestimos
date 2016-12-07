package controle;

import java.util.ArrayList;
import java.util.List;

import dao.DaoRecurso;
import dao.DaoRecursoMemoria;
import dominio.Recurso;
import excecao.DataException;

public class GerenciadorRecursos {
	private DaoRecurso daoRecurso;
	
	public GerenciadorRecursos() {
		this.daoRecurso = DaoRecursoMemoria.getInstance();
	}
	
	public void cadastrarRecurso(Recurso recurso) throws DataException {
		this.daoRecurso.add(recurso);
	}
	
	public void removerRecurso(Recurso recurso) throws DataException {
		this.daoRecurso.remove(recurso);
	}
	
	@SuppressWarnings("unchecked")
	public List<Recurso> listarRecursos() throws DataException {
		return (List<Recurso>)(List<?>) this.daoRecurso.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Recurso> listarRecursos(boolean isDisponivel) throws DataException {
		List<Recurso> recursos = (List<Recurso>)(List<?>) this.daoRecurso.list();
		
		List<Recurso> resultList = new ArrayList<Recurso>();
		for(Recurso recurso : recursos) {
			if(recurso.isDisponivel() == isDisponivel)
				resultList.add(recurso);
		}
		
		return (List<Recurso>)(List<?>) resultList;
	}

	public Recurso getRecurso(Long codigo) throws DataException {
		return (Recurso) this.daoRecurso.get(codigo);
	}
}
