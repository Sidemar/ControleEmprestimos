package instanciahotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import visao.UIGerenciamentoEmprestimos;
import controle.GerenciadorClientes;
import controle.GerenciadorEmprestimos;
import controle.GerenciadorRecursos;
import controle.GerenciadorUsuarios;
import dominio.Cliente;
import dominio.ComprovanteDevolucao;
import dominio.ComprovanteEmprestimo;
import dominio.Emprestimo;
import dominio.Recurso;
import dominio.Usuario;
import excecao.ClienteInvalidoException;
import excecao.DataException;
import excecao.EmprestimoInvalidoException;
import excecao.RecursoInvalidoException;

public class UIGerenciamentoEmprestimosHotel implements UIGerenciamentoEmprestimos {

	private Scanner in = new Scanner(System.in);
	
	private GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
	private GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
	private GerenciadorRecursos gerenciadorRecursos = new GerenciadorRecursos();
	private GerenciadorEmprestimos gerenciadorEmprestimos = new GerenciadorEmprestimos(new RegraHotel(), new ComprovanteEmprestimoBuilderHotel(), new ComprovanteDevolucaoBuilderHotel(), new FabricaNotificacaoHotel());
	
	@Override
	public void realizarEmprestimo() {
		
		try {
			System.out.println("---------- Realizar Emprestimo ----------");
			System.out.print("Login do usuario: ");
			String login = in.nextLine();
			Usuario usuario = gerenciadorUsuarios.getUsuario(login);
		
			System.out.print("Codigo do cliente: ");
			Long codigoCliente = in.nextLong();
			in.nextLine();
			Cliente cliente = gerenciadorClientes.getCliente(codigoCliente);
			
			String continuar = "N";
			List<Recurso> recursos = new ArrayList<Recurso>(); 
			
			do {
				System.out.print("Codigo do quarto: ");
				Long codigoRecurso = in.nextLong();
				in.nextLine();
				
				Quarto recurso = (Quarto) gerenciadorRecursos.getRecurso(codigoRecurso);
				if(!recursos.contains(recurso)) {
					recursos.add(recurso);
					
					System.out.print("Quarto Adicionado => ");
					System.out.print("Descricao: " + recurso.getDescricao());
					System.out.print("Categoria: " + recurso.getCategoria());
					System.out.print(" - Area: " + recurso.getArea());
					System.out.print(" - Numero: " + recurso.getNumero());
					System.out.print(" - Quantidade de pessoas: " + recurso.getQuantidadePessoas());
					System.out.print(" - Preco (de aluguel): " + recurso.getPreco());
					System.out.println();
				}
				
				System.out.print("Deseja inserir outro item (S/N)? ");
				continuar = in.nextLine();
			} while(continuar.equalsIgnoreCase("S"));
			
			System.out.print("Data de Devolucao Prevista (dd/mm/aaaa): ");
			String strDataDevolucao = in.nextLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			Date dataDevolucao = dateFormat.parse(strDataDevolucao);
			ComprovanteEmprestimo comprovanteEmprestimo = gerenciadorEmprestimos.realizarEmprestimo(usuario, cliente, recursos, dataDevolucao);
			System.out.println("Emprestimo realizado!");
			System.out.println();
			comprovanteEmprestimo.imprimir();
			
		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados do emprestimo. Erro: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Erro ao cadastrar data de devolucao. Verifique o formato inserido.");
		} catch (ClienteInvalidoException e) {
			System.out.println("Cliente invalido selecionado. Erro: " + e.getMessage());
		} catch (EmprestimoInvalidoException e) {
			System.out.println("Emprestimo invalido. Erro: " + e.getMessage());
		} catch (RecursoInvalidoException e) {
			System.out.println("Recurso invalido selecionado. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao realizar o emprestimo. Verifique se dados foram inseridos corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void realizarDevolucao() {
		
		try {
			System.out.println("---------- Realizar Devolucao ----------");
			
			System.out.print("Codigo do emprestimo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			
			Emprestimo emprestimo = gerenciadorEmprestimos.getEmprestimo(codigo);
		
			System.out.print("Taxa extra: R$ ");
			double taxaExtra = in.nextDouble();
			
			ComprovanteDevolucao comprovanteDevolucao = gerenciadorEmprestimos.realizarDevolucao(emprestimo,taxaExtra);
			System.out.println("Emprestimo finalizado!");
			System.out.println();
			comprovanteDevolucao.imprimir();
		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados da devolucao. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao realizar a devolucao. Verifique se dados foram inseridos corretamente.");
		}
	}

	@Override
	public void listarEmprestimos() {
		try {
			List<Emprestimo> emprestimos = gerenciadorEmprestimos.listarEmprestimos();
		
			System.out.println("---------- Lista de Emprestimos ----------");
			
			if(emprestimos.size() > 0) {
				for(Emprestimo emprestimo : emprestimos) {
					System.out.print("Codigo: " + emprestimo.getCodigo());
					System.out.print(" - Usuario: " + emprestimo.getUsuario().getLogin());
					System.out.print(" - Cliente: " + emprestimo.getCliente().getNome());
					System.out.print(" - Data emprestimo: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()));
					System.out.println(" - Data devolucao: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataDevolucao()));
					System.out.println("Recursos: ");
					
					for(Recurso recurso : emprestimo.getRecursos()) {
						Quarto quarto = (Quarto) recurso;
						
						System.out.print("Quarto Adicionado => ");
						System.out.print("Descricao: " + quarto.getDescricao());
						System.out.print("Categoria: " + quarto.getCategoria());
						System.out.print(" - Area: " + quarto.getArea());
						System.out.print(" - Numero: " + quarto.getNumero());
						System.out.print(" - Quantidade de pessoas: " + quarto.getQuantidadePessoas());
						System.out.print(" - Preco (de aluguel): " + quarto.getPreco());
						System.out.println();
					}
					System.out.println();
				}
			}
			else {
				System.out.println("Nao existem alugueis em aberto.");
			}
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos emprestimos. Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar os registros de emprestimos. Erro: " + e.getMessage());
		}
	}

	@Override
	public void verificarPrazos() {
		try {
			System.out.println("---------- Verificar Prazos de Emprestimos ----------");
			System.out.println("Verificando prazos. Aguarde... ");
		
			if(this.gerenciadorEmprestimos.verificarPrazos()) { //Existiram emprestimos notificados
				System.out.println("Os usuarios com emprestimos pendentes foram devidamente notificados.");
			}
			else {
				System.out.println("Nao houveram usuarios notificados.");
			}
			
		} catch (DataException e) {
			System.out.println("Erro ao acessar registros dos emprestimos. Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao verificar os prazos. Erro: " + e.getMessage());
		}
	}

	@Override
	public void sugerirEmprestimos() {
		try {
			System.out.println("---------- Sugestao de Quartos ----------");
			
			System.out.print("Codigo do Cliente: ");
			Long codigoCliente = in.nextLong();
			in.nextLine();
			
			List<Recurso> recursosSugeridos = gerenciadorEmprestimos.buscarSugestoes(codigoCliente);
			
			System.out.println("Sugestoes de quartos:");
			
			if(recursosSugeridos != null && !recursosSugeridos.isEmpty()) {	
				for(Recurso recurso : recursosSugeridos) {
					Quarto quarto = (Quarto) recurso;
					
					System.out.print("Codigo: "+ quarto.getCodigo());
					System.out.print(" - Descricao: " + quarto.getDescricao());
					System.out.print(" - Categoria: " + quarto.getCategoria());
					System.out.print(" - Area: " + quarto.getArea());
					System.out.print(" - Numero: " + quarto.getNumero());
					System.out.print(" - Quantidade de pessoas: " + quarto.getQuantidadePessoas());
					System.out.print(" - Preco (de aluguel): " + quarto.getPreco());
					System.out.println();
				}
				System.out.println();
			}
			else {
				System.out.println("Nao existem registros de emprestimos anteriores do cliente inserido para analise.");
			}
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos emprestimos. Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar os registros de emprestimos. Erro: " + e.toString());
			e.printStackTrace();
		}
	}

}
