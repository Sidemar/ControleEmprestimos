package instanciahotel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import visao.UIGerenciamentoRecursos;
import controle.GerenciadorRecursos;
import dominio.Recurso;
import excecao.DataException;

public class UIGerenciamentoRecursosHotel implements UIGerenciamentoRecursos {

	private GerenciadorRecursos gerenciadorRecursos = new GerenciadorRecursos();
	private Scanner in = new Scanner(System.in);
	
	@Override
	public void cadastrarRecurso() {
		try {
			System.out.println("---------- Cadastrar Quarto ----------");
			
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			System.out.print("Descricao: ");
			String descricao = in.nextLine();
			System.out.print("Categoria: ");
			int categoria = in.nextInt();
			System.out.print("Area: ");
			double area = in.nextDouble();
			System.out.print("Numero: ");
			int numero = in.nextInt();
			System.out.print("Quantidade de pessoas: ");
			int quantidadePessoas = in.nextInt();
			System.out.print("Preco de aluguel: ");
			double preco = in.nextDouble();
					
			Recurso recurso = new Quarto(codigo, descricao, categoria, area, numero, quantidadePessoas, preco);
			
			gerenciadorRecursos.cadastrarRecurso(recurso);
		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados do recurso. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao cadastrar o quarto. Verifique se os dados foram inseridos corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void removerRecurso() {
		try {
			System.out.println("---------- Remover Quarto ----------");
		
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			
			Recurso recurso = new Quarto(codigo,"",0);
		
			gerenciadorRecursos.removerRecurso(recurso);
		} catch (DataException e) {
			System.out.println("Erro ao remover registro do recurso. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao remover o quarto. Verifique se o codigo foi inserido corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void listarRecursos() {
		try {
			List<Recurso> recursos = gerenciadorRecursos.listarRecursos();
		
			System.out.println("---------- Lista de Quartos ----------");
			
			for(Recurso recurso : recursos) {
				Quarto quarto = (Quarto) recurso;
				
				System.out.print("Codigo: " + quarto.getCodigo());
				System.out.print(" - Descricao: " + quarto.getDescricao());
				System.out.print(" - Categoria: " + quarto.getCategoria());
				System.out.print(" - Area: " + quarto.getArea());
				System.out.print(" - Numero: " + quarto.getNumero());
				System.out.print(" - Quantidade de pessoas: " + quarto.getQuantidadePessoas());
				System.out.println(" - Preco de aluguel: " + quarto.getPreco());
				System.out.println("\tLivre: " + (quarto.isDisponivel() ? "Sim" : "Nao"));
			}
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos recursos. Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar os registros de quartos. Erro: " + e.getMessage());
		}
	}

}
