package instanciahotel;

import dominio.Recurso;
import excecao.RecursoInvalidoException;

public class Quarto extends Recurso {
	
	private double area;
	private int numero;
	private int quantidadePessoas;
	private double preco; 				// Referente ao valor do aluguel
	
	public Quarto(Long codigo, String descricao, int categoria) {
		super(codigo, descricao, categoria);
	}
	
	public Quarto(Long codigo, String descricao, int categoria, double area, int numero, int quantidadePessoas, double preco) {
		super(codigo, descricao, categoria);
		this.area = area;
		this.numero = numero;
		this.quantidadePessoas = quantidadePessoas;
		this.preco = preco;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(int quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public void alocar() {
		this.setDisponivel(false);
	}
	
	@Override
	public void desalocar() {
		this.setDisponivel(true);
	}

	@Override
	public boolean validar() throws RecursoInvalidoException {
		if(!this.isDisponivel())
			throw new RecursoInvalidoException("Recurso invalido para emprestimo. O quarto de codigo " + getCodigo() + " ja esta alocado.");
			
		return true;
	}
	
}
