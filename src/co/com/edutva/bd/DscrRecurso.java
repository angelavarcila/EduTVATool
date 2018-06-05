package co.com.edutva.bd;

public class DscrRecurso {

	private Integer idDscrRecurso;
	private Recurso recurso;
	private Usuario usuario;
	private String rutaDscrRecurso;
	private Boolean fnlDscrRecurso;


	public DscrRecurso() {
		super();
	}
	
	public DscrRecurso(Integer idDscrRecurso, Recurso recurso, Usuario usuario,
			String rutaDscrRecurso) {
		super();
		this.idDscrRecurso = idDscrRecurso;
		this.recurso = recurso;
		this.usuario = usuario;
		this.rutaDscrRecurso = rutaDscrRecurso;
	}
	public Integer getIdDscrRecurso() {
		return idDscrRecurso;
	}
	public void setIdDscrRecurso(Integer idDscrRecurso) {
		this.idDscrRecurso = idDscrRecurso;
	}
	public Recurso getRecurso() {
		return recurso;
	}
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getRutaDscrRecurso() {
		return rutaDscrRecurso;
	}
	public void setRutaDscrRecurso(String rutaDscrRecurso) {
		this.rutaDscrRecurso = rutaDscrRecurso;
	}
	
	public Boolean getFnlDscrRecurso() {
		return fnlDscrRecurso;
	}

	public void setFnlDscrRecurso(Boolean fnlDscrRecurso) {
		this.fnlDscrRecurso = fnlDscrRecurso;
	}
}
