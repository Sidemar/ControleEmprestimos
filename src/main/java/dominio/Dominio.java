package dominio;

public abstract class Dominio {
	
	protected /*@ spec_public @*/ long codigo;
	
	public /*@ pure @*/ Long getCodigo() {
		return codigo;
	}

	/*@
	@ assignable this.codigo;
	@ ensures this.codigo == codigo;
	@*/
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dominio other = (Dominio) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	

}
