package visao;

import java.util.List;
import java.util.Scanner;

import controle.GerenciadorUsuarios;
import dominio.Usuario;
import excecao.DataException;
import excecao.UsuarioInvalidoException;

public class UIGerenciamentoUsuariosConsole implements UIGerenciamentoUsuarios {

	private Scanner in = new Scanner(System.in);
	private GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
	
	@Override
	public void cadastrarUsuario() {
		try {
			System.out.println("---------- Cadastrar Usuario ----------");
			
			Usuario usuario = new Usuario();
			System.out.print("Nome: ");
			usuario.setNome(in.nextLine());
			System.out.print("Login: ");
			usuario.setLogin(in.nextLine());
			System.out.print("Senha: ");
			usuario.setSenha(in.nextLine());

			gerenciadorUsuarios.cadastrarUsuario(usuario);
		} catch (UsuarioInvalidoException e) {
			System.out.println("Usuario invalido inserido. Erro: " + e.getMessage());
		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados do usuario. Erro: " + e.getMessage());
		}
	}

	@Override
	public void removerUsuario() {
		try {
			System.out.println("---------- Remover Usuario ----------");
			
			Usuario usuario = new Usuario();
			System.out.print("Login: ");
			usuario.setLogin(in.nextLine());

			gerenciadorUsuarios.removerUsuario(usuario);
		} catch (DataException e) {
			System.out.println("Erro ao remover registro do usuario. Erro: " + e.getMessage());
		}
	}

	@Override
	public void listarUsuarios() {
		try {
			List<Usuario> usuarios = gerenciadorUsuarios.listarUsuarios();
			
			System.out.println("---------- Lista de Usuarios ----------");
			
			for(Usuario usuario : usuarios) {
				System.out.print("Nome: " + usuario.getNome());
				System.out.print(" - Login: " + usuario.getLogin());
				System.out.print(" - Senha: " + usuario.getSenha());
				System.out.println();
			}
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos usuarios. Erro: " + e.getMessage());
		}
	}

}
