package instancialocadoraveiculos;

import java.text.SimpleDateFormat;

import dominio.Emprestimo;
import dominio.FabricaNotificacao;
import dominio.Notificacao;
import dominio.NotificacaoCelular;
import dominio.Recurso;

public class FabricaNotificacaoLocadoraVeiculos implements FabricaNotificacao {

	@Override
	public Notificacao getNotificacaoPrazoExpirado(Emprestimo emprestimo) {
		String mensagem = "---------- Locadora LoCar ----------\n";
		mensagem += "-Notificacao de Emprestimo Expirado-\n";
		mensagem += "O prazo do seu emprestimo foi expirado. Compareca a loja para devolucao ou entre em contato para redefinir o prazo.\n";
		mensagem += "Data da Locacao: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()) + "\n";
		mensagem += "Data para Devolucao: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataDevolucao()) + "\n";
		mensagem += "Veiculo(s):\n";
		
		for(Recurso recurso : emprestimo.getRecursos()) {
			Carro carro = (Carro) recurso;
			
			mensagem += "\tCodigo: " + carro.getCodigo();
			mensagem += " - Descricao: " + carro.getDescricao();
			mensagem += " - Placa: " + carro.getPlaca();
			mensagem += " - Modelo: " + carro.getModelo();
			mensagem += " - Fabricante: " + carro.getFabricante();
			mensagem += " - Cor: " + carro.getCor();
			mensagem += " - Preco de aluguel: " + carro.getPreco() + "\n";
		}
		
		return new NotificacaoCelular(mensagem);
	}

	@Override
	public Notificacao getNotificacaoPrazoProximo(Emprestimo emprestimo) {
		String mensagem = "---------- Locadora LoCar ----------\n";
		mensagem += "-Notificacao de Emprestimo Proximo de Expirar-\n";
		mensagem += "O prazo do seu emprestimo esta expirando. Caso deseje renovar o prazo do emprestimo, entre em contato ou compareca a loja mais proxima.\n";
		mensagem += "Data da Locacao: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()) + "\n";
		mensagem += "Data para Devolucao definida: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataDevolucao()) + "\n";
		mensagem += "Veiculo(s):\n";
		
		for(Recurso recurso : emprestimo.getRecursos()) {
			Carro carro = (Carro) recurso;
			
			mensagem += "\tCodigo: " + carro.getCodigo();
			mensagem += " - Descricao: " + carro.getDescricao();
			mensagem += " - Placa: " + carro.getPlaca();
			mensagem += " - Modelo: " + carro.getModelo();
			mensagem += " - Fabricante: " + carro.getFabricante();
			mensagem += " - Cor: " + carro.getCor();
			mensagem += " - Preco de aluguel: " + carro.getPreco() + "\n";
		}
		
		return new NotificacaoCelular(mensagem);
	}

}
