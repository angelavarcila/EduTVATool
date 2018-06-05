package co.com.edutva.bd;

import java.io.Serializable;
import java.util.Date;

public class Rol implements Serializable{

	private Integer idRol;
	private String nmbrRol;
	private String dscrpcnRol;
	private Date fchrgRol;
	
	public Rol() {
		
	}
		
	public Rol(Integer idRol, String nmbrRol, String dscrpcnRol, Date fchrgRol) {
		this.idRol = idRol;
		this.nmbrRol = nmbrRol;
		this.dscrpcnRol = dscrpcnRol;
		this.fchrgRol = fchrgRol;
	}

	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public String getNmbrRol() {
		return nmbrRol;
	}
	public void setNmbrRol(String nmbrRol) {
		this.nmbrRol = nmbrRol;
	}
	public String getDscrpcnRol() {
		return dscrpcnRol;
	}
	public void setDscrpcnRol(String dscrpcnRol) {
		this.dscrpcnRol = dscrpcnRol;
	}
	public Date getFchrgRol() {
		return fchrgRol;
	}
	public void setFchrgRol(Date fchrgRol) {
		this.fchrgRol = fchrgRol;
	}
	
	
	
}
