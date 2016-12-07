package instancialocadoraveiculos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dominio.ComprovanteEmprestimo;
import dominio.Recurso;

public class ComprovanteEmprestimoLocadoraVeiculos extends ComprovanteEmprestimo {
    
	public ComprovanteEmprestimoLocadoraVeiculos(String empresa, String locador, Long codigo, Date dataEmprestimo, Date dataDevolucao, List<Recurso> recursos) {
		super(empresa, locador, codigo, dataEmprestimo, dataDevolucao, recursos);
	}

	@Override
	public void imprimir() {
		System.out.println("------- " + this.getEmpresa() + " -------");
		
		System.out.println("----- Comprovante de Emprestimo -----");
		System.out.println("Codigo do aluguel: " + this.getCodigo());
		System.out.println("Nome do locador: " + this.getLocador());
		System.out.println("Data de emprestimo: " + new SimpleDateFormat("dd/MM/yyyy").format(this.getDataEmprestimo()));
		System.out.println("Data da devolucao definida: " + new SimpleDateFormat("dd/MM/yyyy").format(this.getDataDevolucao()));
		
		System.out.println("Lista de Veiculos Alugados: ");
		for (Recurso recurso : getRecursos()) {
			Carro carro = (Carro) recurso;
			
			System.out.print("\tCodigo: " + carro.getCodigo());
			System.out.print(" - Descricao: " + carro.getDescricao());
			System.out.print(" - Placa: " + carro.getPlaca());
			System.out.print(" - Modelo: " + carro.getModelo());
			System.out.print(" - Fabricante: " + carro.getFabricante());
			System.out.print(" - Cor: " + carro.getCor());
			System.out.print(" - Quilometragem inicial: " + carro.getQuilometragemInicial() + "km");
			System.out.print(" - Preco de aluguel: " + carro.getPreco());
			System.out.println();
		}
	}
}