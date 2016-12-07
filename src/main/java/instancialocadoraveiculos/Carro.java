package instancialocadoraveiculos;

import dominio.Recurso;
import excecao.RecursoInvalidoException;

public class Carro extends Recurso {
	
	private String placa;
	private String modelo;
	private String fabricante;
	private String cor;
	private int quilometragemInicial; 	// Quilometragem rodada pelo veiculo antes do momento do emprestimo
	private int quilometragemFinal; 	// Quilometragem rodada pelo veiculo antes do momento do emprestimo
	private double preco; 				// Referente ao valor do aluguel
	
	public Carro(Long codigo, String descricao, int categoria) {
		super(codigo, descricao, categoria);
	}
	
	public Carro(Long codigo, String descricao, int categoria, String placa, String modelo, String fabricante, String cor, int quilometragemInicial, double preco) {
		super(codigo, descricao, categoria);
		
		this.placa = placa;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.cor = cor;
		this.quilometragemInicial = quilometragemInicial;
		this.preco = preco;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getQuilometragemInicial() {
		return quilometragemInicial;
	}

	public void setQuilometragemInicial(int quilometragemInicial) {
		this.quilometragemInicial = quilometragemInicial;
	}

	public int getQuilometragemFinal() {
		return quilometragemFinal;
	}

	public void setQuilometragemFinal(int quilometragemFinal) {
		this.quilometragemFinal = quilometragemFinal;
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
			throw new RecursoInvalidoException("Recurso invalido para emprestimo. O carro de codigo " + getCodigo() + " ja esta alocado.");
			
		return true;
	}
	
}
