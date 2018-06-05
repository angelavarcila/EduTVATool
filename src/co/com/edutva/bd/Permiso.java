package co.com.edutva.bd;

import java.io.Serializable;
import java.util.Date;

public class Permiso implements Serializable{

	private Integer idPermiso;
	private String nmbrPermiso;
	private String mnPermiso;
	private String dscrpcnPermiso;
	private Date fchrgPermiso;

	public Permiso() {
	}
	
	public Permiso(Integer idPermiso, String nmbrPermiso, String mnPermiso,
			String dscrpcnPermiso, Date fchrgPermiso) {
		super();
		this.idPermiso = idPermiso;
		this.nmbrPermiso = nmbrPermiso;
		this.mnPermiso = mnPermiso;
		this.dscrpcnPermiso = dscrpcnPermiso;
		this.fchrgPermiso = fchrgPermiso;
	}

	public Integer getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getNmbrPermiso() {
		return nmbrPermiso;
	}

	public void setNmbrPermiso(String nmbrPermiso) {
		this.nmbrPermiso = nmbrPermiso;
	}

	public String getMnPermiso() {
		return mnPermiso;
	}

	public void setMnPermiso(String mnPermiso) {
		this.mnPermiso = mnPermiso;
	}

	public String getDscrpcnPermiso() {
		return dscrpcnPermiso;
	}

	public void setDscrpcnPermiso(String dscrpcnPermiso) {
		this.dscrpcnPermiso = dscrpcnPermiso;
	}

	public Date getFchrgPermiso() {
		return fchrgPermiso;
	}

	public void setFchrgPermiso(Date fchrgPermiso) {
		this.fchrgPermiso = fchrgPermiso;
	}	
	
}
