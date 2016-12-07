package controle;

import dominio.Emprestimo;
import dominio.FabricaNotificacao;
import dominio.Notificacao;

public class NotificadorPrazos {
	
	private FabricaNotificacao fabricaNotificacao;
	
	public NotificadorPrazos(FabricaNotificacao fabricaNotificacao) {
		this.fabricaNotificacao = fabricaNotificacao;
	}
	
	public void notificarPrazoExpirado(Emprestimo emprestimo) {
		Notificacao notificacao = fabricaNotificacao.getNotificacaoPrazoExpirado(emprestimo);
		notificacao.enviar();
	}
	
	public void notificarPrazoProximo(Emprestimo emprestimo) {
		Notificacao notificacao = fabricaNotificacao.getNotificacaoPrazoProximo(emprestimo);
		notificacao.enviar();
	}
}
