package dao;

import java.util.ArrayList;
import java.util.List;

import dominio.Emprestimo;

public class DaoEmprestimoMemoria extends DaoMemoria<Emprestimo> implements DaoEmprestimo {

	protected /*@ spec_public @*/ List<Emprestimo> lista; //@ in list;
	//@ public represents list <- lista;
	
	private static /*@ nullable @*/ DaoEmprestimo daoEmprestimo = null;

	private DaoEmprestimoMemoria() {
		super("Emprestimo");
		this.lista = new ArrayList<>();
	}
	
	public static DaoEmprestimo getInstance() {
		if(daoEmprestimo == null)
			daoEmprestimo = new DaoEmprestimoMemoria();
				
		return daoEmprestimo;
	}

	@Override
	protected List<Emprestimo> getLista() {
		return this.lista;
	}

}
