package controle;

import java.util.List;

import dao.DaoUsuario;
import dao.DaoUsuarioMemoria;
import dominio.Usuario;
import excecao.DataException;
import excecao.UsuarioInvalidoException;

public class GerenciadorUsuarios {
	private DaoUsuario daoUsuario; 
	
	public GerenciadorUsuarios() {
		this.daoUsuario = DaoUsuarioMemoria.getInstance();
	}
	
	public void cadastrarUsuario(Usuario usuario) throws UsuarioInvalidoException, DataException {
		if(validarUsuario(usuario))
			this.daoUsuario.add(usuario);
	}
	
	public void removerUsuario(Usuario usuario) throws DataException {
		this.daoUsuario.remove(usuario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() throws DataException {
		return (List<Usuario>)(List<?>) daoUsuario.list();
	}
	
	public Usuario getUsuario(String login) throws DataException {
		return daoUsuario.get(login);
	}
	
	public boolean validarUsuario(Usuario usuario) throws UsuarioInvalidoException, DataException {
		
		if(usuario.getLogin().equals("")) {
			throw new UsuarioInvalidoException("Login vazio");
		}
		if(usuario.getNome().equals("")) {
			throw new UsuarioInvalidoException("Nome do usuario vazio");
		}
		if(usuario.getNome().equals("")) {
			throw new UsuarioInvalidoException("Senha vazia");
		}
		if(daoUsuario.get(usuario.getLogin()) != null) {
			throw new UsuarioInvalidoException("Nome de usuario ja esta em uso");
		}
		
		return true;
	}
}
