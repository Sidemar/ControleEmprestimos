package dominio;

public class GeradorComprovante {

    private ComprovanteDevolucaoBuilder comprovanteDevolucaoBuilder;
    private ComprovanteEmprestimoBuilder comprovanteEmprestimoBuilder;

    public GeradorComprovante(ComprovanteEmprestimoBuilder comprovanteEmprestimoBuilder, ComprovanteDevolucaoBuilder comprovanteDevolucaoBuilder) {
    	this.comprovanteEmprestimoBuilder = comprovanteEmprestimoBuilder;
    	this.comprovanteDevolucaoBuilder = comprovanteDevolucaoBuilder;
    }

    public ComprovanteEmprestimo gerarComprovanteEmprestimo(Emprestimo emprestimo) {
        this.comprovanteEmprestimoBuilder.buildLocador(emprestimo.getCliente().getNome());
        this.comprovanteEmprestimoBuilder.buildCodigo(emprestimo.getCodigo());
        this.comprovanteEmprestimoBuilder.buildDataEmprestimo(emprestimo.getDataEmprestimo());
        this.comprovanteEmprestimoBuilder.buildDataDevolucao(emprestimo.getDataDevolucao());
        this.comprovanteEmprestimoBuilder.buildRecursos(emprestimo.getRecursos());
        
        ComprovanteEmprestimo comprovanteEmprestimo = this.comprovanteEmprestimoBuilder.getComprovante();
        return comprovanteEmprestimo;
    }
    
    public ComprovanteDevolucao gerarComprovanteDevolucao(Emprestimo emprestimo, double valor) {
        this.comprovanteDevolucaoBuilder.buildLocador(emprestimo.getCliente().getNome());
        this.comprovanteDevolucaoBuilder.buildCodigo(emprestimo.getCodigo());
        this.comprovanteDevolucaoBuilder.buildDataEmprestimo(emprestimo.getDataEmprestimo());
        this.comprovanteDevolucaoBuilder.buildDataDevolucao(emprestimo.getDataDevolucao());
        this.comprovanteDevolucaoBuilder.buildRecursos(emprestimo.getRecursos());
        this.comprovanteDevolucaoBuilder.buildValor(valor);
        
        ComprovanteDevolucao comprovanteDevolucao = this.comprovanteDevolucaoBuilder.getComprovante();
        return comprovanteDevolucao;
    }

}
