package co.com.edutva.ngc;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;

import co.com.edutva.bd.RolPermiso;
import co.com.edutva.bd.Usuario;
import co.com.edutva.bd.UsuarioRol;
import co.com.edutva.dao.RolPermisoDAO;
import co.com.edutva.dao.UsuarioRolDAO;
import co.com.edutva.dao.UsuarioDAO;
import co.com.edutva.utl.Constantes;

public class IngresoNgcImpl implements IngresoNgc{

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	private UsuarioDAO usuarioDAO;
	private UsuarioRolDAO usuarioRolDAO;
	private RolPermisoDAO rolPermisoDAO;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public UsuarioRolDAO getUsuarioRolDAO() {
		return usuarioRolDAO;
	}

	public void setUsuarioRolDAO(UsuarioRolDAO usuarioRolDAO) {
		this.usuarioRolDAO = usuarioRolDAO;
	}

	public RolPermisoDAO getRolPermisoDAO() {
		return rolPermisoDAO;
	}

	public void setRolPermisoDAO(RolPermisoDAO rolPermisoDAO) {
		this.rolPermisoDAO = rolPermisoDAO;
	}

	public int validarIngresoUsuario(String usuario, String clave) throws Exception {
		// Búsqueda de usuario en base de datos
		Usuario usuarioObj = usuarioDAO.obtenerUsuario(UsuarioDAO.CAMPO_USUARIO, usuario);
		if(usuarioObj != null){
			// Usuario encontrado
			if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Usuario ").append(usuarioObj.getUsuario()).append(" encontrado. Verificando clave"));
			// Validar la clave ingresada
			if(usuarioObj.getPsswrdUsuario().equalsIgnoreCase(clave)){
				Executions.getCurrent().getDesktop().getSession().setAttribute(Constantes.SESION_USUARIO, usuarioObj);
				return Constantes.EXITO;
			}else {
				return Constantes.ERROR;
			}			
		}else{
			return Constantes.ERROR;
		}
	}

	@Override
	public Usuario obtenerUsuario(String usuario) throws Exception {
		Usuario usuarioObj = usuarioDAO.obtenerUsuario(UsuarioDAO.CAMPO_USUARIO, usuario);
		return usuarioObj;
	}

	@Override
	public List<RolPermiso> obtenerPermisosUsuario(Usuario usuario) throws Exception {
		UsuarioRol usuarioRol = usuarioRolDAO.obtenerRolUsuario(UsuarioRolDAO.CAMPO_USUARIO,usuario);
		if(usuarioRol!=null){
			List<RolPermiso> rolPermisos = rolPermisoDAO.obtenerPermisosRol(RolPermisoDAO.CAMPO_ROL, usuarioRol.getRol());
			return rolPermisos;
		}else {
			return null;	
		}
	}

	@Override
	public void actualizarUsuario(Usuario usuario) throws Exception {
		usuarioDAO.actualizarUsuario(usuario);
	}

}
