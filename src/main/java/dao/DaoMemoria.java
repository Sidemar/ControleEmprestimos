package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dominio.Dominio;
import excecao.DataException;

public abstract class DaoMemoria<T extends Dominio> implements Dao {
	
	private final String entidade;
		
	public DaoMemoria(String entidade) {
		super();
		this.entidade = entidade;
	}
	
	protected abstract List<T> getLista();

	@Override
	public final void add(Dominio obj) throws DataException {
		@SuppressWarnings("unchecked")
		T cobj = (T) obj;
		if(!getLista().contains(cobj)){
			getLista().add(cobj);
		} else {
			throw new DataException(entidade + " já Cadastrado");
		}
	}

	@Override
	public final void remove(Dominio obj) throws DataException {
		Iterator<T> it = getLista().iterator();
		while(it.hasNext()) {
			Dominio c = it.next();
			
			//Remove o objeto armazenado se o codigo for igual
			if(c.equals(obj)) {
				it.remove();
				return;
			}
		}
		throw new DataException(entidade + " nao encontrado");
	}

	@Override
	public final void update(Dominio obj) throws DataException {	
		Iterator<T> it = getLista().iterator();
		while(it.hasNext()) {
			Dominio c = it.next();
			
			//Atualiza objeto armazenado se o codigo for igual
			if(c.equals(obj)) {
				c = obj;
				return;
			}
		}
		
		throw new DataException(entidade + " não existe");
	}

	@Override
	public final  Dominio get(long codigo) throws DataException {
		if(codigo <= 0L) {
			throw new DataException("Codigo menor que zero");
		}
		
		Iterator<T> it = getLista().iterator();
		while(it.hasNext()) {
			Dominio c = it.next();
			if(Long.compare(c.getCodigo(), codigo) == 0) {
				return c;
			}
		}
		
		throw new DataException("Cliente não cadastrado");
	}

	@Override
	public final List<Dominio> list() throws DataException {
		if(getLista()== null){
			throw new DataException("Não há " + entidade + "s cadastrados.");
		}
		return new ArrayList<Dominio>(getLista());
	}

	@Override
	public final boolean exists(long codigo) {
		List<Dominio> list;
		try{
			list = list();
		} catch (DataException e){
			return false;
		}
		
		if(list.isEmpty()){
			return false;
		} else {
			return list.stream().filter(x -> {
				return Long.compare(x.getCodigo(), codigo) == 0;
			}).count() > 0;
		}		
	}

	@Override
	public final boolean exists(Dominio obj) {
		Iterator<T> it = getLista().iterator();
		while(it.hasNext()) {
			Dominio c = it.next();
			if(Long.compare(c.getCodigo(), obj.getCodigo()) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public final void clear() {
		if(getLista() != null){
			getLista().clear();
		}
	}


	
	
	

}
