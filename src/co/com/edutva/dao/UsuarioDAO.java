package co.com.edutva.dao;

import java.util.List;

import org.apache.log4j.Logger;

import co.com.edutva.bd.Usuario;

public class UsuarioDAO extends GenericDAO {

	public static final String CAMPO_USUARIO = "usuario";
	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Obtiene un objeto usuario <code>Usuario</code> especificando el campo y
	 * el objeto a buscar. El campo debe tener un &iacute;ndice &uacute;nico.
	 * 
	 * @param campo el campo que contiene el objeto especificado
	 * @param objeto el objeto a buscar en el campo especificado
	 * @return el objeto usuario <code>Usuario</code>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Usuario obtenerUsuario(String campo, Object objeto) throws Exception {
		List<Usuario> listaUsuario = super.findWhere(Usuario.class, campo,
				objeto);
		if (listaUsuario.size() != 1) {
			return null;
		} else {
			return listaUsuario.get(0);
		}
	}

//	/**
//	 * Registra un objeto usuario <code>Usuario</code>.
//	 * 
//	 * @param usuario el objeto usuario <code>Usuario</code> a registrar
//	 * @throws Exception
//	 */
//	public void registrarUsuario(Usuario usuario) throws Exception {
//		if (logger.isDebugEnabled())
//			logger.debug(new StringBuilder("Registrando usuario = ")
//					.append(usuario.getUsuario()));
//		super.save(usuario);
//	}

	/**
	 * Actualiza un objeto usuario <code>Usuario</code>.
	 * 
	 * @param usuario el objeto usuario <code>Usuario</code> a actualizar
	 * @throws Exception
	 */
	public void actualizarUsuario(Usuario usuario) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug(new StringBuilder("Actualizando usuario = ")
					.append(usuario.getUsuario()));
		super.update(usuario);
	}
}
