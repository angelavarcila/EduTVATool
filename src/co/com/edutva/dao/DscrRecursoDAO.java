package co.com.edutva.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import co.com.edutva.bd.DscrRecurso;
import co.com.edutva.bd.Recurso;
import co.com.edutva.bd.Usuario;

public class DscrRecursoDAO extends GenericDAO{

	@SuppressWarnings("rawtypes")
	public List findDscrRecurso(Usuario usuario, Recurso recurso) {
		List objs = sessionFactory.openSession().createCriteria(DscrRecurso.class)
			     .add( Restrictions.eq("usuario", usuario) ).add(Restrictions.eq("recurso", recurso))
			     	     .list();
		return objs;
	}
	
	@SuppressWarnings("rawtypes")
	public List findDscrRecursoByUsuario(Usuario usuario) {
		List objs = sessionFactory.openSession().createCriteria(DscrRecurso.class)
			     .add( Restrictions.eq("usuario", usuario))
			     	     .list();
		return objs;
	}
	
}
