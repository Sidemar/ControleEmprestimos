package dominio;

public class NotificacaoEmail extends Notificacao{

	public NotificacaoEmail(String mensagem) {
		super(mensagem);
	}
	
	@Override
	public void enviar() {
		System.out.println("Enviando notificacao por email...");
		System.out.println(getMensagem());
	}

}
