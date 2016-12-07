package dominio;

import java.util.Date;
import java.util.List;

public interface ComprovanteDevolucaoBuilder {
    void buildLocador(String nomeLocador);
    void buildCodigo(Long codigo);
    void buildDataEmprestimo(Date dataEmprestimo);
    void buildDataDevolucao(Date dataDevolucao);
    void buildRecursos(List<Recurso> recursos);
    void buildValor(double valor);
    
    ComprovanteDevolucao getComprovante();
}
