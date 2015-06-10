package visao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controle.GerenciadorClientes;
import dominio.Aluno;
import dominio.Cliente;
import dominio.ClienteLocador;
import dominio.Professor;
import excecao.ClienteInvalidoException;
import excecao.DataException;

public class UIGerenciamentoClientesConsoleLivro implements UIGerenciamentoClientes {

	private Scanner in = new Scanner(System.in);
	private GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
	
	@Override
	public void cadastrarCliente() {
		
		try {
			
			System.out.println("---------- Cadastrar Aluno e Professor----------");
			System.out.println("1 - cadastrar Aluno");
			System.out.println("2 - cadastrar Professor");
			System.out.print("Opcao desejada: ");
			int opcao = in.nextInt();
			
			Long codigo;
			String nome;
			String cpf;
			String rg;
			
			switch (opcao) {
				case 1:
					System.out.println("---------- Cadastrar Aluno----------");
					System.out.print("Codigo: ");
					codigo = in.nextLong();
					in.nextLine();
					System.out.print("Nome: ");
					nome = in.nextLine();
					System.out.print("CPF: ");
					cpf = in.nextLine();
					System.out.print("RG: ");
					rg = in.nextLine();
					System.out.print("Matr�cula: ");
					Long matricula = in.nextLong();
					System.out.print("Curso: ");
					String curso = in.nextLine();
				
					Aluno aluno = new Aluno(codigo, nome, cpf, rg, matricula, curso);
					gerenciadorClientes.cadastrarCliente(aluno);
					break;
				default:
					System.out.println("---------- Cadastrar Professor----------");
					System.out.print("Codigo: ");
					codigo = in.nextLong();
					in.nextLine();
					System.out.print("Nome: ");
					nome = in.nextLine();
					System.out.print("CPF: ");
					cpf = in.nextLine();
					System.out.print("RG: ");
					rg = in.nextLine();
					System.out.print("Titula��o: ");
					String titulacao = in.nextLine();
					System.out.print("Data de admiss�o (dd/mm/aaaa): ");
					String strDataAdmissao = in.nextLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date dataAdmissao;
				
					dataAdmissao = dateFormat.parse(strDataAdmissao);
				
					Cliente cliente = new Professor(codigo, nome, cpf, rg, titulacao, dataAdmissao);
					gerenciadorClientes.cadastrarCliente(cliente);
			}

		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados do aluno. Erro: " + e.getMessage());
		} catch (ClienteInvalidoException e) {
			System.out.println("Aluno invalido inserido. Erro: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Erro ao cadastrar data de admiss�o. Verifique o formato inserido.");
		}
	}

	@Override
	public void removerCliente() {
		
		try {
			System.out.println("---------- Remover Aluno ou Professor ----------");
			System.out.print("1 - remover aluno");
			System.out.print("2 - remover professor");
			System.out.print("Opcao desejada: ");
			int opcao = in.nextInt();
			
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			
			Cliente cliente;
			switch (opcao) {
				case 1:
					cliente = new Aluno(codigo,"");
					break;
				default:
					cliente = new Professor(codigo,"");
					break;
			}
			gerenciadorClientes.removerCliente(cliente);
	
		} catch (DataException e) {
			System.out.println("Erro ao remover registro do cliente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void listarClientes() {
		
		try {
			List<Cliente> clientes = gerenciadorClientes.listarClientes();
		
			System.out.println("---------- Lista de Alunos e Professores ----------");
			
			for(Cliente cliente : clientes) {
				if(cliente instanceof Aluno) {
					Aluno aluno = (Aluno) cliente;
					System.out.print("Codigo: " + aluno.getCodigo());
					System.out.print(" - Nome: " + aluno.getNome());
					System.out.print(" - CPF: " + aluno.getCpf());
					System.out.print(" - RG: " + aluno.getRg());
					System.out.print(" - Matr�cula: " + aluno.getMatricula());
					System.out.print(" - Curso: " + aluno.getCurso());
				} else {
					Professor professor = (Professor) cliente;
					System.out.print("Codigo: " + professor.getCodigo());
					System.out.print(" - Nome: " + professor.getNome());
					System.out.print(" - CPF: " + professor.getCpf());
					System.out.print(" - RG: " + professor.getRg());
					System.out.print(" - Titula��o: " + professor.getTitulacao());
					System.out.print(" - Data de Admiss�o: " + new SimpleDateFormat("dd/MM/yyyy").format(professor.getDataAdmissao()));
				}
				
				System.out.println();
			}
			
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos clientes. Erro: " + e.getMessage());
		}
	}

}
