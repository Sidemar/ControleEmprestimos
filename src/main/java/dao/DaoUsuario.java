package dao;

import dominio.Usuario;
import excecao.DataException;

public interface DaoUsuario extends Dao {
	
	public Usuario get(String login) throws DataException;

}
