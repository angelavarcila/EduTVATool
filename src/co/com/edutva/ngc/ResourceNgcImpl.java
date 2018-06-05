package co.com.edutva.ngc;

import java.util.List;

import co.com.edutva.bd.DscrRecurso;
import co.com.edutva.bd.Recurso;
import co.com.edutva.bd.Usuario;
import co.com.edutva.dao.DscrRecursoDAO;
import co.com.edutva.dao.ResourceDAO;

public class ResourceNgcImpl implements ResourceNgc{

	private ResourceDAO resourceDAO;
	private DscrRecursoDAO dscrRecursoDAO;
	
	public DscrRecursoDAO getDscrRecursoDAO() {
		return dscrRecursoDAO;
	}

	public void setDscrRecursoDAO(DscrRecursoDAO dscrRecursoDAO) {
		this.dscrRecursoDAO = dscrRecursoDAO;
	}

	public ResourceDAO getResourceDAO() {
		return resourceDAO;
	}

	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Recurso> obtenerRecursos() throws Exception {
		List<Recurso> recursos = resourceDAO.findAll(Recurso.class, "idRecurso");//getAllResources("select * from recurso");
		return recursos;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void saveResourceDescription(Usuario usuario, Recurso recurso, String rutaDscrRecurso, boolean isDscrpcnFnl) throws Exception {
		//Verificar si el usuario ya marcó el recurso, si es así no hacer nada porque ya existe la relación y la ruta no cambia
		List dscrRecrLst = dscrRecursoDAO.findDscrRecurso(usuario, recurso);
		if(!(dscrRecrLst!=null && !dscrRecrLst.isEmpty())){
			DscrRecurso dscrRecurso = new DscrRecurso();
			dscrRecurso.setRecurso(recurso);
			dscrRecurso.setUsuario(usuario);
			dscrRecurso.setRutaDscrRecurso(rutaDscrRecurso);
			dscrRecurso.setFnlDscrRecurso(isDscrpcnFnl);
			dscrRecursoDAO.saveOrUpdate(dscrRecurso);
		}else {
			((DscrRecurso)dscrRecrLst.get(0)).setFnlDscrRecurso(isDscrpcnFnl);
			dscrRecursoDAO.saveOrUpdate((DscrRecurso)dscrRecrLst.get(0));
		}	
	}
	
	@SuppressWarnings("unchecked")
	public List<DscrRecurso> getDscrRecursoForUser(Usuario usuario) throws Exception{
		List<DscrRecurso> dscrRecursos = dscrRecursoDAO.findDscrRecursoByUsuario(usuario);
		return dscrRecursos;
	}
	
}
