package excecao;

public class ClienteInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	

	public ClienteInvalidoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ClienteInvalidoException(String message) {
		super(message);
	}
	
}
