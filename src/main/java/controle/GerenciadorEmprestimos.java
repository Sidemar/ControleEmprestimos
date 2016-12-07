package controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.DaoEmprestimo;
import dao.DaoEmprestimoMemoria;
import dao.DaoHistorico;
import dao.DaoHistoricoMemoria;
import dao.DaoRecurso;
import dao.DaoRecursoMemoria;
import dominio.Cliente;
import dominio.ComprovanteDevolucao;
import dominio.ComprovanteDevolucaoBuilder;
import dominio.ComprovanteEmprestimo;
import dominio.ComprovanteEmprestimoBuilder;
import dominio.Emprestimo;
import dominio.FabricaNotificacao;
import dominio.GeradorComprovante;
import dominio.Recurso;
import dominio.RegraEmprestimo;
import dominio.Usuario;
import excecao.ClienteInvalidoException;
import excecao.DataException;
import excecao.EmprestimoInvalidoException;
import excecao.RecursoInvalidoException;

public class GerenciadorEmprestimos {
	
	//@ public invariant daoEmprestimo != null;
	private /*@ spec_public @*/ DaoEmprestimo daoEmprestimo;
	
	//@ public invariant daoHistorico != null;
	private /*@ spec_public @*/ DaoHistorico daoHistorico;
	
	//@ public invariant daoRecurso != null;
	private /*@ spec_public @*/ DaoRecurso daoRecurso;
	
	private /*@ spec_public @*/ RegraEmprestimo regraEmprestimo;
	private /*@ spec_public @*/ GeradorComprovante geradorComprovante;
	private /*@ spec_public @*/ VerificadorPrazos verificadorPrazos;
		
	public GerenciadorEmprestimos(RegraEmprestimo regraEmprestimo, ComprovanteEmprestimoBuilder comprovanteEmprestimoBuilder, ComprovanteDevolucaoBuilder comprovanteDevolucaoBuilder, FabricaNotificacao fabricaNotificacao) {
		this.daoEmprestimo = DaoEmprestimoMemoria.getInstance();
		this.daoHistorico = DaoHistoricoMemoria.getInstance(); 
		this.daoRecurso = DaoRecursoMemoria.getInstance();
		
		this.regraEmprestimo = regraEmprestimo;
		this.geradorComprovante = new GeradorComprovante(comprovanteEmprestimoBuilder, comprovanteDevolucaoBuilder);
		this.verificadorPrazos = new VerificadorPrazos(regraEmprestimo, fabricaNotificacao);
	}
	
	/*@
	@ public normal_behavior
	@ 	requires cliente != null;
	@	requires cliente.valido();
	@	requires (\forall int i; 
	@				0 <= i && i < recursos.size();
	@				 recursos.get(i) != null);	
	@ 	assignable \nothing;
	@	ensures \result != null;
	@	ensures (\forall int i; 
	@				0 <= i && i < recursos.size();
	@				((List<Recursos>) \result.getRecursos()) .contains( ((Recurso) recursos.get(i)) )   );	
	@	ensures	regraEmprestimo.validarDataDevolucao(\result.getDataEmprestimo(), \result.getDataDevolucao());
	@*/
	public ComprovanteEmprestimo realizarEmprestimo(Usuario usuario, Cliente cliente, List<Recurso> recursos) throws DataException, EmprestimoInvalidoException, ClienteInvalidoException, RecursoInvalidoException {
		return realizarEmprestimo(usuario, cliente, recursos, null);
	}
	
