package instanciahotel;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import dominio.Emprestimo;
import dominio.Recurso;
import dominio.RegraEmprestimo;
import excecao.EmprestimoInvalidoException;

public class RegraHotel implements RegraEmprestimo{

	private static final int DIAS_PARA_NOTIFICACAO = 2;
	
	@Override
	public int getDiasNotificacaoPrevia() {
		return DIAS_PARA_NOTIFICACAO;
	}
	
	@Override
	public double calcularValorFinal(Emprestimo emprestimo, double taxaExtra) {
		Date dataAtual = Calendar.getInstance().getTime();
		Long msDiff = dataAtual.getTime() - emprestimo.getDataEmprestimo().getTime();
		Long diasEmprestimo = TimeUnit.DAYS.convert(msDiff, TimeUnit.MILLISECONDS);
		
		double valorFinal = 0;
		
		for(Recurso recurso : emprestimo.getRecursos()) {
			Quarto quarto = (Quarto) recurso;
			valorFinal += (quarto.getPreco() * diasEmprestimo);
		}
		
		if(taxaExtra > 0)
			valorFinal += taxaExtra;
		
		return valorFinal;
	}

	@Override
	public Date calcularDataDevolucao(Emprestimo emprestimo) {
		//Retorna a data minima para emprestimo (1 dia)
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	@Override
	public boolean validarDataDevolucao(Date dataEmprestimo, Date dataDevolucao) throws EmprestimoInvalidoException {
		//Verificar se o periodo de emprestimo eh de pelo menos 1 dia (prazo minimo) 
		Long msDiff = dataDevolucao.getTime() - dataEmprestimo.getTime();
		Long diasDiff = TimeUnit.DAYS.convert(msDiff, TimeUnit.MILLISECONDS);
		
		if(diasDiff < 1)
			throw new EmprestimoInvalidoException("O periodo do emprestimo deve ser de pelo menos uma diaria");

		return false;
	}
}
