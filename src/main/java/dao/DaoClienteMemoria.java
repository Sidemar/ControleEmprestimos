package dao;

import java.util.ArrayList;
import java.util.List;

import dominio.Cliente;

public class DaoClienteMemoria extends DaoMemoria<Cliente> implements DaoCliente {
	
	protected /*@ spec_public @*/ List<Cliente> lista; //@ in list;
	//@ public represents list <- lista;
			
	private static /*@ nullable @*/ DaoCliente daoCliente = null;

	private DaoClienteMemoria() {
		super("Cliente");
		this.lista = new ArrayList<>();
	}
	
	public static DaoCliente getInstance() {
		if(daoCliente == null)
			daoCliente = new DaoClienteMemoria();
				
		return daoCliente;
	}

	@Override
	protected List<Cliente> getLista() {
		return this.lista;
	}
	
	
		
}
