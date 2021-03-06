package instanciahotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import visao.UIGerenciamentoClientes;
import controle.GerenciadorClientes;
import dominio.Cliente;
import excecao.ClienteInvalidoException;
import excecao.DataException;

public class UIGerenciamentoClientesHotel implements UIGerenciamentoClientes {

	private Scanner in = new Scanner(System.in);
	private GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
	
	@Override
	public void cadastrarCliente() {
		
		try {
			System.out.println("---------- Cadastrar Cliente ----------");
			
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
			System.out.print("Nome: ");
			String nome = in.nextLine();
			System.out.print("CPF: ");
			String cpf = in.nextLine();
			System.out.print("RG: ");
			String rg = in.nextLine();
			System.out.print("Endereco: ");
			String endereco = in.nextLine();
			System.out.print("Data de Nascimento (dd/mm/aaaa): ");
			String strDataNascimento = in.nextLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dataNascimento;
		
			dataNascimento = dateFormat.parse(strDataNascimento);
			
			Cliente cliente = new ClienteHotel(codigo,nome,cpf,rg,endereco,dataNascimento);
			gerenciadorClientes.cadastrarCliente(cliente);
		
		} catch (DataException e) {
			System.out.println("Erro ao armazenar dados do cliente. Erro: " + e.getMessage());
		} catch (ClienteInvalidoException e) {
			System.out.println("Cliente invalido inserido. Erro: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Erro ao cadastrar data de nascimento. Verifique o formato inserido.");
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo é válido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao cadastrar o cliente. Verifique se os dados foram inseridos corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void removerCliente() {
		
		try {
			System.out.println("---------- Remover Cliente ----------");
			
			System.out.print("Codigo: ");
			Long codigo = in.nextLong();
			in.nextLine();
	
			Cliente cliente = new ClienteHotel(codigo,"");
			gerenciadorClientes.removerCliente(cliente);
		
		} catch (DataException e) {
			System.out.println("Erro ao remover registro do cliente. Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Verifique se o valor inserido para o campo e valido.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao remover o cliente. Verifique se o codigo foi inserido corretamente. Erro: " + e.getMessage());
		}
	}

	@Override
	public void listarClientes() {
		
		try {
			List<Cliente> clientes = gerenciadorClientes.listarClientes();
		
			System.out.println("---------- Lista de Clientes ----------");
			
			for(Cliente cliente : clientes) {
				ClienteHotel clienteLocadorQuarto = (ClienteHotel) cliente;
				
				System.out.print("Codigo: " + clienteLocadorQuarto.getCodigo());
				System.out.print(" - Nome: " + clienteLocadorQuarto.getNome());
				System.out.print(" - CPF: " + clienteLocadorQuarto.getCpf());
				System.out.print(" - RG: " + clienteLocadorQuarto.getRg());
				System.out.print(" - Endereco: " + clienteLocadorQuarto.getEndereco());
				System.out.print(" - Data de Nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(clienteLocadorQuarto.getDataNascimento()));
				System.out.print(" - Idade: " + clienteLocadorQuarto.getIdade());
				System.out.println();
			}
			
		} catch (DataException e) {
			System.out.println("Erro ao recuperar registros dos clientes. Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar os registros. Erro: " + e.getMessage());
		}
	}

}
