package co.com.edutva.bd;

import java.io.Serializable;

public class RolPermiso implements Serializable{

	private Integer idRolPermiso;
	private Rol rol;
	private Permiso permiso;
	
	public RolPermiso() {
		
	}

	public RolPermiso(Integer idRolPermiso, Rol rol, Permiso permiso) {
		this.idRolPermiso = idRolPermiso;
		this.rol = rol;
		this.permiso = permiso;
	}

	public Integer getIdRolPermiso() {
		return idRolPermiso;
	}

	public void setIdRolPermiso(Integer idRolPermiso) {
		this.idRolPermiso = idRolPermiso;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}
	
}
