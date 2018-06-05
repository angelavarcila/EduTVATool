package co.com.edutva.ngc;

import java.util.List;

import co.com.edutva.bd.DscrRecurso;
import co.com.edutva.bd.Recurso;
import co.com.edutva.bd.Usuario;

public interface ResourceNgc {
	
	public List<Recurso> obtenerRecursos() throws Exception;
	
	public void saveResourceDescription(Usuario usuario, Recurso recurso, String rutaDscrRecurso, boolean isDscrpcnFnl) throws Exception;
	
	public List<DscrRecurso> getDscrRecursoForUser(Usuario usuario) throws Exception;
}
