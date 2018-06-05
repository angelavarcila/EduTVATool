package co.com.edutva.bd;

import java.io.Serializable;

public class UsuarioRol implements Serializable{
	
	private Integer idUsuarioRol;
	private Usuario usuario;
	private Rol rol;
	
	public UsuarioRol() {
	
	}
	
	public UsuarioRol(Integer idUsuarioRol, Usuario usuario, Rol rol) {
		this.idUsuarioRol = idUsuarioRol;
		this.usuario = usuario;
		this.rol = rol;
	}
	
	public Integer getIdUsuarioRol() {
		return idUsuarioRol;
	}
	public void setIdUsuarioRol(Integer idUsuarioRol) {
		this.idUsuarioRol = idUsuarioRol;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
