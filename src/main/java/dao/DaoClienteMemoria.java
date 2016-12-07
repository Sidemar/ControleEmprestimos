package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dominio.Cliente;
import excecao.DataException;

public class DaoClienteMemoria implements DaoCliente {

	private static /*@ nullable @*/ DaoCliente daoCliente = null;
	
	private List<Cliente> clientes; //@ in listaClientes;
	//@ private represents listaClientes <- clientes;
	
	public static DaoCliente getInstance() {
		if(daoCliente == null)
			daoCliente = new DaoClienteMemoria();
				
		return daoCliente;
	}
	
	private DaoClienteMemoria() {
		clientes = new ArrayList<Cliente>();
	}
	
	@Override
	public void add(Cliente cliente) throws DataException {
		if(!clientes.contains(cliente)){
			clientes.add(cliente);
		} else {
			throw new DataException("Cliente já Cadastrado");
		}
	}

	@Override
	public void remove(Cliente cliente) throws DataException {
		Iterator<Cliente> it = clientes.iterator();
		while(it.hasNext()) {
			Cliente c = it.next();
			
			//Remove o objeto armazenado se o codigo for igual
			if(c.getCodigo().equals(cliente.getCodigo())) {
				it.remove();
				return;
			}
		}
		throw new DataException("Cliente nao encontrado");
	}

	@Override
	public void update(Cliente cliente) throws DataException {
		Iterator<Cliente> it = clientes.iterator();
		while(it.hasNext()) {
			Cliente c = it.next();
			
			//Atualiza objeto armazenado se o codigo for igual
			if(c.getCodigo().equals(cliente.getCodigo())) {
				c = cliente;
				return;
			}
		}
	}

	@Override
	public Cliente get(long codigo) throws DataException {
		if(codigo <= 0L) {
			throw new DataException("Codigo menor que zero");
		}
		
		Iterator<Cliente> it = clientes.iterator();
		while(it.hasNext()) {
			Cliente c = it.next();
			if(c.getCodigo().equals(codigo)) {
				return c;
			}
		}
		
		throw new DataException("Cliente não cadastrado");
	}

	@Override
	public List<Cliente> list() throws DataException{
		if(clientes == null){
			throw new DataException("Não há clientes cadastrados.");
		}
		return new ArrayList<Cliente>(clientes);
	}

	@Override
	public boolean exists(long codigo) {
		List<Cliente> list;
		try{
			list = list();
		} catch (DataException e){
			return false;
		}
		
		if(list.isEmpty()){
			return false;
		} else {
			return list.stream().filter(x -> {	return x.getCodigo()!= null && x.getCodigo().equals(codigo);}).count() > 0;
		}		
	}

	@Override
	public void clear() {
		if(clientes != null){
			clientes.clear();
		}
	}
	
}
