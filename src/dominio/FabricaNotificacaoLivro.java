package dominio;

public class FabricaNotificacaoLivro implements FabricaNotificacao {

	@Override
	public Notificacao getNotificacaoPrazoExpirado(Emprestimo emprestimo) {
		// TODO Auto-generated method stub
		String mensagem = "---------- Biblioteca ZN----------\n";
		mensagem += "-Notifica��o de Empr�stimo Expirado-\n";
		mensagem += "O prazo do seu emprestimo foi expirado. Compare�a � biblioteca para devolu��o ou entre em contato para redefinir o prazo.\n";
		mensagem += "Data do Empr�stimos: ...\n";
		mensagem += "Data para Devolu��o: ...\n";
		mensagem += "Livro: ...\n";
		
		return new NotificacaoCelular(mensagem);
	}

	@Override
	public Notificacao getNotificacaoPrazoProximo(Emprestimo emprestimo) {
		// TODO Auto-generated method stub
		String mensagem = "---------- Biblioteca ----------\n";
		mensagem += "-Notifica��o de Empr�stimo Pr�ximo de Expirar-\n";
		mensagem += "O prazo do seu emprestimo est� expirando. Caso deseje renovar o prazo do empr�stimo, entre em contato ou compare�a � biblioteca.\n";
		mensagem += "Data da Loca��o: ...\n";
		mensagem += "Data para Devolu��o: ...\n";
		mensagem += "Livro: ...\n";
		
		return new NotificacaoCelular(mensagem);
	}

}
