package instanciahotel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dominio.Cliente;
import excecao.ClienteInvalidoException;

public class ClienteHotel extends Cliente{
	
	private static final int IDADE_MINIMA = 18; //Idade minima de 18 anos para alugar
	
	private /*@ nullable @*/ String cpf;
	private /*@ nullable @*/ String rg;
	private /*@ nullable @*/ String endereco;
	private /*@ nullable @*/ Date dataNascimento;

	public ClienteHotel(Long codigo, String nome) {
		super(codigo, nome);
	}
	
	public ClienteHotel(Long codigo, String nome, String cpf, String rg, String endereco, Date dataNascimento) {
		super(codigo, nome);
		
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
	}
	
	public /*@ pure @*/ String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public /*@ pure @*/ String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public /*@ pure @*/ String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public /*@ pure @*/ Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		Calendar dataNascimento = new GregorianCalendar();
		dataNascimento.setTime(this.dataNascimento);

		// Cria um objeto calendar com a data atual
		Calendar dataAtual = Calendar.getInstance();

		// Obtem a idade baseado no ano
		int idade = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

		dataNascimento.add(Calendar.YEAR, idade);

		if (dataAtual.before(dataNascimento)) {
			idade--;
		}
		
		return idade;
	}
	
	public boolean valido(){
		boolean valido = true;
		
		if(this.getNome().trim().isEmpty()) {
			valido = false;
		}
		if(this.getCpf().trim().isEmpty()) {
			valido = false;
		}
		if(this.getEndereco().trim().isEmpty()) {
			valido = false;
		}
		if(this.getDataNascimento() == null) {
			valido = false;
		}
		if(this.getIdade() < IDADE_MINIMA) {
			valido = false;
		}
		
		return valido;
	}

	public ClienteInvalidoException toClienteInvalidoException(){
		ClienteInvalidoException exception = new ClienteInvalidoException("Cliente Inválido.");
		if(this.getNome().trim().isEmpty()) {
			exception = new ClienteInvalidoException("Nome do cliente vazio");
		}
		else if(this.getCpf().trim().isEmpty()) {
			exception =  new ClienteInvalidoException("CPF vazio");
		}
		else if(this.getEndereco().trim().isEmpty()) {
			exception =  new ClienteInvalidoException("Endereco vazio");
		}
		else if(this.getDataNascimento() == null) {
			exception =  new ClienteInvalidoException("Data de nascimento vazia");
		}
		else if(this.getIdade() < IDADE_MINIMA) {
			exception =  new ClienteInvalidoException("Cliente nao tem a idade minima necessaria (" + IDADE_MINIMA + " anos)");
		}
		return exception;
	}
	
}
