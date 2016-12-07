package dominio;

import java.util.Date;
import java.util.List;

public abstract class Comprovante {
	
	private /*@ spec_public @*/ String empresa;
	private /*@ spec_public @*/ String locador;
	private /*@ spec_public @*/ Long codigo;
	private /*@ spec_public @*/ Date dataEmprestimo;
	private /*@ spec_public @*/ Date dataDevolucao;
	private /*@ spec_public @*/ List<Recurso> recursos;
	
	public Comprovante(String empresa, String locador, Long codigo, Date dataEmprestimo, Date dataDevolucao, List<Recurso> recursos) {
		this.empresa = empresa;
		this.locador = locador;
		this.codigo = codigo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.recursos = recursos;
	}
	
	public /*@ pure @*/ String getEmpresa() {
		return empresa;
	}
	
    public /*@ pure @*/ String getLocador() {
		return locador;
	}
    
    public /*@ pure @*/ Long getCodigo() {
		return codigo;
	}
    
    public /*@ pure @*/ Date getDataEmprestimo() {
		return dataEmprestimo;
	}
    
    public /*@ pure @*/ Date getDataDevolucao() {
		return dataDevolucao;
	}
    
    public /*@ pure @*/ List<Recurso> getRecursos() {
		return recursos;
	}
    
    public /*@ pure @*/ abstract void imprimir();
}