	/*@
	@ public normal_behavior
	@ 	requires cliente != null;
	@	requires cliente.valido();
	@	requires (\forall int i; 
	@				0 <= i && i < recursos.size();
	@				 recursos.get(i) != null);	
	@ 	assignable \nothing;
	@	ensures \result != null;
	@	ensures (\forall int i; 
	@				0 <= i && i < recursos.size();
	@				((List<Recursos>) \result.getRecursos()) .contains( ((Recurso) recursos.get(i)) )   );	
	@	ensures	regraEmprestimo.validarDataDevolucao(\result.getDataEmprestimo(), \result.getDataDevolucao());
	@*/
	public ComprovanteEmprestimo realizarEmprestimo(Usuario usuario, Cliente cliente, List<Recurso> recursos, Date dataDevolucao) throws ClienteInvalidoException, EmprestimoInvalidoException, DataException, RecursoInvalidoException {
		//Validacao do status do cliente para emprestimos
		
		if(!cliente.valido()){
			throw new ClienteInvalidoException(cliente.toClienteInvalidoException());
		}
		
		//Validacao do recurso para emprestimo
		for(Recurso recurso : recursos) {
			recurso.validar();
		}
		
		//Realiza o emprestimo
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setUsuario(usuario);
		emprestimo.setCliente(cliente);
		
		Date dataAtual = Calendar.getInstance().getTime();
		emprestimo.setDataEmprestimo(dataAtual);
		
		if(dataDevolucao != null){
			regraEmprestimo.validarDataDevolucao(dataAtual, dataDevolucao);
			emprestimo.setDataDevolucao(dataDevolucao);
		} else {
			emprestimo.setDataDevolucao(regraEmprestimo.calcularDataDevolucao(emprestimo));
		}
		
		for(Recurso recurso : recursos) {
			emprestimo.adicionarRecurso(recurso);
			recurso.alocar();
		}
		
		daoEmprestimo.add(emprestimo);
	
		ComprovanteEmprestimo comprovanteEmprestimo = geradorComprovante.gerarComprovanteEmprestimo(emprestimo);
		return comprovanteEmprestimo;
	}
	
	/*@
	 @ requires emprestimo != null;
	 @ assignable \nothing;
	 @ ensures this.daoHistorico.exists((long) \result.getCodigo());
 	 @ ensures !this.daoEmprestimo.exists((long) \result.getCodigo());
 	 @ ensures \result.getValor() == regraEmprestimo.calcularValorFinal(emprestimo, taxaExtra);
	 @*/
	public ComprovanteDevolucao realizarDevolucao(Emprestimo emprestimo, double taxaExtra) throws DataException {
		double valorFinal = regraEmprestimo.calcularValorFinal(emprestimo, taxaExtra);
		
		//TODO Implementar a realizacao do pagamento

		for(Recurso recurso : emprestimo.getRecursos()) {
			recurso.desalocar();
		}
		
		daoHistorico.add(emprestimo); // Antes de remover da memoria adiciona no historico
		daoEmprestimo.remove(emprestimo);

		ComprovanteDevolucao comprovanteDevolucao = geradorComprovante.gerarComprovanteDevolucao(emprestimo, valorFinal);
		return comprovanteDevolucao;
	}
			
	/*@
	@ requires codigoCliente > 0L;
	@ assignable \nothing;
	@ ensures \result != null;
	@*/
	public List<Recurso> buscarSugestoes(long codigoCliente) throws DataException {				
		List<Recurso> recursosSugeridos;
		
		Integer idCategoria = daoHistorico.findCategoriaAltaByCliente(codigoCliente);
		
		if(idCategoria != null){
			recursosSugeridos = daoRecurso.getPorCategoria(idCategoria);
		} else {
			recursosSugeridos = new ArrayList<>();
		}
		
		return recursosSugeridos;
	}
	
	/*@
	 @ requires codigo > 0L;
	 @ assignable \nothing;
	 @ ensures daoEmprestimo.exists(codigo) ==> \result != null;
	 @*/
	public /*@ pure @*/ Emprestimo getEmprestimo(long codigo) throws DataException {
		return (Emprestimo) this.daoEmprestimo.get(codigo);
	}
	
	@SuppressWarnings("unchecked")
	public /*@ pure @*/ boolean verificarPrazos() throws DataException {
		List<Emprestimo> emprestimos = (List<Emprestimo>) (List<?>) daoEmprestimo.list();
		return this.verificadorPrazos.verificarEmprestimos(emprestimos);
	}
	
	public boolean verificarStatusCliente(Cliente cliente) {
		//TODO Implementar verificacao de status do cliente
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public /*@ pure @*/ List<Emprestimo> listarEmprestimos() throws DataException {
		return (List<Emprestimo>) (List<?>) this.daoEmprestimo.list();
	}
	
}
