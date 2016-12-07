package instancialocadoraveiculos;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dominio.Cliente;
import excecao.ClienteInvalidoException;

public class ClienteLocadoraVeiculos extends Cliente{
	
	private static final int IDADE_MINIMA = 21; //Idade mínima de 21 anos para alugar
	
	private String cpf;
	private String rg;
	private String carteiraMotorista;
	private Date dataNascimento;

	public ClienteLocadoraVeiculos(Long codigo, String nome) {
		super(codigo, nome);
	}
	
	public ClienteLocadoraVeiculos(Long codigo, String nome, String cpf, String rg, String carteiraMotorista, Date dataNascimento) {
		super(codigo, nome);
		
		this.cpf = cpf;
		this.rg = rg;
		this.carteiraMotorista = carteiraMotorista;
		this.dataNascimento = dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCarteiraMotorista() {
		return carteiraMotorista;
	}

	public void setCarteiraMotorista(String carteiraMotorista) {
		this.carteiraMotorista = carteiraMotorista;
	}
	
	public Date getDataNascimento() {
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

		// Obtém a idade baseado no ano
		int idade = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

		dataNascimento.add(Calendar.YEAR, idade);

		if (dataAtual.before(dataNascimento)) {
			idade--;
		}
		
		return idade;
	}

	public boolean valido() {
		boolean valido = true;
		
		if(this.getNome().trim().isEmpty()) {
			valido = false;
		}
		if(this.getCpf().trim().isEmpty()) {
			valido = false;
		}
		if(this.getCarteiraMotorista().trim().isEmpty()) {
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
	
	@Override
	public ClienteInvalidoException toClienteInvalidoException() {
		if(this.getNome().trim().isEmpty()) {
			return new ClienteInvalidoException("Nome do cliente vazio");
		}
		if(this.getCpf().trim().isEmpty()) {
			return new ClienteInvalidoException("CPF vazio");
		}
		if(this.getCarteiraMotorista().trim().isEmpty()) {
			return new ClienteInvalidoException("Numero de carteira de motorista nao fornecido");
		}
		if(this.getDataNascimento() == null) {
			return new ClienteInvalidoException("Data de nascimento vazia");
		}
		if(this.getIdade() < IDADE_MINIMA) {
			return new ClienteInvalidoException("Cliente não tem a idade minima necessaria (" + IDADE_MINIMA + " anos)");
		}
		
		return new ClienteInvalidoException("Cliente Invalido");
	}
	
}
