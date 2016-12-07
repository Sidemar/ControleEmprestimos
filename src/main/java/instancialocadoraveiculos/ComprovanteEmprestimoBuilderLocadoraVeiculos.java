package instancialocadoraveiculos;

import java.util.Date;
import java.util.List;

import dominio.ComprovanteEmprestimo;
import dominio.ComprovanteEmprestimoBuilder;
import dominio.Recurso;

public class ComprovanteEmprestimoBuilderLocadoraVeiculos implements ComprovanteEmprestimoBuilder {

	private String locador;
	private Long codigo;
	private Date dataEmprestimo;
	private Date dataDevolucao;
    private List<Recurso> recursos;

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
	public ComprovanteEmprestimo getComprovante() {
		return new ComprovanteEmprestimoLocadoraVeiculos("LoCar Locadora", locador, codigo, dataEmprestimo, dataDevolucao, recursos);
	}
}
