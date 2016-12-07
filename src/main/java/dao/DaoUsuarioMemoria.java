package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dominio.Usuario;
import excecao.DataException;

public class DaoUsuarioMemoria extends DaoMemoria<Usuario> implements DaoUsuario {
	
	protected /*@ spec_public @*/ List<Usuario> lista; //@ in list;
	//@ public represents list <- lista;
	
	static /*@ nullable @*/ DaoUsuario daoUsuario = null;
	
	private DaoUsuarioMemoria() {
		super ("Usuario");
		this.lista = new ArrayList<>();
	}
	
	public static DaoUsuario getInstance() {
		if(daoUsuario == null)
			daoUsuario = new DaoUsuarioMemoria();
		
		return daoUsuario;
	}

	@Override
	public Usuario get(String login) throws DataException {
		if("".equals(login)) {
			throw new DataException("Login Vazio");
		}
		
		Iterator<Usuario> it = lista.iterator();
		while(it.hasNext()) {
			Usuario c = it.next();
			if(c.getLogin().equals(login)) {
				return c;
			}
		}
		
		throw new DataException("Cliente não cadastrado");
	}
	
	@Override
	protected List<Usuario> getLista() {
		return this.lista;
	}

}
