package dominio;

import java.util.Date;
import java.util.List;

public abstract class ComprovanteEmprestimo extends Comprovante {

	public ComprovanteEmprestimo(String empresa, String locador, Long codigo, Date dataEmprestimo, Date dataDevolucao, List<Recurso> recursos) {
		super(empresa, locador, codigo, dataEmprestimo, dataDevolucao, recursos);
	}
	
}