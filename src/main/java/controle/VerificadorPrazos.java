package controle;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dominio.Emprestimo;
import dominio.FabricaNotificacao;
import dominio.RegraEmprestimo;

public class VerificadorPrazos {
	
	private RegraEmprestimo regraEmprestimo;
	private NotificadorPrazos notificadorPrazos;
	
	public VerificadorPrazos(RegraEmprestimo regraEmprestimo, FabricaNotificacao fabricaNotificacao) {
		this.regraEmprestimo = regraEmprestimo;
		this.notificadorPrazos = new NotificadorPrazos(fabricaNotificacao);
	}
	
	private boolean prazoExpirado(Emprestimo emprestimo) {
		Date dataAtual = Calendar.getInstance().getTime();
		
		long tempoExpirado = (dataAtual.getTime() - emprestimo.getDataDevolucao().getTime());
		
		if(tempoExpirado > 0)			
			return true;
		else
			return false;
	}

	private boolean prazoProximo(Emprestimo emprestimo) {
		Date dataAtual = Calendar.getInstance().getTime();
		long tempoExpirado = dataAtual.getTime() - emprestimo.getDataDevolucao().getTime();
		long diasExpirado = tempoExpirado / (1000 * 60 * 60 * 24);
		
		if(diasExpirado < 0 && Math.abs(diasExpirado) <= this.regraEmprestimo.getDiasNotificacaoPrevia())			
			return true;
		else
			return false;
	}
	
	public boolean verificarEmprestimo(Emprestimo emprestimo) {
		if(prazoExpirado(emprestimo)) {
			notificadorPrazos.notificarPrazoExpirado(emprestimo);
			return false;
		}
		else if(prazoProximo(emprestimo)) {
			notificadorPrazos.notificarPrazoProximo(emprestimo);
			return false;
		}
		
		return true;
	}
	
	public boolean verificarEmprestimos(List<Emprestimo> emprestimos) {
		boolean notificado = false;
		for(Emprestimo emprestimo : emprestimos) {
			if(prazoExpirado(emprestimo)) {
				notificadorPrazos.notificarPrazoExpirado(emprestimo);
				notificado = true;
			}
			else if(prazoProximo(emprestimo)) {
				notificadorPrazos.notificarPrazoProximo(emprestimo);
				notificado = true;
			}
		}
		return notificado;
	}
}
