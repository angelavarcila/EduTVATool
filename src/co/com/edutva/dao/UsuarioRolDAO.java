package co.com.edutva.dao;

import java.util.List;

import co.com.edutva.bd.Usuario;
import co.com.edutva.bd.UsuarioRol;

public class UsuarioRolDAO extends GenericDAO{

	public static final String CAMPO_USUARIO = "usuario";

	@SuppressWarnings("unchecked")
	public UsuarioRol obtenerRolUsuario(String campoUsuarioRol, Usuario usuario) {
		List<UsuarioRol> listaRol = super.findWhere(UsuarioRol.class, campoUsuarioRol, usuario);
		if (listaRol.size() != 1) {
			return null;
		} else {
			return listaRol.get(0);
		}
	}
	
}
