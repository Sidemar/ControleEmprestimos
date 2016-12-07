package instanciahotel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dominio.ComprovanteEmprestimo;
import dominio.Recurso;

public class ComprovanteEmprestimoHotel extends ComprovanteEmprestimo {
    
	public ComprovanteEmprestimoHotel(String empresa, String locador, Long codigo, Date dataEmprestimo, Date dataDevolucao, List<Recurso> recursos) {
		super(empresa, locador, codigo, dataEmprestimo, dataDevolucao, recursos);
	}

	@Override
	public void imprimir() {
		System.out.println("------- " + this.getEmpresa() + " -------");
		
		System.out.println("----- Comprovante de Checkin -----");
		System.out.println("Codigo do aluguel: " + this.getCodigo());
		System.out.println("Nome do locador: " + this.getLocador());
		System.out.println("Data de checkin: " + new SimpleDateFormat("dd/MM/yyyy").format(this.getDataEmprestimo()));
		System.out.println("Data prevista checkout: " + new SimpleDateFormat("dd/MM/yyyy").format(this.getDataDevolucao()));
		
		System.out.println("Lista de Quartos Alugados: ");
		for (Recurso recurso : getRecursos()) {
			Quarto quarto = (Quarto) recurso;
			
			System.out.print("\tCodigo: " + quarto.getCodigo());
			System.out.print(" - Descricao: " + quarto.getDescricao());
			System.out.print(" - Area: " + quarto.getArea());
			System.out.print(" - Numero: " + quarto.getNumero());
			System.out.print(" - Quantidade de pessoas: " + quarto.getQuantidadePessoas());
			System.out.print(" - Preco de aluguel: R$ " + quarto.getPreco());
			System.out.println();
		}
	}
}