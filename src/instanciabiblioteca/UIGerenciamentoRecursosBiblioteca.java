package instanciabiblioteca;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import visao.UIGerenciamentoRecursos;
import controle.GerenciadorRecursos;
import dominio.Recurso;
import excecao.DataException;

public class UIGerenciamentoRecursosBiblioteca implements UIGerenciamentoRecursos {

	private GerenciadorRecursos gerenciadorRecursos = new GerenciadorRecursos();
	private Scanner in = new Scanner(System.in);
	
	@Override
	public void cadastrarRecurso() {
		try {
			System.out.println("---------- Cadastrar Livro ----------");
			
			System.out.print("Codigo: ");			
			Long codigo = in.nextLong();
			in.nextLine();
			System.out.print("Descricao: ");
			String descricao = in.nextLine();
			System.out.print("Categoria: ");
			int categoria = in.nextInt();
			System.out.print("Autor: ");
			String autor = in.nextLine();
			System.out.print("Editora: ");
			String editora = in.nextLine();
			System.out.print("Edicao: ");
			Integer edicao = in.nextInt();
			System.out.print("Quantidade: ");
			Integer quantidade = in.nextInt();
			System.out.print("Titulo: ");
			String titulo = in.nextLine();
			
			Recurso recurso = new Livro(codigo, descricao, categoria, autor, editora, edicao, quantidade, titulo);
			
			gerenciadorRecursos.cadastrarRecurso(recurso);
		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados do recurso. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao cadastrar o livro. Verifique se os dados foram inseridos corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void removerRecurso() {
		try {
			System.out.println("---------- Remover Livro ----------");
		
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			
			Recurso recurso = new Livro(codigo,"", 0);
		
			gerenciadorRecursos.removerRecurso(recurso);
		} catch (DataException e) {
			System.out.println("Erro ao remover registro do recurso. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao remover o livro. Verifique se o codigo foi inserido corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void listarRecursos() {
		try {
			List<Recurso> recursos = gerenciadorRecursos.listarRecursos();
		
			System.out.println("---------- Lista de Livros ----------");
			
			for(Recurso recurso : recursos) {
				Livro livro = (Livro) recurso;
				
				System.out.print("Codigo: " + livro.getCodigo());
				System.out.print(" - Descricao: " + livro.getDescricao());
				System.out.print(" - Categoria: " + livro.getCategoria());
				System.out.print(" - Autor: " + livro.getAutor());
				System.out.print(" - Editora: " + livro.getEditora());
				System.out.print(" - Edicao: " + livro.getEdicao());
				System.out.print(" - Quantidade: " + livro.getQuantidade());
				System.out.println(" - Titulo: " + livro.getTitulo());
				System.out.println("\tDisponivel: " + (livro.isDisponivel() ? "Sim" : "Nao"));
			}
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos recursos. Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar os registros de livros. Erro: " + e.getMessage());
		}
	}

}
