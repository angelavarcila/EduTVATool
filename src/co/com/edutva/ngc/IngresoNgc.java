package co.com.edutva.ngc;

import java.util.List;

import co.com.edutva.bd.RolPermiso;
import co.com.edutva.bd.Usuario;

public interface IngresoNgc {

	public int validarIngresoUsuario(String usuario, String clave) throws Exception;

	public Usuario obtenerUsuario(String usuario) throws Exception;

	public List<RolPermiso> obtenerPermisosUsuario(Usuario usuario) throws Exception;

	public void actualizarUsuario(Usuario usuario) throws Exception;
	
}
