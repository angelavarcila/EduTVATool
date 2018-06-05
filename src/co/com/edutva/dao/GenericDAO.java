package co.com.edutva.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;


public class GenericDAO {//extends HibernateDaoSupport {

	@Autowired
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Inserta un objeto perteneciente a un registro en la base de datos, si este elemento existe (se evalua clave primaria) ser&aacuten actualizados sus atributos.
	 * 
	 * @param ob Object mapeado por Hibernate que será almcenado o actualizado en base de datos
	 */
	public void saveOrUpdate(Object ob) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		s.saveOrUpdate(ob);
		t.commit();
	}

//	/**
//	 * Almacena un objeto en la base de datos.
//	 * 
//	 * @param ob Object mapeado por hibernate correspondiente a una tabla
//	 */
//	public void save(Object ob) {
//		sessionFactory.openSession().save(ob);
//	}

	/**
	 * Actualiza un objeto en base de datos.
	 * 
	 * @param ob Object mapeado por hibernate correspondiente a una tabla
	 */
	public void update(Object ob) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		s.update(ob);
		t.commit();
	}

//	/**
//	 * Consulta un objeto o registro de base de datos dado un identificador.
//	 * 
//	 * @param clazz Class que corresponde al objeto mapeado
//	 * @param id Serializable identificador del objeto a buscar
//	 * @return Object objeto o registro encontrado
//	 */
//	@SuppressWarnings({ "rawtypes" })
//	public Object find(Class clazz, Serializable id) {
//		return sessionFactory.openSession().get(clazz, id);
//	}

	/**
	 * Consulta todo los objetos o registro de la tabla relacionada al tipo de clase suministrado como par&aacute;metro.
	 * 
	 * @param clazz Class que corresponde al objeto mapeado
	 * @return List estructura de datos con el resultado de la consulta
	 */
	@SuppressWarnings({ "rawtypes" })
	public List findAll(Class clazz, String orderBy) {
		List objs = sessionFactory.openSession().createCriteria(clazz).addOrder(Order.asc(orderBy))
			     .list();	
		return objs;	
	}
	
//	@SuppressWarnings("rawtypes")
//	public List findBySQLQuery(String q) {
//		Session s = sessionFactory.getCurrentSession();
//		Transaction t = s.beginTransaction();	
//		List objs = s.createSQLQuery(q).list();
//		t.commit();
//		return objs;
//	}

	/**
	 * Consulta una lista de registros de base de datos dados los par&aacute;metros especificados.
	 * 
	 * @param clazz Class que corresponde al objeto mapeado
	 * @param field String campo utilizado para la busqueda
	 * @param object Object elemento que ser&aacute; buscado
	 * @return List estructura de datos con el resultado de la consulta
	 */
	@SuppressWarnings({ "rawtypes" })
	public List findWhere(Class clazz, String field, Object object) {
		List objs = sessionFactory.openSession().createCriteria(clazz)
			     .add( Restrictions.eq(field, object) )
			     	     .list();
		return objs;
	}
}