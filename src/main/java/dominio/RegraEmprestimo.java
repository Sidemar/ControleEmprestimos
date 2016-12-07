package dominio;

import java.util.Date;

import excecao.EmprestimoInvalidoException;

public interface RegraEmprestimo {
	public /*@ pure @*/ int getDiasNotificacaoPrevia();
	public /*@ pure @*/ double calcularValorFinal(Emprestimo emprestimo, double taxaExtra);
	public /*@ pure @*/ Date calcularDataDevolucao(Emprestimo emprestimo);
	public /*@ pure @*/ boolean validarDataDevolucao(Date dataEmprestimo, Date dataDevolucao) throws EmprestimoInvalidoException;
}