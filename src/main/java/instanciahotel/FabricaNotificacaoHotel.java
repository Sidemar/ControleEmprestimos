package instanciahotel;

import java.text.SimpleDateFormat;

import dominio.Emprestimo;
import dominio.FabricaNotificacao;
import dominio.Notificacao;
import dominio.NotificacaoCelular;
import dominio.Recurso;

public class FabricaNotificacaoHotel implements FabricaNotificacao {

	@Override
	public Notificacao getNotificacaoPrazoExpirado(Emprestimo emprestimo) {
		String mensagem = "---------- Hotel H ----------\n";
		mensagem += "-Notificacao de Emprestimo Expirado-\n";
		mensagem += "O prazo do seu emprestimo foi expirado. Compareca a recepcao para verificar a possibilidade de renovacao do prazo.\n";
		mensagem += "Data de Checkin: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()) + "\n";
		mensagem += "Data de Checkout definida: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataDevolucao()) + "\n";
		mensagem += "Quarto(s):\n";
		
		for(Recurso recurso : emprestimo.getRecursos()) {
			Quarto quarto = (Quarto) recurso;
			
			mensagem += "Quarto Adicionado => ";
			mensagem += "Descricao: " + quarto.getDescricao();
			mensagem += " - Area: " + quarto.getArea();
			mensagem += " - Numero: " + quarto.getNumero();
			mensagem += " - Quantidade de pessoas: " + quarto.getQuantidadePessoas();
			mensagem += " - Preco (de aluguel): " + quarto.getPreco() + "\n";
		}
		
		return new NotificacaoCelular(mensagem);
	}

	@Override
	public Notificacao getNotificacaoPrazoProximo(Emprestimo emprestimo) {
		String mensagem = "---------- Hotel H ----------\n";
		mensagem += "-Notificacao de Emprestimo Proximo de Expirar-\n";
		mensagem += "O prazo do seu emprestimo esta expirando. Caso deseje renovar o prazo da estadia, compareca a recepcao do hotel para verificar a possibilidade de extensao do prazo.\n";
		mensagem += "Data de Checkin: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()) + "\n";
		mensagem += "Data de Checkout definida: " + new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataDevolucao()) + "\n";
		mensagem += "Quarto(s):\n";
		
		for(Recurso recurso : emprestimo.getRecursos()) {
			Quarto quarto = (Quarto) recurso;
			
			mensagem += "Quarto Adicionado => ";
			mensagem += "Descricao: " + quarto.getDescricao();
			mensagem += " - Area: " + quarto.getArea();
			mensagem += " - Numero: " + quarto.getNumero();
			mensagem += " - Quantidade de pessoas: " + quarto.getQuantidadePessoas();
			mensagem += " - Preco (de aluguel): " + quarto.getPreco() + "\n";
		}
		
		return new NotificacaoCelular(mensagem);
	}

}
