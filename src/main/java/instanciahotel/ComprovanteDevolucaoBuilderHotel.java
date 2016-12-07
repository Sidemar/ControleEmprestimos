package instanciahotel;

import java.util.Date;
import java.util.List;

import dominio.ComprovanteDevolucao;
import dominio.ComprovanteDevolucaoBuilder;
import dominio.Recurso;

public class ComprovanteDevolucaoBuilderHotel implements ComprovanteDevolucaoBuilder {

	private /*@ nullable @*/ String locador;
	private /*@ nullable @*/ Long codigo;
	private /*@ nullable @*/ Date dataEmprestimo;
	private /*@ nullable @*/ Date dataDevolucao;
    private /*@ nullable @*/ List<Recurso> recursos;
    private double valor;

	@Override
	public void buildLocador(String nomeLocador) {
		this.locador = nomeLocador;
	}
	
	@Override
	public void buildCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public void buildDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	@Override
	public void buildDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	@Override
	public void buildRecursos(List<Recurso> recursos) {
		this.recursos = recursos;		
	}
	
	@Override
	public void buildValor(double valor) {
		this.valor = valor;
	}

	@Override
	public ComprovanteDevolucao getComprovante() {
		return new ComprovanteDevolucaoHotel("H Hotel", locador, codigo, dataEmprestimo, dataDevolucao, recursos, valor);
	}
}
