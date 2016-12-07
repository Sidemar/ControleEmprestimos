package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dominio.Recurso;
import excecao.DataException;

public class DaoRecursoMemoria extends DaoMemoria<Recurso> implements DaoRecurso {

	protected /*@ spec_public @*/ List<Recurso> lista; //@ in list;
	//@ public represents list <- lista;
	
	private static /*@ nullable @*/ DaoRecurso daoRecurso = null;
	
	public DaoRecursoMemoria() {
		super("Recurso");
		this.lista = new ArrayList<>();
	}	
	
	public static DaoRecurso getInstance() {
		if(daoRecurso == null)
			daoRecurso = new DaoRecursoMemoria();
		
		return daoRecurso;
	}
	
	@Override
	public List<Recurso> getPorCategoria(int categoria) throws DataException {
		List<Recurso> resultList = new ArrayList<Recurso>();
		
		Iterator<Recurso> it = lista.iterator();
		while(it.hasNext()) {
			Recurso recurso = it.next();
			
			if(recurso.getCategoria() == categoria) {
				resultList.add(recurso);
			}
		}
		
		return resultList;
	}
	
	@Override
	protected List<Recurso> getLista() {
		return this.lista;
	}

}
