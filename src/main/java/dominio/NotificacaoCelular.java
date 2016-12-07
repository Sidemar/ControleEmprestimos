package dominio;

public class NotificacaoCelular extends Notificacao{

	public NotificacaoCelular(String mensagem) {
		super(mensagem);
	}
	
	@Override
	public void enviar() {
		System.out.println("Enviando notificacao por celular...");
		System.out.println(getMensagem());
	}

}
