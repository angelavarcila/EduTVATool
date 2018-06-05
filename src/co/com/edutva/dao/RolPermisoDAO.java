package co.com.edutva.dao;

import java.util.List;

import co.com.edutva.bd.Rol;
import co.com.edutva.bd.RolPermiso;

public class RolPermisoDAO extends GenericDAO{

	public static final String CAMPO_ROL = "rol";

	@SuppressWarnings("unchecked")
	public List<RolPermiso> obtenerPermisosRol(String campoRol, Rol rol) {
		return super.findWhere(RolPermiso.class, campoRol, rol);
	}
	
}
