package dominio;

public interface FabricaNotificacao {
	public Notificacao getNotificacaoPrazoExpirado(Emprestimo emprestimo);
	public Notificacao getNotificacaoPrazoProximo(Emprestimo emprestimo);
}
