package dominio;

import java.util.Date;
import java.util.List;

public interface ComprovanteEmprestimoBuilder {
    void buildLocador(String nomeLocador);
    void buildCodigo(Long codigo);
    void buildDataEmprestimo(Date dataEmprestimo);
    void buildDataDevolucao(Date dataDevolucao);
    void buildRecursos(List<Recurso> recursos);
    
    ComprovanteEmprestimo getComprovante();
}
