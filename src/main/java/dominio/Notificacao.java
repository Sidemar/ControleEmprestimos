package dominio;

public abstract class Notificacao {

	private /*@ spec_public @*/ String mensagem;
	
	public Notificacao(String mensagem) {
		this.setMensagem(mensagem);
	}
	
	public /*@ pure @*/ String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public abstract void enviar();

}