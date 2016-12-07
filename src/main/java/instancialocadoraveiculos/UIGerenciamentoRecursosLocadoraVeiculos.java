package instancialocadoraveiculos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import visao.UIGerenciamentoRecursos;
import controle.GerenciadorRecursos;
import dominio.Recurso;
import excecao.DataException;

public class UIGerenciamentoRecursosLocadoraVeiculos implements UIGerenciamentoRecursos {

	private GerenciadorRecursos gerenciadorRecursos = new GerenciadorRecursos();
	private Scanner in = new Scanner(System.in);
	
	@Override
	public void cadastrarRecurso() {
		try {
			System.out.println("---------- Cadastrar Carro ----------");
			
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			System.out.print("Descricao: ");
			String descricao = in.nextLine();
			System.out.print("Categoria: ");
			int categoria = in.nextInt();
			System.out.print("Placa: ");
			String placa = in.nextLine();
			System.out.print("Modelo: ");
			String modelo = in.nextLine();
			System.out.print("Fabricante: ");
			String fabricante = in.nextLine();
			System.out.print("Cor: ");
			String cor = in.nextLine();
			System.out.print("Quilometragem inicial: ");
			int quilometragemInicial = in.nextInt();
			System.out.print("Preco de aluguel: ");
			double preco = in.nextDouble();
			
			Recurso recurso = new Carro(codigo, descricao, categoria, placa, modelo, fabricante, cor, quilometragemInicial, preco);
			
			gerenciadorRecursos.cadastrarRecurso(recurso);
		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados do recurso. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao cadastrar o veiculo. Verifique se os dados foram inseridos corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void removerRecurso() {
		try {
			System.out.println("---------- Remover Carro ----------");
		
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			
			Recurso recurso = new Carro(codigo,"",0);
		
			gerenciadorRecursos.removerRecurso(recurso);
		} catch (DataException e) {
			System.out.println("Erro ao remover registro do recurso. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao remover o veiculo. Verifique se o codigo foi inserido corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void listarRecursos() {
		try {
			List<Recurso> recursos = gerenciadorRecursos.listarRecursos();
		
			System.out.println("---------- Lista de Carros ----------");
			
			for(Recurso recurso : recursos) {
				Carro carro = (Carro) recurso;
				
				System.out.print("Codigo: " + carro.getCodigo());
				System.out.print(" - Descricao: " + carro.getDescricao());
				System.out.print(" - Categoria: " + carro.getCategoria());
				System.out.print(" - Placa: " + carro.getPlaca());
				System.out.print(" - Modelo: " + carro.getModelo());
				System.out.print(" - Fabricante: " + carro.getFabricante());
				System.out.print(" - Cor: " + carro.getCor());
				System.out.print(" - Quilometragem inicial: " + carro.getQuilometragemInicial() + "km");
				System.out.println(" - Preco de aluguel: " + carro.getPreco());
				System.out.println("\tDisponivel: " + (carro.isDisponivel() ? "Sim" : "Nao"));
			}
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos recursos. Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar os registros de veiculos. Erro: " + e.getMessage());
		}
	}

}
